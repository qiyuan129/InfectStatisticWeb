package com.example.infect_statistic_web.web;

import com.example.infect_statistic_web.repository.ArticleRepository;
import com.example.infect_statistic_web.service.CountryMapData;
import com.example.infect_statistic_web.service.InfectStatistic;
import com.example.infect_statistic_web.model.DailyInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    @Autowired
    ArticleRepository localRepository;

//    @RequestMapping("/countryInfos")
//    @ResponseBody
//    public List<Object> getCountryInfos(String dateStr){
//        InfectStatistic infectInfoOperator = new InfectStatistic();
//        infectInfoOperator.readLogs();
//        List<Object> dataList=infectInfoOperator.getCountryInfos(LocalDate.parse("2020-02-02"));
////        dataList.add(countryInfo);
////        dataList.add(countryChange);
////        dataList.add(provinceInfos);
//        //System.out.println(countryInfo.toString(DailyInfo.ALL_TYPES));
//
//        return dataList;
//    }

//    @RequestMapping("/provinceInfos")
//    public List<Object> getProvinceInfos(String provinceName,String dateStr){
//        InfectStatistic infectInfoOperator = new InfectStatistic();
//        infectInfoOperator.readLogs();
//        List<Object> dataList=infectInfoOperator.getProvinceInfos(provinceName,LocalDate.parse("2020-02-02"));
////        dataList.add(countryInfo);
////        dataList.add(countryChange);
////        dataList.add(provinceInfos);
//        //System.out.println(countryInfo.toString(DailyInfo.ALL_TYPES));
//        return dataList;
//    }

    @RequestMapping("/province")       //省份页面测试
    public String testProvince(Model map,String provinceName,String newDate){
        LocalDate date;

        InfectStatistic infectInfoOperator = new InfectStatistic();
        infectInfoOperator.readLogs();
        if(newDate!=null) {
            date = LocalDate.parse(newDate);
        }
        else{
            date=infectInfoOperator.getEndDate();
        }

        Object[] data=infectInfoOperator.getChartData(provinceName,date).toArray();
        DailyInfo provinceStatistic=infectInfoOperator.getProvinceStatistic(date,provinceName);
        DailyInfo provinceChange=infectInfoOperator.getProvinceChange(date,provinceName);
        map.addAttribute("provinceName",provinceName);
        map.addAttribute("statistic",provinceStatistic);
        map.addAttribute("change",provinceChange);
        map.addAttribute("Xdata",data[0]);
        map.addAttribute("infected",data[1]);
        map.addAttribute("suspected",data[2]);
        map.addAttribute("dead",data[3]);
        map.addAttribute("cured",data[4]);
        map.addAttribute("articles",localRepository.getList(provinceName));
        return "province";
    }

    @RequestMapping("/country")      //全国页面测试
    public String testCountry(Model map,String newDate){
        LocalDate date;

        InfectStatistic infectInfoOperator = new InfectStatistic();
        infectInfoOperator.readLogs();
        if(newDate!=null) {
            date = LocalDate.parse(newDate);
        }
        else{
            date=infectInfoOperator.getEndDate();
        }
        DailyInfo countryStatistic = infectInfoOperator.getCountryStatistic(date);
        DailyInfo countryChange = infectInfoOperator.getCountryChange(date);
        List<CountryMapData> mapData = infectInfoOperator.getCountryMapData(date);
        map.addAttribute("statistic",countryStatistic);
        map.addAttribute("change",countryChange);
        map.addAttribute("mapData",mapData);
        map.addAttribute("articles",localRepository.getList("全国"));
        return "country";


    }

//    @RequestMapping("/error")
//    public String error(){
//        return "errorPage";
//    }

}
