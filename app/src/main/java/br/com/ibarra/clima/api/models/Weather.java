package br.com.ibarra.clima.api.models;

import com.google.gson.annotations.Expose;
import com.orm.SugarRecord;
import com.orm.dsl.Table;

/**
 * Created by joaoibarra on 19/01/16.
 */
public class Weather extends SugarRecord{
    private int count;
    private String created;
    private String lang;
    private Result results;

    @Expose
    private String city;

    @Expose
    private String unit;

    public Weather() {
    }

    public int getCount() {
        return count;
    }

    public String getCreated() {
        return created;
    }

    public String getLang() {
        return lang;
    }

    public Result getResults() {
        return results;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public long save() {
        this.deleteAll(Weather.class);
        Forecast.deleteAll(Forecast.class);

        getResults().getChannel().getItem().getCondition().save();
        getResults().getChannel().getItem().getGuid().save();
        getResults().getChannel().getItem().save();
        for(Forecast forecast : getResults().getChannel().getItem().getForecast()) {
            forecast.setItem(getResults().getChannel().getItem());
            forecast.save();
        }
        getResults().getChannel().save();
        getResults().save();
        return super.save();
    }
}
