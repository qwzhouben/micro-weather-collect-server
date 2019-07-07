package com.zben.cloud.weather.service;

import com.zben.cloud.weather.util.XmlBuidler;
import com.zben.cloud.weather.vo.City;
import com.zben.cloud.weather.vo.CityList;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @DESC: City Data ServiceImpl
 * @AUTHOR: jhon.zhou
 * @DATE: 2019/7/7 0007 9:35
 */
@Service
public class CityDataServiceImpl implements CityDataService {

    private static final String CITY_XML = "citylist.xml";

    @Override
    public List<City> listCity() throws Exception {
        CityList cityList = (CityList) XmlBuidler.xmlStringToObj(CityList.class, CITY_XML);
        return cityList.getCityList();
    }
}
