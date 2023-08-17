package com.example.trainimageannotation.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.trainimageannotation.po.Task;
import com.example.trainimageannotation.service.ITaskService;
import com.example.trainimageannotation.vo.EasyResult;
import com.example.trainimageannotation.vo.TaskVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author LENOVO
 * @date 20230814
 */
@Controller
public class TaskController {
    @Resource
    private ITaskService taskService;

    @RequestMapping(value = "/task/list",method = RequestMethod.GET)
    @ResponseBody
    public EasyResult showTaskList(int page, int limit){
        List<TaskVo> taskVoList = taskService.showTaskVoList(page-1,limit);

        EasyResult<TaskVo> taskVoEasyResult = new EasyResult<>();
        taskVoEasyResult.setCode(0);
        taskVoEasyResult.setMsg("");
        taskVoEasyResult.setCount(taskService.getTaskCounts());
        taskVoEasyResult.setData(taskVoList);

        System.out.println(JSONObject.toJSONString(taskVoEasyResult));
        return taskVoEasyResult;
    }
    @RequestMapping(value = "/task/one",method = RequestMethod.GET)
    @ResponseBody
    public Task showTaskOne(Long taskId){
        Task task = taskService.showTaskById(taskId);
        System.out.println(JSONObject.toJSONString(task));
        return task;
    }
}
