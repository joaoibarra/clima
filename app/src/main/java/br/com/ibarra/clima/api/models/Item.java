package br.com.ibarra.clima.api.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by joaoibarra on 19/01/16.
 */
public class Item {
    private String title;
    @SerializedName("lat")
    private String latitude;
    @SerializedName("long")
    private String longitude;
    private String link;
    private String pubDate;
    private Condition condition;

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
}
