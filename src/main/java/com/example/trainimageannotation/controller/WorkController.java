package com.example.trainimageannotation.controller;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.example.trainimageannotation.service.IModelService;
import com.example.trainimageannotation.util.createXml.factory.OperationTagFactory;
import com.example.trainimageannotation.util.createXml.goods.IoperationTag;
import com.example.trainimageannotation.po.Data;
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
    @Resource
    private IModelService modelService;
    @Resource
    private OperationTagFactory operationTagFactory;

    @RequestMapping(value = "/work/manual_anno",method = RequestMethod.GET)
    @ResponseBody
    public Result  manualAnno(Long taskId){
        Task task = new Task();
        task.setTaskStatus(Constant.TaskStatus.MANUAL_ANNOTATING.getCode());
        task.setTaskId(taskId);
        //1.修改任务状态为 【手工标注中】
        taskService.updateTaskStatus(task);
        task = taskService.showTaskById(taskId);
        //2.查询当前任务的数据列表
        String dataInPath = dataService.showDataById(task.getDataId()).getDataInPath();
        List<String> fileName = ReadFileUtil.getFileName(dataInPath);
        System.out.println(fileName);
        //3.
        String taskJson = JSONUtil.toJsonStr(task);
        String fileNameJson = JSONUtil.toJsonStr(fileName);
        String info = "{\"task\":"+taskJson+",\"fileName\":"+fileNameJson+"}";
        Result result = new Result(Constant.ResponseCode.SUCCESS.getCode(), info);
        System.out.println(info);

        return result;
    }
    @RequestMapping(value = "/work/auto_anno",method = RequestMethod.POST)
    @ResponseBody
    public Result  autoAnno (Long taskId){
        Task task = taskService.showTaskById(taskId);
        //2.自动标注开始
        Result result = modelService.start(task.getModelId(), task.getDataId());
        //3.修改任务状态为 【自动标注完成】
        task.setTaskStatus(Constant.TaskStatus.AUTO_ANNOTATED.getCode());
        taskService.updateTaskStatus(task);
        return result;
    }

    @RequestMapping(value = "/work/task_list",method = RequestMethod.GET)
    @ResponseBody
    public Result dropDownVo2annoList(){
        //1.标注人员只显示【新建，自动标注完成，手工标注完成】状态的任务
        ArrayList<Integer> taskStatusList = new ArrayList<>(3);
        taskStatusList.add(Constant.TaskStatus.NEW_BUILD.getCode());
        taskStatusList.add(Constant.TaskStatus.MANUAL_ANNOTATED.getCode());
        taskStatusList.add(Constant.TaskStatus.AUTO_ANNOTATED.getCode());
        List<TaskVo> taskList = taskService.showTaskByStatus(taskStatusList);
        //2.渲染前端任务下拉框
        String info = "";
        for (int i =0;i<taskList.size();i++) {
            TaskVo taskVo = taskList.get(i);
            info += "{\"id\":"+taskVo.getTaskId()+",\"title\":\""+taskVo.getTaskName()+"（状态："+Constant.TaskStatus.getInfoByCode(taskVo.getTaskStatus())+"）\""+"}";
            if(i<taskList.size()-1){
                info+=",";
            }

        }
        Result result = new Result(Constant.ResponseCode.SUCCESS.getCode(), "["+info+"]");
        return result;
    }

    @RequestMapping(value = "/work/saveAnno",method = RequestMethod.POST)
    @ResponseBody
    public Result saveAnno(@RequestBody AnnoSaveVo annoSaveVo){
        Task task = taskService.showTaskById(Long.valueOf(annoSaveVo.getCurrentTaskId()));
        Data data = dataService.showDataById(task.getDataId());
        //保存标注
        IoperationTag operationTagService = operationTagFactory.getOperationTagService(data.getTagWay());
        boolean res = operationTagService.saveTag(annoSaveVo, data);
       Result result = new Result();
        if(res){
            result.setCode(Constant.ResponseCode.SUCCESS.getCode());
            result.setInfo("保存成功");
        }else {
            result.setCode(Constant.ResponseCode.UN_ERROR.getCode());
            result.setInfo("保存失败");
        }
        return result;

    }
    @RequestMapping(value = "/work/showAnno",method = RequestMethod.POST)
    @ResponseBody
    public List<AnnotationsW3c> showAnno(String fileName,String currentTaskId){
        Task task = taskService.showTaskById(Long.valueOf(currentTaskId));
        Data data = dataService.showDataById(task.getDataId());
        //显示标注
        IoperationTag operationTagService = operationTagFactory.getOperationTagService(data.getTagWay());
        List<AnnotationsW3c> annotationsW3c =  operationTagService.showXml(fileName,task,data);
        String jsonString = JSONObject.toJSONString(annotationsW3c);
        System.out.println(jsonString);
        return annotationsW3c;
    }
}
