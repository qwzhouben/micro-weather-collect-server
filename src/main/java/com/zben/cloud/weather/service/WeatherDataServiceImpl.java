package com.zben.cloud.weather.service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zben.cloud.weather.vo.WeatherResponse;

/**
 * WeatherDataService 实现.
 *
 * @since 1.0.0 2017年11月22日
 * @author <a href="https://waylau.com">Way Lau</a>
 */
@Service
@Slf4j
public class WeatherDataServiceImpl implements WeatherDataService {
    private static final String WEATHER_URI = "http://wthrcdn.etouch.cn/weather_mini?";
    private static final Long TIME_OUT = 1800l;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public WeatherResponse getDataByCityId(String cityId) {
        String uri = WEATHER_URI + "citykey=" + cityId;
        return this.doGetWeather(uri);
    }

    @Override
    public WeatherResponse getDataByCityName(String cityName) {
        String uri = WEATHER_URI + "city=" + cityName;
        return this.doGetWeather(uri);
    }

    @Override
    public void syncWeatherDataByCityId(String cityId) {
        String uri = WEATHER_URI + "citykey=" + cityId;
        //先从缓存中查询
        String key = uri;

        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();

        //从服务中查询
        saveDataToRedis(uri, key, null, opsForValue);

    }

    private WeatherResponse doGetWeather(String uri) {
        //先从缓存中查询
        String key = uri;

        ObjectMapper mapper = new ObjectMapper();
        WeatherResponse resp = null;
        String strBody = null;

        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        if (redisTemplate.hasKey(key)) {
            log.info("redis has data");
            strBody = opsForValue.get(key);
        } else {
            log.info("redis has not data");
            strBody = saveDataToRedis(uri, key, strBody, opsForValue);
        }

        try {
            resp = mapper.readValue(strBody, WeatherResponse.class);
        } catch (IOException e) {
            log.error("Error!", e);
        }

        return resp;
    }

    private String saveDataToRedis(String uri, String key, String strBody, ValueOperations<String, String> opsForValue) {
        //从服务中查询
        ResponseEntity<String> respString = restTemplate.getForEntity(uri, String.class);
        if (respString.getStatusCodeValue() == 200) {
            strBody = respString.getBody();
        }
        //存储到缓存中
        opsForValue.set(key, strBody, TIME_OUT, TimeUnit.SECONDS);
        return strBody;
    }

}