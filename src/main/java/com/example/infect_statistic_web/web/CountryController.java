package com.example.infect_statistic_web.web;

import com.example.infect_statistic_web.service.InfectStatistic;
import com.example.infect_statistic_web.model.DailyInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

@Controller
public class CountryController {

    @RequestMapping("/country")
    public ModelAndView getCountryStatistic(String dateStr){
        InfectStatistic infectInfoOperator = new InfectStatistic();
        infectInfoOperator.readLogs();
        DailyInfo countryInfo=infectInfoOperator.getCountryChange(LocalDate.parse("2020-02-02"));
        DailyInfo countryChange=infectInfoOperator.getCountryChange(LocalDate.parse("2020-02-02"));
        HashMap<String,DailyInfo> provinceInfos=infectInfoOperator.getProvincesLatestStatistic();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("country");
        modelAndView.addObject("countryInfo", countryInfo);
        modelAndView.addObject("provinceInfos",provinceInfos);
        modelAndView.addObject("countryChange",countryChange);

        //System.out.println(countryInfo.toString(DailyInfo.ALL_TYPES));
        return modelAndView;
    }

    @RequestMapping("/provinces")
    public ModelAndView getProvinceStatistic(String provinceName,String dateStr){
        InfectStatistic infectInfoOperator = new InfectStatistic();
        infectInfoOperator.readLogs();
        DailyInfo provinceInfo=infectInfoOperator.getProvinceInfo(LocalDate.parse(dateStr),provinceName);
        DailyInfo change=infectInfoOperator.getProvinceChange(LocalDate.parse(dateStr),provinceName);
        ArrayList<DailyInfo> allDailyInfo=infectInfoOperator.getProvinceAllDailyInfo(provinceName);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("province");
        modelAndView.addObject("provinceInfo",provinceInfo);
        modelAndView.addObject("provicneChange",change);
        modelAndView.addObject("allDailyInfo",allDailyInfo);

        return modelAndView;
    }
    @RequestMapping("/testRun")
    public String testRun(){
        //用于测试，之后会删除
        return "index.html";
    }

}
