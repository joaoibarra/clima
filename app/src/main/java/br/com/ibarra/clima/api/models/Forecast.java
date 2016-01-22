package br.com.ibarra.clima.api.models;

import com.yahoo.squidb.annotations.PrimaryKey;
import com.yahoo.squidb.annotations.TableModelSpec;

/**
 * Created by joaoibarra on 19/01/16.
 */
@TableModelSpec(className="ForecastSpec", tableName="forecasts", tableConstraint = "FOREIGN KEY(itemId) REFERENCES items(_id)")
public class Forecast {
    @PrimaryKey
    long id;
    long itemId;
    private String code;
    private String date;
    private String day;
    private String high;
    private String low;
    private String text;

    public String getCode() {
        return code;
    }

    public String getDate() {
        return date;
    }

    public String getDay() {
        return day;
    }

    public String getHigh() {
        return high;
    }

    public String getLow() {
        return low;
    }

    public String getText() {
        return text;
    }
}
