package com.zben.cloud.weather.vo;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * @DESC:
 * @AUTHOR: jhon.zhou
 * @DATE: 2019/7/6 0006 22:48
 */
@Data
@XmlRootElement(name = "c")
@XmlAccessorType(XmlAccessType.FIELD)
public class CityList {

    @XmlElement(name = "d")
    private List<City> cityList;
}
