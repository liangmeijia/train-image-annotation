package com.example.trainimageannotation.service;

import com.example.trainimageannotation.po.Model;
import com.example.trainimageannotation.vo.DataVo;

import java.util.List;

/**
 * @author LENOVO
 * @date 20230814
 */
public interface IModelService {
    List<Model> showModelList(int offset, int limit);
    Long getModelCount();
}
