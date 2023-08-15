package com.example.trainimageannotation.vo;

import java.util.Date;

/**
 * @author LENOVO
 */
public class DataVo {
    private Long id;
    private Long dataId;
    private String dataName;
    private String dataInPath;
    private String dataOutPath;
    /**
     * 标注文件格式（0-PascalVOC,1-COCO,2-YOLO,3-DOTA）
     */
    private String tagWay;
    private String creator;
    private Date createTime;
    private Date updateTime;

    public DataVo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDataId() {
        return dataId;
    }

    public void setDataId(Long dataId) {
        this.dataId = dataId;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public String getDataInPath() {
        return dataInPath;
    }

    public void setDataInPath(String dataInPath) {
        this.dataInPath = dataInPath;
    }

    public String getDataOutPath() {
        return dataOutPath;
    }

    public void setDataOutPath(String dataOutPath) {
        this.dataOutPath = dataOutPath;
    }

    public String getTagWay() {
        return tagWay;
    }

    public void setTagWay(String tagWay) {
        this.tagWay = tagWay;
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
}
