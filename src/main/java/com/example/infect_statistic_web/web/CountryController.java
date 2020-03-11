package com.example.infect_statistic_web.web;

import com.example.infect_statistic_web.InfectStatistic;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
public class CountryController {

    @RequestMapping("/country")
    public void getCountryStatistic(){
//        Resource resource = new ClassPathResource("picture/bottom.png");
//// 有些系统提示找不到资源，可以把上面的代码换成下面这句：
//// ClassPathResource resource = new ClassPathResource("picture/bottom.png");
//        File sourceFile =  resource.getFile();
        System.out.println(ResourceUtils.CLASSPATH_URL_PREFIX);
        String path=new String(ResourceUtils.CLASSPATH_URL_PREFIX+"logs");
        String[] args={"-log","logs"};
        InfectStatistic infectInfoOperator = new InfectStatistic(args);
        infectInfoOperator.readLogs();
        infectInfoOperator.output();
    }
}
