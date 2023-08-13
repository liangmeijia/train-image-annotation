package com.example.trainimageannotation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * @author LENOVO
 * @date 20230813
 */
@Controller
public class HomeController {
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String  index(Model model){
        return "index";
    }
    @RequestMapping(value = "/task",method = RequestMethod.GET)
    public String  task(Model model){
        return "task";
    }
    @RequestMapping(value = "/data",method = RequestMethod.GET)
    public String  data(Model model){
        return "data";
    }
}
