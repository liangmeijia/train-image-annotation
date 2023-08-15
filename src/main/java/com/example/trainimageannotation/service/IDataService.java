package com.example.trainimageannotation.service;

import com.example.trainimageannotation.po.Data;
import com.example.trainimageannotation.vo.DataVo;

import java.util.List;


public interface IDataService {
    List<DataVo> showDataList(int offset, int limit);
    Long getDataCount();
    Data showDataById(Long dataId);
}
