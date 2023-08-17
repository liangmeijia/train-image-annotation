package com.example.trainimageannotation.service.impl;

import com.example.trainimageannotation.dao.ModelDao;
import com.example.trainimageannotation.po.Model;
import com.example.trainimageannotation.service.IModelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author LENOVO
 * @date 20230814
 */
@Service
public class ModelService implements IModelService {
    @Resource
    private ModelDao modelDao;

    @Override
    public List<Model> showModelList(int offset, int limit) {
        return modelDao.selectModelList(offset,limit);
    }

    @Override
    public Long getModelCount() {
        return modelDao.selectModelCount();
    }
}
