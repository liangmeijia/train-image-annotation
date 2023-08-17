package com.example.trainimageannotation.service.impl;

import com.example.trainimageannotation.dao.DataDao;
import com.example.trainimageannotation.dao.ModelDao;
import com.example.trainimageannotation.dao.TaskDao;
import com.example.trainimageannotation.po.Data;
import com.example.trainimageannotation.po.Model;
import com.example.trainimageannotation.po.Task;
import com.example.trainimageannotation.service.ITaskService;
import com.example.trainimageannotation.util.Constant;
import com.example.trainimageannotation.vo.TaskVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LENOVO
 */
@Service
public class TaskService implements ITaskService {
    @Resource
    private TaskDao taskDao;
    @Resource
    private DataDao dataDao;
    @Resource
    private ModelDao modelDao;

    @Override
    public List<TaskVo> showTaskVoList(int offset, int limit) {
        List<Task> taskList = taskDao.selectTaskList(offset, limit);

        List<TaskVo> taskVoList = new ArrayList<>(taskList.size());
        for (Task task:taskList){
            Data data = dataDao.selectDataById(task.getDataId());
            Model model = modelDao.selectModelById(task.getModelId());

            TaskVo taskVo = new TaskVo();
            taskVo.setId(task.getId());
            taskVo.setTaskId(task.getTaskId());
            taskVo.setTaskName(task.getTaskName());

            taskVo.setTaskStatus(Constant.TaskStatus.getInfoByCode(task.getTaskStatus()));
            taskVo.setTaskType(Constant.TaskType.getInfoByCode(task.getTaskType()));
            taskVo.setTaskWay(Constant.TaskWay.getInfoByCode(task.getTaskWay()));

            taskVo.setModelName(model == null?"":model.getModelName());
            taskVo.setDataName(data.getDataName());
            taskVo.setCreator(task.getCreator());
            taskVo.setCreateTime(task.getCreateTime());
            taskVo.setUpdateTime(task.getUpdateTime());
            taskVo.setTag(task.getTag());
            taskVoList.add(taskVo);
        }
        return taskVoList;
    }

    @Override
    public Task showTaskById(Long taskId) {
        return  taskDao.selectTaskById(taskId);
    }

    @Override
    public List<TaskVo> showTaskByStatus(List<Integer> taskStatusList) {
        List<Task> taskList = taskDao.selectTaskByStatus(taskStatusList);
        List<TaskVo> taskVoList = new ArrayList<>(taskList.size());
        for (Task task:taskList){
            Data data = dataDao.selectDataById(task.getDataId());
            Model model = modelDao.selectModelById(task.getModelId());

            TaskVo taskVo = new TaskVo();
            taskVo.setId(task.getId());
            taskVo.setTaskId(task.getTaskId());
            taskVo.setTaskName(task.getTaskName());

            taskVo.setTaskStatus(Constant.TaskStatus.getInfoByCode(task.getTaskStatus()));
            taskVo.setTaskType(Constant.TaskType.getInfoByCode(task.getTaskType()));
            taskVo.setTaskWay(Constant.TaskWay.getInfoByCode(task.getTaskWay()));

            taskVo.setModelName(model == null?"":model.getModelName());
            taskVo.setDataName(data.getDataName());
            taskVo.setCreator(task.getCreator());
            taskVo.setCreateTime(task.getCreateTime());
            taskVo.setUpdateTime(task.getUpdateTime());
            taskVo.setTag(task.getTag());
            taskVoList.add(taskVo);
        }
        return taskVoList;
    }

    @Override
    public Long getTaskCounts() {
        return taskDao.selectTaskCount();
    }

    @Override
    public int updateTaskStatus(Task task) {
        return taskDao.updateTaskStatus(task);
    }
}
