package br.com.ibarra.clima.helpers;

import br.com.ibarra.clima.api.models.Weather;

/**
 * Created by joaoibarra on 20/01/16.
 */
public class BackgroundImage {

    public static String getImageUrl(Weather weather){
        int code = weather.getResults().getChannel().getItem().getCondition().getCode();
        switch (code){
            case 27:case 29:
                return "https://s.yimg.com/os/mit/media/m/weather/images/fallbacks/lead/cloudy_n-e618500.jpg";
            case 26:case 28:case 30:
                return "https://s.yimg.com/os/mit/media/m/weather/images/fallbacks/lead/cloudy_d-e618500.jpg";
            case 31:case 33:
                return "https://s.yimg.com/os/mit/media/m/weather/images/fallbacks/lead/clear_n-e618500.jpg";
            case 32:case 34:case 36:
                return "https://s.yimg.com/os/mit/media/m/weather/images/fallbacks/lead/clear_d-e618500.jpg";
            case 10:case 3:case 23:
                return "https://s.yimg.com/os/mit/media/m/weather/images/fallbacks/lead/rain_d-e618500.jpg";
            case 16:
                return "https://s.yimg.com/os/mit/media/m/weather/images/fallbacks/lead/snow_d-e618500.jpg";
            case 20:
                return "https://s.yimg.com/os/mit/media/m/weather/images/fallbacks/lead/foggy_d-e618500.jpg";
            default:
                return "https://s.yimg.com/os/mit/media/m/weather/images/fallbacks/lead/clear_d-e618500.jpg";
        }
    }
}
