package br.com.ibarra.clima.api.models;

import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;
import com.orm.dsl.Table;

import java.util.List;

/**
 * Created by joaoibarra on 19/01/16.
 */
public class Item extends SugarRecord {
    private String title;
    @SerializedName("lat")
    private String latitude;
    @SerializedName("long")
    private String longitude;
    private String link;
    private String pubDate;
    private Condition condition;
    private String description;
    private List<Forecast> forecast;
    private Guid guid;

    public String getTitle() {
        return title;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLink() {
        return link;
    }

    public String getPubDate() {
        return pubDate;
    }

    public Condition getCondition() {
        return condition;
    }

    public String getDescription() {
        return description;
    }

    public List<Forecast> getForecast() {
        if(forecast==null){
            forecast = Forecast.find(Forecast.class, "item = ?", new String(Long.toString(getId())));
        }
        return forecast;
    }

    public Guid getGuid() {
        return guid;
    }

    @Override
    public long save() {
        this.deleteAll(Guid.class);
        return super.save();
    }

}
