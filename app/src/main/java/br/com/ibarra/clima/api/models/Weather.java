package br.com.ibarra.clima.api.models;

import com.yahoo.squidb.annotations.PrimaryKey;
import com.yahoo.squidb.annotations.TableModelSpec;

/**
 * Created by joaoibarra on 19/01/16.
 */
@TableModelSpec(className="WeatherSpec", tableName="weathers")
public class Weather {
    @PrimaryKey
    long id;
    private int count;
    private String created;
    private String lang;
    private Result results;

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
}
