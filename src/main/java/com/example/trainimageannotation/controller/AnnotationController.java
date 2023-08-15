package com.example.trainimageannotation.controller;

import com.example.trainimageannotation.po.Task;
import com.example.trainimageannotation.service.IDataService;
import com.example.trainimageannotation.service.ITaskService;
import com.example.trainimageannotation.util.Constant;
import com.example.trainimageannotation.util.ReadFileUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;

/**
 * @author LENOVO
 */
@Controller
public class AnnotationController {
    @Resource
    private ITaskService taskService;
    @Resource
    private IDataService dataService;

    @RequestMapping(value = "/manualAnno",method = RequestMethod.GET)
    public String  anno(@RequestParam(value = "taskId",required = true) Long taskId,Model model){
        Task task = new Task();
        task.setTaskStatus(Constant.TaskStatus.MANUAL_ANNOTATING.getCode());
        task.setTaskId(taskId);
        //.修改任务状态
        taskService.updateTaskStatus(task);

        task = taskService.showTaskById(taskId);
        model.addAttribute("task",task);

        //查询当前任务的数据列表
        ArrayList<File> fileArrayList = new ArrayList<>();
        ReadFileUtil.getFiles(fileArrayList,dataService.showDataById(task.getDataId()).getDataInPath());
        System.out.println(fileArrayList);
        model.addAttribute("dataInPath",fileArrayList);

        return "/anno";
    }
}
