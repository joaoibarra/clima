package br.com.ibarra.clima.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.ibarra.clima.R;
import br.com.ibarra.clima.api.models.Forecast;
import br.com.ibarra.clima.ui.holders.WeatherHolder;

/**
 * Created by joaoibarra on 20/01/16.
 */
public class WeatherAdapter extends RecyclerView.Adapter<WeatherHolder>{
    private static List<Forecast> forecastList;

    public WeatherAdapter(List<Forecast> forecastList) {
        this.forecastList = forecastList;
    }

    @Override
    public WeatherHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.weather_holder, parent, false);
        return new WeatherHolder(v);
    }

    @Override
    public void onBindViewHolder(WeatherHolder holder, int position) {
        Forecast forecast = this.forecastList.get(position);
        /*Picasso.with(holder.getImage().getContext())
                .load(Url.IMAGE + weatherDailyItem.getWeather().get(0).getIcon() + ".png")
                .into(holder.getImage());*/

       /* holder.getTextViewDate().setText(Util.dateToString(weatherDailyItem.getDt()));

        holder.getTextViewMaxTemperature().setText(Util.temperatureDoubleToString(weatherDailyItem.getDayTemperature().getMaxDailyTemperature()));
        holder.getTextViewMinTemperature().setText(Util.temperatureDoubleToString(weatherDailyItem.getDayTemperature().getMinDailyTemperature()));
        holder.setWeatherDailyItem(weatherDailyItem);*/

        holder.getTextViewDate().setText(forecast.getDay());

        holder.getTextViewMaxTemperature().setText(forecast.getHigh());
        holder.getTextViewMinTemperature().setText(forecast.getLow());
        holder.setForecast(forecast);
    }

    @Override
    public int getItemCount() {
        return forecastList.size();
    }

}
