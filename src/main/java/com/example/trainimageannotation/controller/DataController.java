package com.example.trainimageannotation.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.trainimageannotation.po.Data;
import com.example.trainimageannotation.service.IDataService;
import com.example.trainimageannotation.vo.EasyResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author LENOVO
 */
@Controller
public class DataController {
    @Resource
    private IDataService dataService;

    @RequestMapping(value = "/data/list",method = RequestMethod.GET)
    @ResponseBody
    public EasyResult showDataList(int page, int limit){
        List<Data> dataList = dataService.showDataList(page - 1, limit);

        EasyResult<Data> taskVoEasyResult = new EasyResult<>();
        taskVoEasyResult.setCode(0);
        taskVoEasyResult.setMsg("");
        taskVoEasyResult.setCount(dataService.getDataCount());
        taskVoEasyResult.setData(dataList);

        System.out.println(JSONObject.toJSONString(dataList));
        return taskVoEasyResult;
    }

}
