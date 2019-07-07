package com.zben.cloud.weather.job;

import com.zben.cloud.weather.service.WeatherDataCollectService;
import com.zben.cloud.weather.vo.City;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @DESC:
 * @AUTHOR: jhon.zhou
 * @DATE: 2019/7/6 0006 22:18
 */
@Slf4j
public class WeatherDataJob extends QuartzJobBean {

   @Autowired
    WeatherDataCollectService weatherDataCollectService;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("Weather Data Job Start!");
        try {
            //获取城市列表
            //TODO 之后会调用城市列表服务API
            List<City> cityList = new ArrayList<>();
            City city = new City();
            city.setCityId("101280101");
            cityList.add(city);

            if (!CollectionUtils.isEmpty(cityList)) {
                //同步天气
                cityList.forEach(c -> weatherDataCollectService.syncWeatherDataByCityId(c.getCityId()));
            }
        } catch (Exception e) {
            log.error("Exception!", e);
        }
        log.info("Weather Data Job End!");
    }
}
