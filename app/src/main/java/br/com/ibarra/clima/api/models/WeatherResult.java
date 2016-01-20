package br.com.ibarra.clima.api.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by joaoibarra on 19/01/16.
 */
public class WeatherResult {
    @SerializedName("query")
    private Weather weather;

    public Weather getWeather() {
        return weather;
    }
}
