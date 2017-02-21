package com.iamtravisjsmith.sampleweatherapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Travis on 2/17/2017
 */

public class Coordinate {

    @SerializedName("lat") private double latitude;
    @SerializedName("lon") private double longitude;

    public Coordinate(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
