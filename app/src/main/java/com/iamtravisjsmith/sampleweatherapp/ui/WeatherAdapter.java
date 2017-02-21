package com.iamtravisjsmith.sampleweatherapp.ui;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iamtravisjsmith.sampleweatherapp.R;
import com.iamtravisjsmith.sampleweatherapp.model.WeatherReading;

/**
 * Created by Travis on 2/19/2017
 */

public class WeatherAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int HEADER_TYPE = 5;
    private static final int CONTENT_TYPE = 6;

    private WeatherReading weatherReading;

    public WeatherAdapter() {
        this(null);
    }

    public WeatherAdapter(@Nullable WeatherReading weatherReading) {
        this.weatherReading = weatherReading;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case HEADER_TYPE: {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_weather_header, parent, false);
                return new WeatherHeaderViewHolder(v);
            }
            default: {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_weather_content, parent, false);
                return new WeatherContentViewHolder(v);
            }
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Context context = holder.itemView.getContext();

        if (holder instanceof WeatherHeaderViewHolder) {
            WeatherHeaderViewHolder vh = (WeatherHeaderViewHolder) holder;

            double temperature = weatherReading.getMainWeather().getTemperature();
            String temperatureString = context.getString(R.string.temperature_fahrenheit_formatted, temperature);

            String name = weatherReading.getName();
            if (name == null || name.isEmpty()) {
                name = context.getString(R.string.unknown);
            }
            vh.getTitleTextView().setText(name);
            vh.getSubtitleTextView().setText(temperatureString);
        } else if (holder instanceof WeatherContentViewHolder) {
            WeatherContentViewHolder vh = (WeatherContentViewHolder) holder;

            switch (position) {
                case 1: {
                    vh.getLayout().setBackgroundColor(ContextCompat.getColor(context, R.color.material_orange_500));
                    vh.getTitleTextView().setText(context.getString(R.string.clouds));

                    double cloudiness = weatherReading.getCloudWeather().getPercent();
                    vh.getSubtitleTextView().setText(context.getString(R.string.percent_formatted, cloudiness));
                    break;
                }
                case 2: {
                    vh.getLayout().setBackgroundColor(ContextCompat.getColor(context, R.color.material_green_500));
                    vh.getTitleTextView().setText(context.getString(R.string.wind));

                    double windSpeed = weatherReading.getWindWeather().getSpeed();
                    vh.getSubtitleTextView().setText(context.getString(R.string.speed_miles_per_hour_formatted, windSpeed));
                    break;
                }
                case 3: {
                    vh.getLayout().setBackgroundColor(ContextCompat.getColor(context, R.color.material_blue_500));
                    vh.getTitleTextView().setText(context.getString(R.string.rain));

                    if (weatherReading.getRainWeather() == null) {
                        vh.getSubtitleTextView().setText(context.getString(R.string.none));
                    } else {
                        double rainAmount = weatherReading.getRainWeather().getPastThreeHours();
                        vh.getSubtitleTextView().setText(context.getString(R.string.height_inches_formatted, rainAmount));
                    }
                    break;
                }
                case 4: {
                    vh.getLayout().setBackgroundColor(ContextCompat.getColor(context, R.color.material_purple_500));
                    vh.getTitleTextView().setText(context.getString(R.string.snow));

                    if (weatherReading.getSnowWeather() == null) {
                        vh.getSubtitleTextView().setText(context.getString(R.string.none));
                    } else {
                        double snowAmount = weatherReading.getSnowWeather().getPastThreeHours();
                        vh.getSubtitleTextView().setText(context.getString(R.string.height_inches_formatted, snowAmount));
                    }
                    break;
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        if (weatherReading == null) {
            return 0;
        }

        return 5;
    }

    @Override
    public int getItemViewType(int position) {

        switch (position) {
            case 0:
                return HEADER_TYPE;
            default:
                return CONTENT_TYPE;
        }
    }

    public void update(WeatherReading weatherReading) {
        this.weatherReading = weatherReading;
        notifyDataSetChanged();
    }

    static class WeatherHeaderViewHolder extends RecyclerView.ViewHolder {

        private TextView titleTextView;
        private TextView subtitleTextView;

        public WeatherHeaderViewHolder(View itemView) {
            super(itemView);

            titleTextView = (TextView) itemView.findViewById(R.id.weather_header_title_text_view);
            subtitleTextView = (TextView) itemView.findViewById(R.id.weather_header_subtitle_text_view);
        }

        public TextView getTitleTextView() {
            return titleTextView;
        }

        public TextView getSubtitleTextView() {
            return subtitleTextView;
        }
    }

    static class WeatherContentViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout layout;
        private TextView titleTextView;
        private TextView subtitleTextView;

        public WeatherContentViewHolder(View itemView) {
            super(itemView);

            layout = (LinearLayout) itemView.findViewById(R.id.weather_content_layout);
            titleTextView = (TextView) itemView.findViewById(R.id.weather_content_title_text_view);
            subtitleTextView = (TextView) itemView.findViewById(R.id.weather_content_subtitle_text_view);
        }

        public LinearLayout getLayout() {
            return layout;
        }

        public TextView getTitleTextView() {
            return titleTextView;
        }

        public TextView getSubtitleTextView() {
            return subtitleTextView;
        }
    }
}
