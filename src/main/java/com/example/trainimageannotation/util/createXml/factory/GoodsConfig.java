package com.example.trainimageannotation.util.createXml.factory;

import com.example.trainimageannotation.util.createXml.goods.IoperationTag;
import com.example.trainimageannotation.util.createXml.goods.PascalVoc;
import com.example.trainimageannotation.util.Constant;
import com.example.trainimageannotation.util.createXml.goods.YOLO;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author LENOVO
 */
public class GoodsConfig {
    /** 标注文件操作策略组 */
    protected static Map<Integer, IoperationTag> operationTagMap = new ConcurrentHashMap<>();

    @Resource
    private PascalVoc pascalVoc;
    @Resource
    private YOLO yolo;

    @PostConstruct
    public void init() {
        operationTagMap.put(Constant.TagWay.PASCALVOC.getCode(), pascalVoc);
        operationTagMap.put(Constant.TagWay.YOLO.getCode(),yolo);
    }

}
