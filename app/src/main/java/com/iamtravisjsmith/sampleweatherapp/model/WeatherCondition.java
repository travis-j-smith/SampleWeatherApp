package com.iamtravisjsmith.sampleweatherapp.model;

/**
 * Created by Travis on 2/17/2017
 */

public class WeatherCondition {

    private int id;
    private String main;
    private String description;
    private String icon;

    public WeatherCondition(String description, String icon, int id, String main) {
        this.description = description;
        this.icon = icon;
        this.id = id;
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }

    public int getId() {
        return id;
    }

    public String getMain() {
        return main;
    }
}
