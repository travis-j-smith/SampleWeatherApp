package com.iamtravisjsmith.sampleweatherapp.util;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.iamtravisjsmith.sampleweatherapp.model.WeatherReading;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Travis on 2/14/2017
 */

public class WeatherApiManager {

    private static String apiKey;
    private static WeatherApiManager instance;
    public static WeatherApiManager getInstance() {
        if (instance == null) {
            instance = new WeatherApiManager();
        }
        return instance;
    }

    public static void init(String apiKey) {
        WeatherApiManager.apiKey = apiKey;
    }

    private WeatherApiService weatherApiService;

    protected WeatherApiManager() {

        if (apiKey == null) {
            throw new IllegalStateException("You must initialize the WeatherApiService with your API key");
        }

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        HttpUrl url = request.url().newBuilder()
                                .addQueryParameter("units", "imperial")
                                .addQueryParameter("APPID", apiKey)
                                .build();
                        request = request.newBuilder().url(url).build();
                        return chain.proceed(request);
                    }
                })
                .build();

        weatherApiService = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .client(client)
                .build()
                .create(WeatherApiService.class);
    }

    public void fetchWeather(double latitude, double longitude, @NonNull final WeatherCallback callback) {

        weatherApiService.getByGeographicCoordinates(latitude, longitude).enqueue(new Callback<WeatherReading>() {
            @Override
            public void onResponse(Call<WeatherReading> call, retrofit2.Response<WeatherReading> response) {
                callback.withWeather(response.body(), null);
            }

            @Override
            public void onFailure(Call<WeatherReading> call, Throwable t) {
                callback.withWeather(null, t);
            }
        });
    }
}
