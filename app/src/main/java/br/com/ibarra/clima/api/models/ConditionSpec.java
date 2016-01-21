package br.com.ibarra.clima.api.models;

import com.google.gson.annotations.SerializedName;
import com.yahoo.squidb.annotations.PrimaryKey;
import com.yahoo.squidb.annotations.TableModelSpec;

import java.util.List;

/**
 * Created by joaoibarra on 19/01/16.
 */
@TableModelSpec(className="Condition", tableName="conditions", tableConstraint = "FOREIGN KEY(itemId) REFERENCES items(_id)")
public class ConditionSpec {
    @PrimaryKey
    long id;
    long itemId;
    private int code;
    private String date;
    @SerializedName("temp")
    private String temperature;
    private String text;

    public int getCode() {
        return code;
    }

    public String getDate() {
        return date;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getText() {
        return text;
    }
}
