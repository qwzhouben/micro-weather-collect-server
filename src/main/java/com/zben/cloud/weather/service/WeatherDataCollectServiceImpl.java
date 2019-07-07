package com.zben.cloud.weather.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @DESC: Weather Data Collect ServiceImpl
 * @AUTHOR: jhon.zhou
 * @DATE: 2019/7/7 0007 15:39
 */
@Service
public class WeatherDataCollectServiceImpl implements WeatherDataCollectService {

    private static final String WEATHER_URI = "http://wthrcdn.etouch.cn/weather_mini?";
    private static final Long TIME_OUT = 1800l;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void syncWeatherDataByCityId(String cityId) {
        String uri = WEATHER_URI + "citykey=" + cityId;
        //先从缓存中查询
        String key = uri;

        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();

        //从服务中查询
        saveDataToRedis(uri, key, null, opsForValue);
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
