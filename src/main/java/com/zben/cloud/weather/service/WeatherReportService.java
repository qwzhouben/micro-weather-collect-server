package com.zben.cloud.weather.service;

import com.zben.cloud.weather.vo.Weather;

/**
 * @DESC: Weather Report Service
 * @AUTHOR: jhon.zhou
 * @DATE: 2019/7/7 0007 12:16
 */
public interface WeatherReportService {

    /**
     * 根据城市id获取天气信息
     */
    Weather getWeatherByCityId(String cityId);
}
