package com.example.infect_statistic_web.service;

public class CountryMapData{
    public String name;
    public int value;
    public int suspected;
    public int dead;
    public int cured;

    public CountryMapData(String name, int value, int suspected, int dead, int cured){
        this.name=name;
        this.value = value;
        this.suspected=suspected;
        this.dead=dead;
        this.cured=cured;
    }
}
