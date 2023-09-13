package com.example.trainimageannotation.service;

import com.example.trainimageannotation.po.Model;

import java.util.List;

/**
 * @author LENOVO
 * @date 20230814
 */
public interface IModelService {
    /**
     * 查询模型列表
     * @param offset
     * @param limit
     * @return
     */
    List<Model> showModelList(int offset, int limit);

    /**
     * 模型数量
     * @return
     */
    Long getModelCount();

    /**
     * 执行模型
     * @param modelId 模型id
     * @param dataId 数据集id
     */
    boolean start(Long modelId, Long dataId);

}
