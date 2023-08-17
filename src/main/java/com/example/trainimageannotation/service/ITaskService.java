package com.example.trainimageannotation.service;

import com.example.trainimageannotation.po.Task;
import com.example.trainimageannotation.vo.TaskVo;

import java.util.List;

/**
 * @author LENOVO
 */
public interface ITaskService {
    List<TaskVo> showTaskVoList(int offset, int limit);
    Task showTaskById(Long taskId);
    List<TaskVo> showTaskByStatus(List<Integer> taskStatusList);
    Long getTaskCounts();
    int updateTaskStatus(Task task);
}
