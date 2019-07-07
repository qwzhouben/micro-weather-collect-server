package com.zben.cloud.weather.service;

import com.zben.cloud.weather.vo.Weather;
import com.zben.cloud.weather.vo.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @DESC: Weather Report ServiceImpl
 * @AUTHOR: jhon.zhou
 * @DATE: 2019/7/7 0007 12:17
 */
@Service
public class WeatherReportServiceImpl implements WeatherReportService {

    @Autowired
    WeatherDataService weatherDataService;

    @Override
    public Weather getWeatherByCityId(String cityId) {
        WeatherResponse response = weatherDataService.getDataByCityId(cityId);
        return response.getData();
    }
}
