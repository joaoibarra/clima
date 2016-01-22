package br.com.ibarra.clima.helpers;

import br.com.ibarra.clima.api.models.Weather;

/**
 * Created by joaoibarra on 1/22/16.
 */
public class UrlHelper {
    public static String API = "https://query.yahooapis.com";
    public static String IMAGE = "http://www.locationcarolina.com/wp-content/plugins/wp-simpleweather/images/yahoo-weather/";

    public static String getImageUrl(String code){
        return IMAGE + code + "d.png";
    }
    public static String getImageUrl(Weather weather){
        return IMAGE + weather.getResults().getChannel().getItem().getCondition().getCode() + "d.png";
    }
}
