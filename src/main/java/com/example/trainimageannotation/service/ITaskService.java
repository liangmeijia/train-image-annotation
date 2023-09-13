package com.example.trainimageannotation.service;

import com.example.trainimageannotation.po.Task;
import com.example.trainimageannotation.vo.TaskVo;

import java.util.List;

/**
 * @author LENOVO
 */
public interface ITaskService {
    /**
     * 查询任务列表
     * @param offset
     * @param limit
     * @return
     */
    List<TaskVo> showTaskList(int offset, int limit);

    /**
     * 根据ID查询任务
     * @param taskId 任务ID
     * @return
     */
    Task showTaskById(Long taskId);

    /**
     * 根据任务状态查询任务列表
     * @param taskStatusList 任务状态列表
     * @return
     */
    List<TaskVo> showTaskByStatus(List<Integer> taskStatusList);

    /**
     * 查询任务数量
     * @return
     */
    Long getTaskCounts();

    /**
     * 更新任务状态
     * @param task 任务
     * @return
     */
    int updateTaskStatus(Task task);
}
