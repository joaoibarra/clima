package br.com.ibarra.clima.api.models;

import com.google.gson.annotations.SerializedName;
import com.yahoo.squidb.annotations.PrimaryKey;
import com.yahoo.squidb.annotations.TableModelSpec;

import java.util.List;

/**
 * Created by joaoibarra on 19/01/16.
 */
@TableModelSpec(className="Item", tableName="items", tableConstraint = "FOREIGN KEY(channelId) REFERENCES channels(_id)")
public class Item {
    @PrimaryKey
    long id;
    long channelId;
    private String title;
    @SerializedName("lat")
    private String latitude;
    @SerializedName("long")
    private String longitude;
    private String link;
    private String pubDate;
    private ConditionSpec condition;
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

    public ConditionSpec getCondition() {
        return condition;
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
