package com.example.trainimageannotation.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.trainimageannotation.po.Task;
import com.example.trainimageannotation.service.ITaskService;
import com.example.trainimageannotation.vo.TableResult;
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
    public TableResult showTaskList(int page, int limit){
        List<TaskVo> taskList = taskService.showTaskList(page-1,limit);

        TableResult<TaskVo> taskVoTableResult = new TableResult<>();
        taskVoTableResult.setCode(0);
        taskVoTableResult.setMsg("");
        taskVoTableResult.setCount(taskService.getTaskCounts());
        taskVoTableResult.setData(taskList);

        System.out.println(JSONObject.toJSONString(taskVoTableResult));
        return taskVoTableResult;
    }
    @RequestMapping(value = "/task/one",method = RequestMethod.GET)
    @ResponseBody
    public Task showTaskOne(Long taskId){
        Task task = taskService.showTaskById(taskId);
        System.out.println(JSONObject.toJSONString(task));
        return task;
    }
    @RequestMapping(value = "/task/updateStatus",method = RequestMethod.POST)
    @ResponseBody
    public String updateStatus(Long taskId,Integer status){
        Task task = new Task();
        task.setTaskStatus(status);
        task.setTaskId(taskId);
        //1.修改任务状态
        int i = taskService.updateTaskStatus(task);
        if(i==1){
            return "success";
        }else {
            return "fail";
        }

    }
}
