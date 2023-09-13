package com.example.trainimageannotation.cfg;

import com.example.trainimageannotation.po.Data;
import com.example.trainimageannotation.service.IDataService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description 程序运行初期，将data表中所有dataInPath注入到spring的静态文件映射中
 * @author LENOVO
 */
@Component
public class MyWebMvcConfig implements WebMvcConfigurer {
    @Resource
    private IDataService dataService;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Long dataCount = dataService.getDataCount();
        List<Data> dataVoList = dataService.showDataList(0, dataCount.intValue());
        ResourceHandlerRegistration resourceHandlerRegistration = registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        for (Data data :dataVoList){
            resourceHandlerRegistration.addResourceLocations("file:"+data.getDataInPath()+"/");
        }
        //"classpath:/META-INF/resources/", "classpath:/resources/", "classpath:/static/", "classpath:/public/"

    }
}