package com.example.trainimageannotation.dao;

import com.example.trainimageannotation.po.Data;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author LENOVO
 */
@Mapper
public interface DataDao {
    Data selectDataById(Long dataId);
    List<Data> selectDataList(int offset, int limit);
    Long selectDataCount();
}
