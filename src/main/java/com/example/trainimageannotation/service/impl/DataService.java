package com.example.trainimageannotation.service.impl;

import com.example.trainimageannotation.dao.DataDao;
import com.example.trainimageannotation.po.Data;
import com.example.trainimageannotation.service.IDataService;
import com.example.trainimageannotation.util.Constant;
import com.example.trainimageannotation.vo.DataVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataService implements IDataService {
    @Resource
    private DataDao dataDao;

    @Override
    public List<DataVo> showDataList(int offset, int limit) {
        List<Data> dataList = dataDao.selectDataList(offset, limit);
        List<DataVo> dataVoList = new ArrayList<>(dataList.size());

        for (Data data:dataList){
            DataVo dataVo = new DataVo();
            dataVo.setId(data.getId());
            dataVo.setDataId(data.getDataId());
            dataVo.setDataName(data.getDataName());
            dataVo.setDataInPath(data.getDataInPath());
            dataVo.setDataOutPath(data.getDataOutPath());
            dataVo.setTagWay(Constant.TagWay.getEnumByCode(data.getTagWay()).toString());

            dataVo.setCreateTime(data.getCreateTime());
            dataVo.setUpdateTime(data.getUpdateTime());
            dataVo.setCreator(data.getCreator());
            dataVoList.add(dataVo);
        }
        return dataVoList;
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
