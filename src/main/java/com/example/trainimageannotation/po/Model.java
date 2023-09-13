package com.example.trainimageannotation.po;

import java.util.Date;

/**
 * @author LENOVO
 */
public class Model {
    private Long id;
    private Long modelId;
    private String modelName;
    /**
     * 模型权重文件位置
     */
    private String modelWeights;
    /**
     * 模型推理文件位置
     */
    private String cfg;
    /**
     * 来源
     */
    private String source;
    private String creator;
    private Date createTime;
    private Date updateTime;

    public Model() {
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getModelWeights() {
        return modelWeights;
    }

    public void setModelWeights(String modelWeights) {
        this.modelWeights = modelWeights;
    }

    public String getCfg() {
        return cfg;
    }

    public void setCfg(String cfg) {
        this.cfg = cfg;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
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
        return "Model{" +
                "id=" + id +
                ", modelId=" + modelId +
                ", modelName='" + modelName + '\'' +
                ", modelWeights='" + modelWeights + '\'' +
                ", source='" + source + '\'' +
                ", creator='" + creator + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
