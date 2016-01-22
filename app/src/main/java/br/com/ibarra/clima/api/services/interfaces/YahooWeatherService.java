package br.com.ibarra.clima.api.services.interfaces;

import br.com.ibarra.clima.api.models.WeatherResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by joaoibarra on 19/01/16.
 */
public interface YahooWeatherService {
    //https://query.yahooapis.com/v1/public/yql?q=select item from weather.forecast where woeid in (select woeid from geo.places(1) where text='chicago, il') and u='c'&format=json&callback=callbackFunction
    @Headers({"Accept: Application/JSON"})
    @GET("/v1/public/yql")
    Call<WeatherResult> getWeather(@Query("q") String query, @Query("format") String format);
}
