package com.example.trainimageannotation.service.impl;

import com.example.trainimageannotation.dao.LogDao;
import com.example.trainimageannotation.po.Log;
import com.example.trainimageannotation.service.ILogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author LENOVO
 */
@Service
public class LogService implements ILogService {
    @Resource
    private LogDao logDao;
    @Override
    public List<Log> showLogList(int offset, int limit) {
        List<Log> logList = logDao.selectLogList(offset, limit);
        return logList;
    }

    @Override
    public Long getLogCount() {
        return logDao.selectLogCount();
    }

    @Override
    public Log showLogById(Long logId) {
        return logDao.selecLogById(logId);
    }
}
