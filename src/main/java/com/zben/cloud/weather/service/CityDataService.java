package com.zben.cloud.weather.service;

import com.zben.cloud.weather.vo.City;

import java.util.List;

/**
 * @DESC:
 * @AUTHOR: jhon.zhou
 * @DATE: 2019/7/7 0007 9:35
 */
public interface CityDataService {

    List<City> listCity() throws Exception;
}
