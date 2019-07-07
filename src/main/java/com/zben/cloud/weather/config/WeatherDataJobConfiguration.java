package com.zben.cloud.weather.config;

import com.zben.cloud.weather.job.WeatherDataJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @DESC:定时任务
 * @AUTHOR: jhon.zhou
 * @DATE: 2019/7/6 0006 22:09
 */
@Configuration
public class WeatherDataJobConfiguration {

    //JobDetail
    @Bean
    public JobDetail weatherDataJobDetail() {
        return JobBuilder.newJob(WeatherDataJob.class)
                .withIdentity("weatherDataJobDetail")
                .storeDurably()
                .build();
    }

    //Trigger
    @Bean
    public Trigger weatherDataTrigger() {
        ScheduleBuilder builder = SimpleScheduleBuilder.repeatSecondlyForever(5);
        return TriggerBuilder.newTrigger()
                .withIdentity("weatherDataTrigger")
                .forJob(weatherDataJobDetail())
                .withSchedule(builder)
                .build();
    }
}
