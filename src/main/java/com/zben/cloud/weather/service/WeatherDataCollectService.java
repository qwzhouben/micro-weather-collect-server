package com.zben.cloud.weather.service;

/**
 * @DESC: Weather Data Collect Service
 * @AUTHOR: jhon.zhou
 * @DATE: 2019/7/7 0007 15:39
 */
public interface WeatherDataCollectService {
    /**
     * 根据城市id同步天气数据
     */
    void syncWeatherDataByCityId(String cityId);
}
