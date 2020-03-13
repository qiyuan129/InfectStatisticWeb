package com.example.infect_statistic_web.model;

import java.time.LocalDate;
import java.util.HashMap;

/**
 * 存有各省疫情信息的全国类
 * 使用了单例模式，该类的唯一实例由该类的getInstance()方法获得
 */
public class Country {
    HashMap<String, Province> provincesMap;
    public static String[] PROVINCES = {"安徽", "北京", "重庆", "福建", "甘肃", "广东", "广西",
            "贵州", "海南", "河北", "河南", "黑龙江", "湖北", "湖南", "吉林", "江苏", "江西", "辽宁",
            "内蒙古", "宁夏", "青海", "山东", "山西", "陕西", "上海", "四川", "天津", "西藏", "新疆",
            "云南", "浙江"};
    //HashMap<String, DailyInfo> totalStatistics = null;
    private static final Country country = new Country();

    private Country() {
        provincesMap = new HashMap<>();

        //把代表各个省份的Province对象加入map中方便存取
        for (String provinceName : PROVINCES) {
            Province province = new Province(provinceName);
            provincesMap.put(provinceName, province);
        }
    }

    /**
     * 获取Country类的唯一实例
     *
     * @return Country类的唯一实例
     */
    public static Country getInstance() {
        return country;
    }

    /**
     * @param name 省份名称
     * @return 对应的Province对象
     */
    public Province getProvince(String name) {
        return provincesMap.get(name);
    }

    /**
     * 录入全国各省份的某一天中的疫情信息
     *
     * @param infosFromLog 由Log获取的一天中的各省疫情信息
     */
    public void logData(HashMap<String, DailyInfo> infosFromLog) {
        for (String provinceName : PROVINCES) {
            Province province = provincesMap.get(provinceName);
            DailyInfo info = infosFromLog.get(provinceName);
            province.addDailyInfo(info);
        }
    }

    /**
     * 获得全国截止到指定日期的疫情情况
     *
     * @param endDate 要指定的日期
     * @return 全国截止到endDate这一天的疫情情况
     */
    public DailyInfo getCountryTotalInfo(LocalDate endDate) {
        DailyInfo countryTotalInfo = new DailyInfo(endDate);
        HashMap<String, DailyInfo> allProvincesInfo = this.getAllProvincesInfo(endDate);

        for (String provinceName : Country.PROVINCES) {
            DailyInfo provinceInfo = allProvincesInfo.get(provinceName);
            countryTotalInfo.add(provinceInfo);
        }

        return countryTotalInfo;
    }


    /**
     * 使用各个省份的每日感染数据，计算出各省截止到指定日期的总疫情情况
     *
     * @param endDate 要指定的日期
     * @return 各省截止到指定日期的疫情情况
     */
    public HashMap<String, DailyInfo> getAllProvincesInfo(LocalDate endDate) {
        HashMap<String, DailyInfo> totalInfos = new HashMap<>();

        for (String provinceName : Country.PROVINCES) {
            Province province = provincesMap.get(provinceName);
            DailyInfo provinceStatistic = province.getStatistic(endDate);
            totalInfos.put(provinceName, provinceStatistic);
        }

        return totalInfos;
    }

    public DailyInfo getCountryChange(LocalDate date){
        DailyInfo countryChange=new DailyInfo(date);

        for(String provinceName:Country.PROVINCES){
            Province province=this.provincesMap.get(provinceName);
            DailyInfo provinceChange=province.getProvinceChange(date);
            countryChange.add(provinceChange);
        }

        return countryChange;
    }

}
