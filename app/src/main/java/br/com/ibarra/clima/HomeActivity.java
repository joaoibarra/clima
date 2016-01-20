package br.com.ibarra.clima;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.RelativeLayout;

import com.squareup.picasso.Picasso;

import br.com.ibarra.clima.api.models.Weather;
import br.com.ibarra.clima.api.models.WeatherResult;
import br.com.ibarra.clima.api.services.YahooWeatherServiceImpl;
import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    @Bind(R.id.header)
    ImageView header;
    @Bind(R.id.weather_daily_list)
    RecyclerView weatherDailyList;
    @Bind(R.id.temperature)
    TextView textViewTemperature;
    @Bind(R.id.description)
    TextView textViewDescription;
    @Bind(R.id.humidity)
    TextView textViewHumidity;
    @Bind(R.id.unit)
    TextView textViewUnit;
    @Bind(R.id.image)
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getData();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void getData() {
        Call<WeatherResult> call = YahooWeatherServiceImpl.getInstance().getWeather(
                "select item from weather.forecast where woeid in (select woeid from geo.places(1) where text='campo grande, ms') and u='c'",
                "json"
        );
        call.enqueue(new Callback<WeatherResult>() {
            @Override
            public void onResponse(Response<WeatherResult> response) {
                if (response.isSuccess()) {
                    WeatherResult weather = response.body();
                    Picasso.with(HomeActivity.this)
                            .load("https://s.yimg.com/os/mit/media/m/weather/images/fallbacks/lead/cloudy_n-e618500.jpg")
                            .into(header);
                    setLayoutValues(weather.getWeather());
                   /* setLayoutValues(weatherToday);
                    getWeatherNextDays();*/
                }
            }

            @Override
            public void onFailure(Throwable t) {
                t.fillInStackTrace();
               /* onFinishProgress();
                onFinishError();*/
            }
        });
    }

    public void setLayoutValues(Weather weather) {
        /*Picasso.with(this)
                .load(Url.IMAGE + weather.getWeather().get(0).getIcon() + ".png")
                .into(image);*/
        /*textViewTemperature.setText(Util.temperatureDoubleToString(weather.getMain().getTemperature()));
        textViewUnit.setText(configuration.getUnitAbbreviation());
        textViewDescription.setText(weather.getWeather().get(0).getDescription());
        textViewHumidity.setText(weather.getMain().getHumidity() + "%");*/

        Picasso.with(this)
                .load("http://www.weather.com/sites/all/modules/custom/angularmods/app/shared/wxicon/svgz/thunderstorm.svgz")

                .into(image);
        textViewTemperature.setText(weather.getResults().getChannel().getItem().getCondition().getTemperature());
        textViewUnit.setText("C°");
        textViewDescription.setText(weather.getResults().getChannel().getItem().getCondition().getText());
        //textViewHumidity.setText(weather.getMain().getHumidity() + "%");

    }
}
