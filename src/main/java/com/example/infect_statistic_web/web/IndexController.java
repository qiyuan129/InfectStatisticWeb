package com.example.infect_statistic_web.web;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class IndexController {
    //返回一个String的返回值，框架会自动在 templates目录下找到与返回值对应的html，后由Thymeleaf渲染出来
    @RequestMapping("/countryFec")
    public String country(){
        return "country";
    }

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }
    @RequestMapping("/province")
    public String province(){
        return "province";
    }

    @RequestMapping("/test")
    public ModelAndView sayHello() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("test");
        modelAndView.addObject("key", 12345);
        return modelAndView;
    }

    @RequestMapping("/requestProvince")
    public ModelAndView requestProvince(HttpServletRequest request) {
        String reqProvince = request.getParameter("province");//获取http请求里的参数
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("province");//要转到的页面
        modelAndView.addObject("province", reqProvince);//数据
        //modelAndView.addObject("data", reqData);
        return modelAndView;




        /*Map<String, Object> map = new HashMap<String, Object>();
        map.put("name","河南");
        map.put("value",2000);
        List list=new ArrayList();
        list.add(map);
        *//*map = new HashMap<String, Object>();
        map.put("name","河北");
        map.put("value",2400);
        list.add(map);*//*
        request.setAttribute("mapDataJson", JSONArray.fromObject(list));*/




    }




}
