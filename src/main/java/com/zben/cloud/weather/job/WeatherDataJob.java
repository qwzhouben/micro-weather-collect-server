package com.zben.cloud.weather.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @DESC:
 * @AUTHOR: jhon.zhou
 * @DATE: 2019/7/6 0006 22:18
 */
@Slf4j
public class WeatherDataJob extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("Weather Data Job");
    }
}
