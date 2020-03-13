package com.example.infect_statistic_web.web;

import com.example.infect_statistic_web.InfectStatistic;
import com.example.infect_statistic_web.model.Country;
import com.example.infect_statistic_web.model.DailyInfo;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

@Controller
public class CountryController {

    @RequestMapping("/country")
    public ModelAndView getCountryStatistic(String dateStr){
        InfectStatistic infectInfoOperator = new InfectStatistic();
        infectInfoOperator.readLogs();
        DailyInfo countryInfo=infectInfoOperator.getCountryLatestStatistic();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("country");
        modelAndView.addObject("countryInfo", countryInfo);

        //System.out.println(countryInfo.toString(DailyInfo.ALL_TYPES));
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
