package br.com.ibarra.clima.api.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by joaoibarra on 19/01/16.
 */
public class Condition {
    private String code;
    private String date;
    @SerializedName("temp")
    private String temperature;
    private String description;
    private List<Forecast> forecast;
    private Guid guid;

    public String getCode() {
        return code;
    }

    public String getDate() {
        return date;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getDescription() {
        return description;
    }

    public List<Forecast> getForecast() {
        return forecast;
    }

    public Guid getGuid() {
        return guid;
    }
}
