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
    Long getTaskCounts();
    int updateTaskStatus(Task task);
}
