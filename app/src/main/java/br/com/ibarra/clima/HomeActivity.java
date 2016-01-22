package br.com.ibarra.clima;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import br.com.ibarra.clima.api.models.Configuration;
import br.com.ibarra.clima.api.models.Weather;
import br.com.ibarra.clima.api.models.WeatherResult;
import br.com.ibarra.clima.api.services.YahooWeatherServiceImpl;
import br.com.ibarra.clima.helpers.BackgroundImageHelper;
import br.com.ibarra.clima.ui.activities.ConfigurationActivity;
import br.com.ibarra.clima.ui.adapters.WeatherAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    @Bind(R.id.header) ImageView header;
    @Bind(R.id.weather_daily_list) RecyclerView forecastList;
    @Bind(R.id.temperature) TextView textViewTemperature;
    @Bind(R.id.description) TextView textViewDescription;
    @Bind(R.id.humidity) TextView textViewHumidity;
    @Bind(R.id.unit) TextView textViewUnit;
    @Bind(R.id.image) ImageView image;

    private LinearLayoutManager layoutManager;
    private Toolbar toolbar;
    Configuration configuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        layoutManager = new LinearLayoutManager(this);
        configuration = new Configuration(HomeActivity.this);
        forecastList.setLayoutManager(layoutManager);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ConfigurationActivity.class);
                startActivity(intent);
            }
        });
        verifyData();
    }

    public void getData() {
        Call<WeatherResult> call = YahooWeatherServiceImpl.getInstance().getWeather(
                configuration.getQuery(),
                "json"
        );
        call.enqueue(new Callback<WeatherResult>() {
            @Override
            public void onResponse(Response<WeatherResult> response) {
                if (response.isSuccess()) {
                    WeatherResult weather = response.body();
                    if (weather.getWeather().getResults().getChannel().getItem().getForecast() != null) {
                        weather.getWeather().setCity(configuration.getCity());
                        weather.getWeather().setUnit(configuration.getUnitToString());
                        weather.getWeather().save();
                        WeatherAdapter weatherAdapter = new WeatherAdapter(weather.getWeather().getResults().getChannel().getItem().getForecast());
                        forecastList.setAdapter(weatherAdapter);
                        setLayoutValues(weather.getWeather());
                       /* setLayoutValues(weatherToday);
                        getWeatherNextDays();*/
                    } else {
                        Toast.makeText(HomeActivity.this, "Erro ao obter dados", Toast.LENGTH_LONG).show();
                    }
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

    private void setLayoutValues(){
        getSupportActionBar().setTitle(configuration.getCity());
        getData();
    }

    private void setLayoutValues(Weather weather) {
        getSupportActionBar().setTitle(configuration.getCity());
        Picasso.with(HomeActivity.this)
                .load(BackgroundImageHelper.getImageUrl(weather))
                .into(header);
        textViewTemperature.setText(weather.getResults().getChannel().getItem().getCondition().getTemperature());
        textViewUnit.setText(configuration.getUnitAbbreviation());
        textViewDescription.setText(weather.getResults().getChannel().getItem().getCondition().getText());
    }

    public void verifyData(){
        Weather weather = Weather.first(Weather.class);
        if(weather!=null && weather.getCity().equalsIgnoreCase(configuration.getCity()) && weather.getUnit().equalsIgnoreCase(configuration.getUnitToString())){
            WeatherAdapter weatherAdapter = new WeatherAdapter(weather.getResults().getChannel().getItem().getForecast());
            forecastList.setAdapter(weatherAdapter);
            setLayoutValues(weather);
        }else{
            setLayoutValues();
        }
    }
}
