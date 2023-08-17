package com.example.trainimageannotation.vo;

import com.example.trainimageannotation.po.Task;

import java.util.List;

/**
 * @author LENOVO
 */
public class WorkVo {
    private Task task;
    private List<String> fileName;

    public WorkVo() {
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public List<String> getFileName() {
        return fileName;
    }

    public void setFileName(List<String> fileName) {
        this.fileName = fileName;
    }
}
