package com.example.trainimageannotation.dao;

import com.example.trainimageannotation.po.Log;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author LENOVO
 */
@Mapper
public interface LogDao {
    Log selecLogById(Long logId);
    List<Log> selectLogList(int offset, int limit);
    Long selectLogCount();
}
