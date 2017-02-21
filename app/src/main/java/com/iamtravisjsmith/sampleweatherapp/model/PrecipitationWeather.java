package com.iamtravisjsmith.sampleweatherapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Travis on 2/19/2017
 */

public class PrecipitationWeather {

    @SerializedName("3h") private double pastThreeHours;

    public PrecipitationWeather(double pastThreeHours) {
        this.pastThreeHours = pastThreeHours;
    }

    public double getPastThreeHours() {
        return pastThreeHours;
    }
}
