package com.example.infect_statistic_web.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.HashMap;

public class Log {
    LocalDate date;
    File logFile;
    Country country;
    /**
     * 记录该日志
     */
    HashMap<String, DailyInfo> dailyInfos;

    public Log(String filePath) {
        logFile = new File(filePath);
        String logName = logFile.getName();
        date = LocalDate.parse(logName.substring(0, 10));

        country = Country.getInstance();
        dailyInfos = new HashMap<>();
        for (String provinceName : Country.PROVINCES) {
            DailyInfo dailyInfo = new DailyInfo(date);
            dailyInfos.put(provinceName, dailyInfo);
        }
//        date=LocalDate.parse(filePath.substring())
    }

    public void analyzeLog() {
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(logFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis, "UTF-8"));

            String line = null;
            while ((line = br.readLine()) != null) {
                //注释不处理
                if (line.contains("//")) {
                    continue;
                }
                //下面是日志中八种情况的处理
                if (line.contains("新增") && line.contains("感染")) {
                    addInfected(line);
                    continue;
                }
                if (line.contains("新增") && line.contains("疑似")) {
                    addSuspected(line);
                    continue;
                }
                if (line.contains("流入") && line.contains("感染")) {
                    flowInfected(line);
                    continue;
                }
                if (line.contains("流入") && line.contains("疑似")) {
                    flowSuspected(line);
                    continue;
                }
                if (line.contains("死亡")) {
                    addDead(line);
                    continue;
                }
                if (line.contains("治愈")) {
                    addCured(line);
                    continue;
                }
                if (line.contains("确诊")) {
                    suspectedToInfected(line);
                    continue;
                }
                if (line.contains("排除")) {
                    suspectedToHealthy(line);
                    continue;
                }
            }

            br.close();
            fis.close();
        } catch (Exception e) {
            System.out.println("读取日志文件" + logFile.getName() + "时出现错误！");
            e.printStackTrace();
        }

        country.logData(dailyInfos);


    }

    private void addInfected(String line) {
        String[] words = line.split(" ");
        String provinceName = words[0];
        String numberString = words[3];
        int number = Integer.parseInt(numberString.substring(0, numberString.length() - 1));

        dailyInfos.get(provinceName).changeInfected(number);
        country.getProvince(provinceName).hasOccurred = true;
    }

    private void addSuspected(String line) {
        String[] words = line.split(" ");
        String provinceName = words[0];
        String numberString = words[3];
        int number = Integer.parseInt(numberString.substring(0, numberString.length() - 1));

        dailyInfos.get(provinceName).changeSuspected(number);
        country.getProvince(provinceName).hasOccurred = true;
    }

    private void flowInfected(String line) {
        String[] words = line.split(" ");
        String province1Name = words[0];
        String province2Name = words[3];
        String numberString = words[4];
        int number = Integer.parseInt(numberString.substring(0, numberString.length() - 1));

        dailyInfos.get(province1Name).changeInfected(-number);
        dailyInfos.get(province2Name).changeInfected(number);
        country.getProvince(province1Name).hasOccurred = true;
        country.getProvince(province2Name).hasOccurred = true;
    }

    private void flowSuspected(String line) {
        String[] words = line.split(" ");
        String province1Name = words[0];
        String province2Name = words[3];
        String numberString = words[4];
        int number = Integer.parseInt(numberString.substring(0, numberString.length() - 1));

        dailyInfos.get(province1Name).changeSuspected(-number);
        dailyInfos.get(province2Name).changeSuspected(number);
        country.getProvince(province1Name).hasOccurred = true;
        country.getProvince(province2Name).hasOccurred = true;
    }

    private void addDead(String line) {
        String[] words = line.split(" ");
        String provinceName = words[0];
        String numberString = words[2];
        int number = Integer.parseInt(numberString.substring(0, numberString.length() - 1));

        dailyInfos.get(provinceName).changeDead(number);
        dailyInfos.get(provinceName).changeInfected(-number);
        country.getProvince(provinceName).hasOccurred = true;
    }

    private void addCured(String line) {
        String[] words = line.split(" ");
        String provinceName = words[0];
        String numberString = words[2];
        int number = Integer.parseInt(numberString.substring(0, numberString.length() - 1));

        dailyInfos.get(provinceName).changeInfected(-number);
        dailyInfos.get(provinceName).changeCured(number);
        country.getProvince(provinceName).hasOccurred = true;
    }

    private void suspectedToInfected(String line) {
        String[] words = line.split(" ");
        String provinceName = words[0];
        String numberString = words[3];
        int number = Integer.parseInt(numberString.substring(0, numberString.length() - 1));

        dailyInfos.get(provinceName).changeInfected(number);
        dailyInfos.get(provinceName).changeSuspected(-number);
        country.getProvince(provinceName).hasOccurred = true;
    }

    private void suspectedToHealthy(String line) {
        String[] words = line.split(" ");
        String provinceName = words[0];
        String numberString = words[3];
        int number = Integer.parseInt(numberString.substring(0, numberString.length() - 1));

        dailyInfos.get(provinceName).changeSuspected(-number);
        country.getProvince(provinceName).hasOccurred = true;
    }

}
