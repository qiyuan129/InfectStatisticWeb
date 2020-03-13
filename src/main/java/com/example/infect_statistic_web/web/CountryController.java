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
    public ModelAndView getCountryStatistic(){
        Resource resource = new ClassPathResource("logs");
        ModelAndView modelAndView = null;
//// 有些系统提示找不到资源，可以把上面的代码换成下面这句：
//// ClassPathResource resource = new ClassPathResource("picture/bottom.png");
        try {
            File sourceFile =  resource.getFile();
            String path=sourceFile.getPath();
            String[] args={"-log",path};
            InfectStatistic infectInfoOperator = new InfectStatistic(args);
            infectInfoOperator.readLogs();

            DailyInfo countryInfo=infectInfoOperator.getCountryLatestStatistic();
            modelAndView = new ModelAndView();
            modelAndView.setViewName("country");
            modelAndView.addObject("countryInfo", countryInfo);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return modelAndView;




    }

    @RequestMapping("/provinces")
    public ModelAndView getProvinceStatistic(){
        Resource resource = new ClassPathResource("logs");
        ModelAndView modelAndView = null;
//// 有些系统提示找不到资源，可以把上面的代码换成下面这句：
//// ClassPathResource resource = new ClassPathResource("picture/bottom.png");
        try {
            File sourceFile =  resource.getFile();
            String path=sourceFile.getPath();
            String[] args={"-log",path};
            InfectStatistic infectInfoOperator = new InfectStatistic(args);
            infectInfoOperator.readLogs();

            HashMap<String,DailyInfo> provinceInfos=infectInfoOperator.getProvinceLatestStatistic();
            modelAndView = new ModelAndView();
            modelAndView.setViewName("province");
            modelAndView.addObject("provinceInfos",provinceInfos);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return modelAndView;




    }
    @RequestMapping("/testRun")
    public String testRun(){
        //用于测试，之后会删除
        return "index.html";
    }

}
