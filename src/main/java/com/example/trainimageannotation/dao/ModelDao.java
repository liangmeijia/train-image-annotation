package com.example.trainimageannotation.dao;

import com.example.trainimageannotation.po.Model;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author LENOVO
 */
@Mapper
public interface ModelDao {
    Model selectModelById(Long modelId);
}
