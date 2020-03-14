package com.example.infect_statistic_web.model;

import java.time.LocalDate;

/**
 * 记录某一天的各项人数（总数或变化）
 */
public class DailyInfo {
    public LocalDate date;
    public static String[] ALL_TYPES = {"ip", "sp", "cure", "dead"};
    public int infected;
    public int suspected;
    public int dead;
    public int cured;

    public DailyInfo(LocalDate date) {
        this.date = date;
        infected = 0;
        suspected = 0;
        dead = 0;
        cured = 0;
    }

    DailyInfo(LocalDate date, int ip, int sp, int dead, int cured) {
        this.date = date;
        infected = ip;
        suspected = sp;
        this.dead = dead;
        this.cured = cured;
    }

    public void changeInfected(int change) {
        infected += change;
    }

    public void changeSuspected(int change) {
        suspected += change;
    }

    public void changeDead(int change) {
        dead += change;
    }

    public void changeCured(int change) {
        cured += change;
    }

    public DailyInfo add(DailyInfo dailyInfo2) {
        //两份信息相加，以较晚的为相加后的日期
        if (this.date.isBefore(dailyInfo2.getDate())) {
            this.date = dailyInfo2.getDate();
        }

        this.infected += dailyInfo2.infected;
        this.suspected += dailyInfo2.suspected;
        this.dead += dailyInfo2.dead;
        this.cured += dailyInfo2.cured;

        return this;

    }

    public LocalDate getDate() {
        return this.date;
    }


    public String toString(String[] types) {
        StringBuilder stringBuilder = new StringBuilder();

        for (String type : types) {
            switch (type) {
                case "ip":
                    stringBuilder.append("感染患者" + infected + "人 ");
                    break;
                case "sp":
                    stringBuilder.append("疑似患者" + suspected + "人 ");
                    break;
                case "cure":
                    stringBuilder.append("治愈" + cured + "人 ");
                    break;
                case "dead":
                    stringBuilder.append("死亡" + dead + "人 ");
                    break;
                default:
                    System.out.println("\n输出时出现错误，类型参数只能指定为ip/sp/cure/dead");
            }
        }
        return stringBuilder.toString();
    }
}
