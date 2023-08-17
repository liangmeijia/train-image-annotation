package com.example.trainimageannotation.dao;

import com.example.trainimageannotation.po.Model;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author LENOVO
 */
@Mapper
public interface ModelDao {
    Model selectModelById(Long modelId);
    List<Model> selectModelList(int offset, int limit);
    Long selectModelCount();
}
