package com.example.trainimageannotation.service.impl;

import com.example.trainimageannotation.dao.DataDao;
import com.example.trainimageannotation.po.Data;
import com.example.trainimageannotation.service.IDataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DataService implements IDataService {
    @Resource
    private DataDao dataDao;

    @Override
    public List<Data> showDataList(int offset, int limit) {
        List<Data> dataList = dataDao.selectDataList(offset, limit);
        return dataList;
    }

    @Override
    public Long getDataCount() {
        return dataDao.selectDataCount();
    }

    @Override
    public Data showDataById(Long dataId) {
        return dataDao.selectDataById(dataId);
    }

}
