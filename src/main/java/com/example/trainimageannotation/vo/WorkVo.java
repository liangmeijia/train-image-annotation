package com.example.trainimageannotation.vo;

import com.example.trainimageannotation.po.Task;

/**
 * @author LENOVO
 */
public class WorkVo {
    private Task task;
    private String[] fileName;

    public WorkVo() {
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String[] getFileName() {
        return fileName;
    }

    public void setFileName(String[] fileName) {
        this.fileName = fileName;
    }
}
