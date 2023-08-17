package com.example.trainimageannotation.dao;

import com.example.trainimageannotation.po.Task;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author LENOVO
 */
@Mapper
public interface TaskDao {
    List<Task> selectTaskList(int offset,int limit);
    Task selectTaskById(Long taskId);
    Long selectTaskCount();
    int updateTaskStatus(Task task);
    List<Task> selectTaskByStatus(List<Integer> taskStatusList);
}
