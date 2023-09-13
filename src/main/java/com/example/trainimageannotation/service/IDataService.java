package com.example.trainimageannotation.service;

import com.example.trainimageannotation.po.Data;

import java.util.List;


public interface IDataService {
    List<Data> showDataList(int offset, int limit);
    Long getDataCount();
    Data showDataById(Long dataId);
}
