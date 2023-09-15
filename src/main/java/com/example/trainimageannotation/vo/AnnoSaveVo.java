package com.example.trainimageannotation.vo;

import java.util.List;

/**
 * @description 标注保存的信息
 * @author LENOVO
 */
public class AnnoSaveVo {
    private String fileName;
    private Integer width;
    private Integer height;
    private String currentTaskId;
    private List<AnnoVo> regions;

    public AnnoSaveVo() {
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public List<AnnoVo> getRegions() {
        return regions;
    }

    public void setRegions(List<AnnoVo> regions) {
        this.regions = regions;
    }

    public String getCurrentTaskId() {
        return currentTaskId;
    }

    public void setCurrentTaskId(String currentTaskId) {
        this.currentTaskId = currentTaskId;
    }
}
