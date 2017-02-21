package com.iamtravisjsmith.sampleweatherapp.util;

import android.location.Location;

/**
 * Created by Travis on 2/20/2017
 */

public interface LocationCallback {
    
    void lacksPermission();
    void withLocation(Location location);
}
