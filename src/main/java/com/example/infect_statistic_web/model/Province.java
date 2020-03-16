package com.example.infect_statistic_web.model;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * 代表各省的类
 */
public class Province {
    String name;
    ArrayList<DailyInfo> dailyInfos;
    /**
     * 标记是否在日志中出现过
     */
    public boolean hasOccurred;
    //DailyInfo totalInfo = null;


    Province(String name) {
        this.name = name;
        dailyInfos = new ArrayList<>();
        hasOccurred = false;
    }

    public void addDailyInfo(DailyInfo dailyInfo) {
        dailyInfos.add(dailyInfo);
    }

    /**
     * 返回当前省份 截止到日期endDate的疫情统计数据
     * @param endDate
     * @return
     */
    public DailyInfo getStatistic(LocalDate endDate) {
        DailyInfo totalInfo = new DailyInfo(endDate);

        for (DailyInfo info : dailyInfos) {
            //只处理指定日期当天以及之前的日志
            if (info.getDate().isBefore(endDate) || info.getDate().isEqual(endDate)) {
                totalInfo.add(info);
            }
        }
        return totalInfo;
    }

    /**
     * 返回当前省份 日期date的疫情数据相较于前一天的变化
     * @param date
     * @return
     */
    public DailyInfo getProvinceChange(LocalDate date){
        for(DailyInfo info:dailyInfos){
            if(info.date.isEqual(date)){
                return info;
            }
        }
        return null;
    }

    /**
     * 返回当前省份记录的所有疫情数据
     * @return
     */
    public ArrayList<DailyInfo> getAllDailyInfo(){
        return dailyInfos;
    }
    public void deleteInfos(){
        dailyInfos.clear();
    }
}
