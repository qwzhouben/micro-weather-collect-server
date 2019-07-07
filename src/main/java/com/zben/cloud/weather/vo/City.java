package com.zben.cloud.weather.vo;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @DESC:城市列表xml的包装
 * @AUTHOR: jhon.zhou
 * @DATE: 2019/7/6 0006 22:45
 */
@Data
public class City {
    private String cityId;
    private String cityName;
    private String cityCode;
    private String province;
}
