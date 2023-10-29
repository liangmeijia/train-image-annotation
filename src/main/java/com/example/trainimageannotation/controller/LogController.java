package com.example.trainimageannotation.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.trainimageannotation.po.Log;
import com.example.trainimageannotation.service.ILogService;
import com.example.trainimageannotation.vo.TableResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author LENOVO
 */
@Controller
public class LogController {
    @Resource
    private ILogService logService;

    @RequestMapping(value = "/log/list",method = RequestMethod.GET)
    @ResponseBody
    public TableResult showDataList(int page, int limit){
        List<Log> logList = logService.showLogList(page - 1, limit);

        TableResult<Log> taskVoTableResult = new TableResult<>();
        taskVoTableResult.setCode(0);
        taskVoTableResult.setMsg("");
        taskVoTableResult.setCount(logService.getLogCount());
        taskVoTableResult.setData(logList);

        System.out.println(JSONObject.toJSONString(logList));
        return taskVoTableResult;
    }

}
