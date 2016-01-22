package br.com.ibarra.clima.api.models;

import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;
import com.orm.dsl.Table;

import java.util.List;

/**
 * Created by joaoibarra on 19/01/16.
 */
public class Condition extends SugarRecord {
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

    @Override
    public long save() {
        this.deleteAll(Condition.class);
        return super.save();
    }
}
