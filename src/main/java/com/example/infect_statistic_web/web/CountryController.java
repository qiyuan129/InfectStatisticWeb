package com.example.infect_statistic_web.web;

import com.example.infect_statistic_web.service.CountryMapData;
import com.example.infect_statistic_web.service.InfectStatistic;
import com.example.infect_statistic_web.model.DailyInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class CountryController {

    @RequestMapping("/country")
    public String getCountryStatistic(){

        //System.out.println(countryInfo.toString(DailyInfo.ALL_TYPES));
        return "country";
    }

    @RequestMapping("/provinces")
    public String getProvinceStatistic(){
        return "province";
    }
    @RequestMapping("/testRun")
    public String testRun(){
        //用于测试，之后会删除
        return "index.html";
    }

    @RequestMapping("/countryInfos")
    @ResponseBody
    public List<Object> getCountryInfos(String dateStr){
        InfectStatistic infectInfoOperator = new InfectStatistic();
        infectInfoOperator.readLogs();
        List<Object> dataList=infectInfoOperator.getCountryInfos(LocalDate.parse("2020-02-02"));
//        dataList.add(countryInfo);
//        dataList.add(countryChange);
//        dataList.add(provinceInfos);
        //System.out.println(countryInfo.toString(DailyInfo.ALL_TYPES));
        return dataList;
    }

    @RequestMapping("/provinceInfos")
    @ResponseBody
    public List<Object> getProvinceInfos(String provinceName,String dateStr){
        InfectStatistic infectInfoOperator = new InfectStatistic();
        infectInfoOperator.readLogs();
        List<Object> dataList=infectInfoOperator.getProvinceInfos(provinceName,LocalDate.parse("2020-02-02"));
//        dataList.add(countryInfo);
//        dataList.add(countryChange);
//        dataList.add(provinceInfos);
        //System.out.println(countryInfo.toString(DailyInfo.ALL_TYPES));
        return dataList;
    }
}
