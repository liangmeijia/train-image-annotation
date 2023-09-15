package com.example.trainimageannotation.util;

import java.util.Optional;

/**
 * @description: 枚举信息定义
 * @author LENOVO
 * @date ${DATE}
 */
public class Constant {
    /**
     * 通用的返回结果
     */
    public enum ResponseCode {
        SUCCESS(0, "成功"),
        UN_ERROR(1,"未知失败"),
        ILLEGAL_PARAMETER(2,"非法参数"),
        INDEX_DUP(3,"主键冲突"),
        NO_UPDATE(4,"SQL操作无更新");

        private int code;
        private String info;

        ResponseCode(int code, String info) {
            this.code = code;
            this.info = info;
        }

        public int getCode() {
            return code;
        }

        public String getInfo() {
            return info;
        }

    }
    /**
     * Ids 生成策略枚举
     */
    public enum Ids {
        /** 雪花算法 */
        SnowFlake,
        /** 日期算法 */
        ShortCode,
        /** 随机算法 */
        RandomNumeric;
    }
    /**
     * 任务状态state有7种值，
     * 0是新建状态；1是手工标注中状态；2是手工标注完成状态；3是自动标注中状态；4是自动标注完成状态；5是人工校验中状态；6是人工校验完成状态。
     */
    public enum TaskStatus{
        NEW_BUILD(0,"新建"),
        MANUAL_ANNOTATING(1,"手工标注中"),
        MANUAL_ANNOTATED(2,"手工标注完成"),
        AUTO_ANNOTATING(3,"自动标注中"),
        AUTO_ANNOTATED(4,"自动标注完成"),
        MANUAL_CHECKING(5,"人工校验中"),
        MANUAL_CHECKED(6,"人工校验完成");

        private Integer code;
        private String info;

        TaskStatus(Integer code, String info) {
            this.code = code;
            this.info = info;
        }

        public Integer getCode() {
            return code;
        }

        public String getInfo() {
            return info;
        }

        public static String getInfoByCode(Integer code){
            Optional<TaskStatus> m1 = EnumUtil.getEnumObject(TaskStatus.class, e -> e.getCode().equals(code));
            return m1.isPresent() ? m1.get().getInfo() : null;
        }
    }

    /**
     * 任务标注类型(0-图像分类,1-物体检测,2-图像分割)
     */
    public enum TaskType{
        IMAGE_CLASSIFICATION(0,"图像分类"),
        OBJECT_DETECTION(1,"物体检测"),
        IMAGE_SEGMENTATION(2,"图像分割");

        private Integer code;
        private String info;

        TaskType(Integer code, String info) {
            this.code = code;
            this.info = info;
        }

        public Integer getCode() {
            return code;
        }

        public String getInfo() {
            return info;
        }

        public static String getInfoByCode(Integer code){
            Optional<TaskType> m1 = EnumUtil.getEnumObject(TaskType.class, e -> e.getCode().equals(code));
            return m1.isPresent() ? m1.get().getInfo() : null;
        }
    }
    /**
     * 标注方式（0-手工标注方式，1-智能标注方式）
     */
    public enum TaskWay{
        MANUAL_ANNOTATION(0,"手工标注"),
        AUTO_ANNOTATION(1,"智能标注");

        private Integer code;
        private String info;

        TaskWay(Integer code, String info) {
            this.code = code;
            this.info = info;
        }

        public Integer getCode() {
            return code;
        }

        public String getInfo() {
            return info;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public static String getInfoByCode(Integer code){
            Optional<TaskWay> m1 = EnumUtil.getEnumObject(TaskWay.class, e -> e.getCode().equals(code));
            return m1.isPresent() ? m1.get().getInfo() : null;
        }
    }
    /**
     * 标注文件格式（0-PascalVOC,1-COCO,2-YOLO,3-DOTA）
     */
    public enum TagWay{
        PASCALVOC(0),
        COCO(1),
        YOLO(2),
        DOTA(3);

        private Integer code;

        TagWay(Integer code) {
            this.code = code;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public static TagWay getEnumByCode(Integer code){
            Optional<TagWay> m1 = EnumUtil.getEnumObject(TagWay.class, e -> e.getCode().equals(code));
            return m1.isPresent() ? m1.get() : null;
        }
    }
}
