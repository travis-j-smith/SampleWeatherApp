package com.iamtravisjsmith.sampleweatherapp.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.iamtravisjsmith.sampleweatherapp.R;
import com.iamtravisjsmith.sampleweatherapp.model.WeatherReading;
import com.iamtravisjsmith.sampleweatherapp.util.LocationCallback;
import com.iamtravisjsmith.sampleweatherapp.util.LocationTracker;
import com.iamtravisjsmith.sampleweatherapp.util.WeatherApiManager;
import com.iamtravisjsmith.sampleweatherapp.util.WeatherCallback;

/**
 * Created by Travis on 2/14/2017
 */

public class LocationsActivity extends AppCompatActivity {

    // Arbitrary request code number
    private static final int PERMISSION_REQUEST_CODE = 10;

    private WeatherAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final int numberOfColumns = 2;
        GridLayoutManager layoutManager = new GridLayoutManager(this, numberOfColumns);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (position) {
                    case 0:
                        return numberOfColumns;
                    default:
                        return 1;
                }
            }
        });

        adapter = new WeatherAdapter();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        checkLocationPermissions();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_REQUEST_CODE) {
            checkLocationPermissions();
        }
    }

    private void checkLocationPermissions() {
        boolean hasGrantedFinePermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;

        if (hasGrantedFinePermission) {
            startTrackingLocation();
        } else {
            requestLocationPermissions();
        }
    }

    private void requestLocationPermissions() {
        String[] permissions = new String[] {
                Manifest.permission.ACCESS_FINE_LOCATION
        };
        ActivityCompat.requestPermissions(this, permissions, PERMISSION_REQUEST_CODE);
    }

    private void startTrackingLocation() {
        LocationTracker.getInstance().startListeningForCurrentLocation(new LocationCallback() {
            @Override
            public void lacksPermission() {
                requestLocationPermissions();
            }

            @Override
            public void withLocation(Location location) {
                fetchWeather(location);
            }
        });
    }

    private void fetchWeather(Location location) {
        WeatherApiManager.getInstance().fetchWeather(location.getLatitude(), location.getLongitude(), new WeatherCallback() {
            @Override
            public void withWeather(WeatherReading weatherReading, Throwable error) {
                adapter.update(weatherReading);
            }
        });
    }
}
