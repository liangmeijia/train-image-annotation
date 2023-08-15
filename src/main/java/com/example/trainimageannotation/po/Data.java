package com.example.trainimageannotation.po;

import java.util.Date;

/**
 * @author LENOVO
 */
public class Data {
    private Long id;
    private Long dataId;
    private String dataName;
    private String dataInPath;
    private String dataOutPath;
    /**
     * 标注文件格式（0-PascalVOC,1-COCO,2-YOLO,3-DOTA）
     */
    private int tagWay;
    private String creator;
    private Date createTime;
    private Date updateTime;

    public Data() {
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

    public int getTagWay() {
        return tagWay;
    }

    public void setTagWay(int tagWay) {
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

    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", dataId=" + dataId +
                ", dataName='" + dataName + '\'' +
                ", dataInPath='" + dataInPath + '\'' +
                ", dataOutPath='" + dataOutPath + '\'' +
                ", tagWay=" + tagWay +
                ", creator='" + creator + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
