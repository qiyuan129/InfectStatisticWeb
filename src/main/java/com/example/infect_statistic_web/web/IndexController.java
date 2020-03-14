package com.example.infect_statistic_web.web;
import com.example.infect_statistic_web.model.DailyInfo;
import com.example.infect_statistic_web.service.InfectStatistic;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


class Echarts{
    private String name;
    private Integer value;
    public Echarts(String name, Integer value) {
        this.name = name;
        this.value = value;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}




@Controller
public class IndexController {
    //返回一个String的返回值，框架会自动在 templates目录下找到与返回值对应的html，后由Thymeleaf渲染出来
    @RequestMapping("/countryFec")
    public String country(){
        return "country";
    }

    @RequestMapping("/login")
    public String hello(){
        return "login";
    }
    @RequestMapping("/provinceFec")
    public String province(){
        return "province";
    }

    @RequestMapping("/test1233")
    public ModelAndView sayHello() {
        /*InfectStatistic infectInfoOperator = new InfectStatistic();
        infectInfoOperator.readLogs();
        DailyInfo countryInfo=infectInfoOperator.getCountryChange(LocalDate.parse("2020-02-02"));
        DailyInfo countryChange=infectInfoOperator.getCountryChange(LocalDate.parse("2020-02-02"));
        HashMap<String,DailyInfo> provinceInfos=infectInfoOperator.getProvincesLatestStatistic();*/


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("test");
       /* modelAndView.addObject("countryInfo", countryInfo);
        modelAndView.addObject("provinceInfos",provinceInfos);
        modelAndView.addObject("countryChange",countryChange);*/



        //我测试的
     /*   HashMap<String,Integer> mymap=new  HashMap<String,Integer>();
        mymap.put("云南",15);
        mymap.put("四川",125);
        mymap.put("河北",151);
        mymap.put("广西",145);
        mymap.put("台湾",185);
        mymap.put("湖北",1615);*/
        modelAndView.addObject("myMap","555");
        //System.out.println(countryInfo.toString(DailyInfo.ALL_TYPES));
        return modelAndView;

    }


    @RequestMapping("/test")
    @ResponseBody
    public List<Echarts> echartsshow(Model model){
        List<Echarts> list = new ArrayList<Echarts>();
        list.add(new Echarts("正级和",100));
        list.add(new Echarts("正的负极",100));
        list.add(new Echarts("正四川负的极",100));
        list.add(new Echarts("正负的事实极",100));
        return list;
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
