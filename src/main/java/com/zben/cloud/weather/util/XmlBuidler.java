package com.zben.cloud.weather.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;

/**
 * @DESC: 解析xml
 * @AUTHOR: jhon.zhou
 * @DATE: 2019/7/7 0007 9:26
 */
public class XmlBuidler {

    /**
     * 将xml转为指定pojo
     */
    public static Object xmlStringToObj(Class<?> clazz, String filePath) throws Exception {
        //读取xml
        Resource resource = new ClassPathResource(filePath);
        BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()));
        StringBuffer sb = new StringBuffer();
        String line = "";

        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();

        //xml转为对象
        JAXBContext context = JAXBContext.newInstance(clazz);
        Reader reader = new StringReader(sb.toString());

        //xml转为对象
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Object object = unmarshaller.unmarshal(reader);
        if (null != reader) {
            reader.close();
        }
        return object;
    }
}
