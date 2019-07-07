package com.zben.cloud.weather.job;

import com.zben.cloud.weather.service.CityDataService;
import com.zben.cloud.weather.service.WeatherDataService;
import com.zben.cloud.weather.vo.City;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * @DESC:
 * @AUTHOR: jhon.zhou
 * @DATE: 2019/7/6 0006 22:18
 */
@Slf4j
public class WeatherDataJob extends QuartzJobBean {

    @Autowired
    CityDataService cityDataService;
    @Autowired
    WeatherDataService weatherDataService;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("Weather Data Job Start!");
        try {
            //获取城市列表
            List<City> cityList = cityDataService.listCity();
            if (!CollectionUtils.isEmpty(cityList)) {
                //同步天气
                cityList.forEach(city -> weatherDataService.syncWeatherDataByCityId(city.getCityId()));
            }
        } catch (Exception e) {
            log.error("Exception!", e);
        }
        log.info("Weather Data Job End!");
    }
}
