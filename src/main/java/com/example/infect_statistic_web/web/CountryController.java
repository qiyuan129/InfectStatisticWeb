package com.example.infect_statistic_web.web;

import com.example.infect_statistic_web.service.InfectStatistic;
import com.example.infect_statistic_web.model.DailyInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.HashMap;

@Controller
public class CountryController {

    @RequestMapping("/country")
    public ModelAndView getCountryStatistic(String dateStr){
        InfectStatistic infectInfoOperator = new InfectStatistic();
        infectInfoOperator.readLogs();
        DailyInfo countryInfo=infectInfoOperator.getCountryChange(LocalDate.parse("2020-02-02"));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("country");
        modelAndView.addObject("countryInfo", countryInfo);

        System.out.println(countryInfo.toString(DailyInfo.ALL_TYPES));
        return modelAndView;
    }

    @RequestMapping("/provinces")
    public ModelAndView getProvinceStatistic(){
        InfectStatistic infectInfoOperator = new InfectStatistic();
        infectInfoOperator.readLogs();

        HashMap<String,DailyInfo> provinceInfos=infectInfoOperator.getProvinceLatestStatistic();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("province");
        modelAndView.addObject("provinceInfos",provinceInfos);

        return modelAndView;
    }
    @RequestMapping("/testRun")
    public String testRun(){
        //用于测试，之后会删除
        return "index.html";
    }

}
