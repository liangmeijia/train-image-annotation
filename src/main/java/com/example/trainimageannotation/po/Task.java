package com.example.trainimageannotation.po;

import java.util.Date;

/**
 * @author LENOVO
 */
public class Task {
    private Long id;
    private Long taskId;
    private String taskName;
    /**
     * 任务状态（0-新建状态，1-人工自动标注状态，2...）
     */
    private int taskStatus;
    /**
     * 标注类型(0-图像分类,1-物体检测,2-图像分割)
     */
    private int taskType;
    /**
     * 数据集id
     */
    private Long dataId;
    /**
     * 标签集
     */
    private String tag;
    /**
     * 标注方式（0-手工标注方式，1-智能标注方式）
     */
    private int taskWay;
    /**
     * 标注模型id
     */
    private Long modelId;
    private String creator;
    private Date createTime;
    private Date updateTime;

    public Task() {
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(int taskStatus) {
        this.taskStatus = taskStatus;
    }

    public int getTaskType() {
        return taskType;
    }

    public void setTaskType(int taskType) {
        this.taskType = taskType;
    }

    public Long getDataId() {
        return dataId;
    }

    public void setDataId(Long dataId) {
        this.dataId = dataId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getTaskWay() {
        return taskWay;
    }

    public void setTaskWay(int taskWay) {
        this.taskWay = taskWay;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", taskId=" + taskId +
                ", taskName='" + taskName + '\'' +
                ", taskStatus=" + taskStatus +
                ", taskType=" + taskType +
                ", dataId=" + dataId +
                ", tag='" + tag + '\'' +
                ", taskWay='" + taskWay + '\'' +
                ", modelId=" + modelId +
                ", creator='" + creator + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
