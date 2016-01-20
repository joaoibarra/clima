package br.com.ibarra.clima.api.models;

/**
 * Created by joaoibarra on 19/01/16.
 */
public class Weather {
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
