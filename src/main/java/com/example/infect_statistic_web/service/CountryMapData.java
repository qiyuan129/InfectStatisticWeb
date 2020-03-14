package com.example.infect_statistic_web.service;

public class CountryMapData{
    public String name;
    public int infected;
    public int suspected;
    public int dead;
    public int cured;

    public CountryMapData(String name,int infected,int suspected,int dead,int cured){
        this.name=name;
        this.infected=infected;
        this.suspected=suspected;
        this.dead=dead;
        this.cured=cured;
    }
}
