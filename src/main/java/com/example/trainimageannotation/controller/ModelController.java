package com.example.trainimageannotation.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.trainimageannotation.po.Model;
import com.example.trainimageannotation.service.IModelService;
import com.example.trainimageannotation.vo.TableResult;
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
public class ModelController {
    @Resource
    private IModelService modelService;

    @RequestMapping(value = "/model/list",method = RequestMethod.GET)
    @ResponseBody
    public TableResult showModelList(int page, int limit){
        List<Model> modelList = modelService.showModelList(page-1,limit);

        TableResult<Model> taskVoTableResult = new TableResult<>();
        taskVoTableResult.setCode(0);
        taskVoTableResult.setMsg("");
        taskVoTableResult.setCount(modelService.getModelCount());
        taskVoTableResult.setData(modelList);

        System.out.println(JSONObject.toJSONString(taskVoTableResult));
        return taskVoTableResult;
    }
}
