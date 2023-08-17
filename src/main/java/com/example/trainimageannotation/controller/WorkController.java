package com.example.trainimageannotation.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.trainimageannotation.po.Task;
import com.example.trainimageannotation.service.IDataService;
import com.example.trainimageannotation.service.ITaskService;
import com.example.trainimageannotation.util.Constant;
import com.example.trainimageannotation.util.ReadFileUtil;
import com.example.trainimageannotation.vo.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LENOVO
 */
@Controller
public class WorkController {
    @Resource
    private ITaskService taskService;
    @Resource
    private IDataService dataService;


    @RequestMapping(value = "/work/manual_anno",method = RequestMethod.GET)
    @ResponseBody
    public WorkVo  manualAnno(Long taskId){
        Task task = new Task();
        task.setTaskStatus(Constant.TaskStatus.MANUAL_ANNOTATING.getCode());
        task.setTaskId(Long.valueOf(taskId));
        //1.修改任务状态为 【手工标注中】
        taskService.updateTaskStatus(task);
        task = taskService.showTaskById(Long.valueOf(taskId));
        //2.查询当前任务的数据列表
        String dataInPath = dataService.showDataById(task.getDataId()).getDataInPath();
        String[] fileName = ReadFileUtil.getFileName(dataInPath);
        System.out.println(fileName);
        //3.

        WorkVo workVo = new WorkVo();
        workVo.setTask(task);
        workVo.setFileName(fileName);
        return workVo;
    }

    @RequestMapping(value = "/work/task_list",method = RequestMethod.GET)
    @ResponseBody
    public EasyResult dropDownVo2annoList(){
        //1.标注人员只显示【新建，自动标注完成，手工标注完成】状态的任务
        ArrayList<Integer> taskStatusList = new ArrayList<>(3);
        taskStatusList.add(Constant.TaskStatus.NEW_BUILD.getCode());
        taskStatusList.add(Constant.TaskStatus.MANUAL_ANNOTATED.getCode());
        taskStatusList.add(Constant.TaskStatus.AUTO_ANNOTATED.getCode());
        List<TaskVo> taskVoList = taskService.showTaskByStatus(taskStatusList);
        //2.渲染前端下拉框
        List<DropDownVo> dropDownVoList = new ArrayList<>(taskVoList.size());
        for (TaskVo taskVo:taskVoList) {
            DropDownVo dropDownVo= new DropDownVo();
            dropDownVo.setTitle(taskVo.getTaskName()+"（状态："+taskVo.getTaskStatus()+"）");
            dropDownVo.setId(taskVo.getTaskId());
            dropDownVoList.add(dropDownVo);
        }
        EasyResult<DropDownVo> taskEasyResult = new EasyResult<>();
        taskEasyResult.setCode(0);
        taskEasyResult.setMsg("");
        taskEasyResult.setCount(Long.valueOf(taskVoList.size()));
        taskEasyResult.setData(dropDownVoList);

        System.out.println(JSONObject.toJSONString(taskEasyResult));
        return taskEasyResult;
    }
    @RequestMapping(value = "/work/saveAnno",method = RequestMethod.POST)
    @ResponseBody
    public String saveAnno(@RequestBody AnnoSaveVo request){
        System.out.println(request);

        return "success";
    }
}
