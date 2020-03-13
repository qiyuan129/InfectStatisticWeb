package com.example.infect_statistic_web.web;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


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


}
