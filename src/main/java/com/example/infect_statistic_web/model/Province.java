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
    DailyInfo totalInfo = null;


    Province(String name) {
        this.name = name;
        dailyInfos = new ArrayList<>();
        hasOccurred = false;
    }

    public void addDailyInfo(DailyInfo dailyInfo) {
        dailyInfos.add(dailyInfo);
    }

    public DailyInfo getStatistic(LocalDate endDate) {
        //未进行统计就计算一遍
        if (totalInfo == null) {
            DailyInfo totalInfo = new DailyInfo(endDate);

            for (DailyInfo info : dailyInfos) {
                //只处理指定日期当天以及之前的日志
                if (info.getDate().isBefore(endDate) || info.getDate().isEqual(endDate)) {
                    totalInfo.add(info);
                }
            }

            this.totalInfo = totalInfo;
            return totalInfo;
        } else {
            return this.totalInfo;
        }
    }

    public DailyInfo getDailyInfoFromDate(LocalDate date){
        for(DailyInfo info:dailyInfos){
            if(info.date.isEqual(date)){
                return info;
            }
        }
        return null;
    }
}
