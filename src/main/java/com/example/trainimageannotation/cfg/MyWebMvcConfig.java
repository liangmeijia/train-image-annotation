package com.example.trainimageannotation.cfg;

import com.example.trainimageannotation.service.IDataService;
import com.example.trainimageannotation.vo.DataVo;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author LENOVO
 */
@Component
public class MyWebMvcConfig implements WebMvcConfigurer {
    @Resource
    private IDataService dataService;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Long dataCount = dataService.getDataCount();
        List<DataVo> dataVoList = dataService.showDataList(0, dataCount.intValue());
        ResourceHandlerRegistration resourceHandlerRegistration = registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        for (DataVo dataVo :dataVoList){
            resourceHandlerRegistration.addResourceLocations("file:"+dataVo.getDataInPath()+"/");
        }
        //"classpath:/META-INF/resources/", "classpath:/resources/", "classpath:/static/", "classpath:/public/"

    }
}