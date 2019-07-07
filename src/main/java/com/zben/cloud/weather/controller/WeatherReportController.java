package com.zben.cloud.weather.controller;

import com.zben.cloud.weather.service.CityDataService;
import com.zben.cloud.weather.service.WeatherReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @DESC: Weather ReportController
 * @AUTHOR: jhon.zhou
 * @DATE: 2019/7/7 0007 12:19
 */
@Controller
@RequestMapping("/report")
public class WeatherReportController {

    @Autowired
    CityDataService cityDataService;
    @Autowired
    WeatherReportService weatherReportService;

    @GetMapping(value = "/cityId/{cityId}")
    public ModelAndView getReportByCityId(@PathVariable("cityId") String cityId, Model model) throws Exception {

        model.addAttribute("title", "老周老笨的天气预报");
        model.addAttribute("cityId", cityId);
        model.addAttribute("cityList", cityDataService.listCity());
        model.addAttribute("report", weatherReportService.getWeatherByCityId(cityId));
        return new ModelAndView("weather/report", "reportModel", model);
    }
}
