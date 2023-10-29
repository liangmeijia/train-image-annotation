package com.example.trainimageannotation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * @author LENOVO
 * @date 20230813
 */
@Controller
public class HomeController {

    @RequestMapping(value = "/task",method = RequestMethod.GET)
    public String  task(){
        return "task";
    }

    @RequestMapping(value = "/data",method = RequestMethod.GET)
    public String  data(){
        return "data";
    }

    @RequestMapping(value = "/model",method = RequestMethod.GET)
    public String  model(){
        return "model";
    }

    @RequestMapping(value = "/work",method = RequestMethod.GET)
    public String  work(){
        return "work";
    }

    @RequestMapping(value = "/check",method = RequestMethod.GET)
    public String  check(){
        return "check";
    }

    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public String  user(){
        return "user";
    }

    @RequestMapping(value = "/log",method = RequestMethod.GET)
    public String  log(){
        return "log";
    }
}
