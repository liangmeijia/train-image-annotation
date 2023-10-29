package com.example.trainimageannotation.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.trainimageannotation.po.Data;
import com.example.trainimageannotation.po.User;
import com.example.trainimageannotation.service.IUserService;
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
public class UserController {
    @Resource
    private IUserService userService;

    @RequestMapping(value = "/user/list",method = RequestMethod.GET)
    @ResponseBody
    public TableResult showDataList(int page, int limit){
        List<User> userList = userService.showUserList(page - 1, limit);

        TableResult<User> taskVoTableResult = new TableResult<>();
        taskVoTableResult.setCode(0);
        taskVoTableResult.setMsg("");
        taskVoTableResult.setCount(userService.getUserCount());
        taskVoTableResult.setData(userList);

        System.out.println(JSONObject.toJSONString(userList));
        return taskVoTableResult;
    }

}
