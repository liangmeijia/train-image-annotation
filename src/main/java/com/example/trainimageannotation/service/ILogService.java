package com.example.trainimageannotation.service;

import com.example.trainimageannotation.po.Log;

import java.util.List;


public interface ILogService {
    List<Log> showLogList(int offset, int limit);
    Long getLogCount();
    Log showLogById(Long logId);
}
