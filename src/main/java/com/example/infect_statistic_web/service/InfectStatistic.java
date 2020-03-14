package com.example.infect_statistic_web.service;

import com.example.infect_statistic_web.model.Country;
import com.example.infect_statistic_web.model.DailyInfo;
import com.example.infect_statistic_web.model.LogList;
import com.example.infect_statistic_web.model.Province;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * InfectStatistic
 * 疫情统计程序主类
 *
 * @author 陈启元
 * @since 2020-02-19
 */
public class InfectStatistic {
    /**
     * 疫情统计程序所要用到的参数
     */
    private String inputPath;
    /**
     * 统计时会用到的对象
     */
    private LogList logList = new LogList();
    private Country country;

    /**
     * 使用命令行参数初始化InfectStatistic类（在这一步中获取各个参数）
     *
     */
    public InfectStatistic() {

        //设置resources文件夹下的logs文件夹为数据来源
        Resource resource = new ClassPathResource("logs");
        File sourceFile = null;
        try {
            sourceFile = resource.getFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        inputPath=sourceFile.getPath();

        //int inputPosition = -1;
        //int datePosition = -1;
        //int weight = 0;
        this.country = Country.getInstance();

    }

    /**
     * 从输入路径读取日志
     */
    public void readLogs() {
        logList.readLogsFromPath(inputPath);
    }

    /**
     * 返回全国页面所需的所有数据
     * @param date
     * @return 一个存有所有所需数据的List
     */
    public List<Object> getCountryInfos(LocalDate date){
        DailyInfo countryInfo=getCountryStatistic(date);
        DailyInfo countryChange=getCountryChange(date);
        ArrayList<CountryMapData> provinceInfos= getCountryMapData(date);

        List<Object> dataList=new ArrayList<>();
        dataList.add(countryInfo);
        dataList.add(countryChange);
        dataList.add(provinceInfos);
        return dataList;
    }

    public DailyInfo getCountryStatistic(LocalDate date) {
        return country.getCountryTotalInfo(date);
    }

    /**
     * 返回全国页面中地图要用的数据
     * @param date
     * @return
     */
    public ArrayList<CountryMapData> getCountryMapData(LocalDate date){
        HashMap<String,DailyInfo> infos=country.getAllProvincesInfo(date);
        ArrayList<CountryMapData> mapData=new ArrayList<>();

        for(String provinceName:Country.PROVINCES){
            DailyInfo dailyInfo=infos.get(provinceName);
            CountryMapData data=new CountryMapData(provinceName,dailyInfo.infected,dailyInfo.suspected,
                                                    dailyInfo.dead,dailyInfo.cured);
            mapData.add(data);
        }
        return mapData;
    }

    public DailyInfo getCountryChange(LocalDate date){
        return country.getCountryChange(date);
    }


    public DailyInfo getProvinceStatistic(LocalDate date, String provinceName){
        Province province=country.getProvince(provinceName);
        return province.getStatistic(date);
    }

    public DailyInfo getProvinceChange(LocalDate date,String provinceName){
        Province province=country.getProvince(provinceName);
        return province.getProvinceChange(date);
    }

    public List<Object> getChartData(String provinceName,LocalDate date){
        Province province=country.getProvince(provinceName);
        ArrayList<DailyInfo> dailyInfos=province.getAllDailyInfo();

        ArrayList<String> xAxisDate=new ArrayList<>();
        ArrayList<Integer> infected=new ArrayList<>();
        ArrayList<Integer> suspected=new ArrayList<>();
        ArrayList<Integer> dead=new ArrayList<>();
        ArrayList<Integer> cured=new ArrayList<>();

        for(DailyInfo dailyInfo:dailyInfos){
            if(dailyInfo.date.isAfter(date)) {
                break;
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M-d");

            xAxisDate.add(dailyInfo.date.format(formatter));
            infected.add(dailyInfo.infected);
            suspected.add(dailyInfo.suspected);
            dead.add(dailyInfo.dead);
            cured.add(dailyInfo.cured);
        }

        List<Object> chartData=new ArrayList<>();
        chartData.add(xAxisDate.toArray());
        chartData.add(infected.toArray());
        chartData.add(suspected.toArray());
        chartData.add(dead.toArray());
        chartData.add(cured.toArray());

        return chartData;
    }

    public List<Object> getProvinceInfos(String provinceName,LocalDate date){
        DailyInfo provinceStatistic=this.getProvinceStatistic(date,provinceName);
        DailyInfo provinceChange=this.getProvinceChange(date,provinceName);
        List<Object> chartData=this.getChartData(provinceName,date);

        List<Object> dataList=new ArrayList<>();
        dataList.add(provinceStatistic);
        dataList.add(provinceChange);
        dataList.add(chartData);
        return dataList;
    }
}
