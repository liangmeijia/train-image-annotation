package com.example.trainimageannotation.createXml.factory;

import com.example.trainimageannotation.createXml.goods.IOperationTag;
import com.example.trainimageannotation.createXml.goods.PascalVoc;
import com.example.trainimageannotation.util.Constant;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author LENOVO
 */
public class GoodsConfig {
    /** 标注文件操作策略组 */
    protected static Map<Integer, IOperationTag> operationTagMap = new ConcurrentHashMap<>();

    @Resource
    private PascalVoc pascalVoc;


    @PostConstruct
    public void init() {
        operationTagMap.put(Constant.TagWay.PASCALVOC.getCode(), pascalVoc);
    }

}
