package com.iamtravisjsmith.sampleweatherapp.util;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

/**
 * Created by Travis on 2/20/2017
 */

public class LocationTracker implements LocationListener{

    private static final long MINIMUM_TIME_MILLISECONDS = 1000L * 5L; // 5 seconds
    private static final long MINIMUM_DISTANCE_METERS = 1000L; // 1 kilometer

    private static LocationTracker instance;
    public static void init(Context context) {
        instance = new LocationTracker(context);
    }
    public static LocationTracker getInstance() {
        if (instance == null) {
            throw new IllegalStateException("You must initialize the LocationTracker with a Context");
        }
        return instance;
    }

    private LocationManager locationManager;
    private LocationCallback locationCallback;

    private LocationTracker(Context context) {
        this.locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }

    public void startListeningForCurrentLocation(LocationCallback locationCallback) {
        this.locationCallback = locationCallback;

        boolean isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        boolean isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if (isGpsEnabled) {
            trackLocation(LocationManager.GPS_PROVIDER);
        } else if (isNetworkEnabled) {
            trackLocation(LocationManager.NETWORK_PROVIDER);
        } else {
            // TODO: Show error
        }
    }

    private void trackLocation(String provider) {
        try {
            locationManager.requestLocationUpdates(
                    provider,
                    MINIMUM_TIME_MILLISECONDS,
                    MINIMUM_DISTANCE_METERS,
                    this);
        } catch (SecurityException e) {
            if (locationCallback != null) {
                locationCallback.lacksPermission();
            }
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        if (locationCallback != null) {
            locationCallback.withLocation(location);
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
