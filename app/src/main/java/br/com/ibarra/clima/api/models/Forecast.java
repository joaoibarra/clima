package br.com.ibarra.clima.api.models;

import com.google.gson.annotations.Expose;
import com.orm.SugarRecord;
import com.orm.dsl.Table;

/**
 * Created by joaoibarra on 19/01/16.
 */
public class Forecast extends SugarRecord {
    private String code;
    private String date;
    private String day;
    private String high;
    private String low;
    private String text;
    @Expose
    Item item;

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

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public long save() {
        return super.save();
    }
}
