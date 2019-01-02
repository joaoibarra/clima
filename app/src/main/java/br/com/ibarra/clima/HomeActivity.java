package br.com.ibarra.clima;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import br.com.ibarra.clima.api.models.Configuration;
import br.com.ibarra.clima.api.models.Weather;
import br.com.ibarra.clima.api.models.WeatherResult;
import br.com.ibarra.clima.api.services.YahooWeatherServiceImpl;
import br.com.ibarra.clima.helpers.BackgroundImageHelper;
import br.com.ibarra.clima.helpers.UrlHelper;
import br.com.ibarra.clima.ui.activities.BaseActivity;
import br.com.ibarra.clima.ui.activities.ConfigurationActivity;
import br.com.ibarra.clima.ui.adapters.WeatherAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements BaseActivity {
    @BindView(R.id.progressbar)
    LinearLayout progressbarLayout;
    @BindView(R.id.error)
    RelativeLayout errorLayout;
    @BindView(R.id.content)
    ConstraintLayout contentLayout;
    @BindView(R.id.header)
    ImageView header;
    @BindView(R.id.weather_daily_list)
    RecyclerView forecastList;
    @BindView(R.id.temperature)
    TextView textViewTemperature;
    @BindView(R.id.description)
    TextView textViewDescription;
    @BindView(R.id.unit)
    TextView textViewUnit;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.reload)
    Button reloadButton;

    private LinearLayoutManager layoutManager;
    Configuration configuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        layoutManager = new LinearLayoutManager(this);
        configuration = new Configuration(HomeActivity.this);
        forecastList.setLayoutManager(layoutManager);
        setSupportActionBar(toolbar);
        verifyData();
    }

    @OnClick(R.id.reload)
    public void reload() {
        verifyData();
    }

    public void getData() {
        onLoadProgress();
        Call<WeatherResult> call = YahooWeatherServiceImpl.getInstance().getWeather(
                configuration.getQuery(),
                "json"
        );
        call.enqueue(new Callback<WeatherResult>() {
            @Override
            public void onResponse(Call<WeatherResult> call, Response<WeatherResult> response) {
                if (response.isSuccessful()) {
                    WeatherResult weather = response.body();
                    if (weather.getWeather().getResults().getChannel().getItem().getForecast() != null) {
                        weather.getWeather().setCity(configuration.getCity());
                        weather.getWeather().setUnit(configuration.getUnitToString());
                        weather.getWeather().save();
                        WeatherAdapter weatherAdapter = new WeatherAdapter(weather.getWeather().getResults().getChannel().getItem().getForecast());
                        forecastList.setAdapter(weatherAdapter);
                        setLayoutValues(weather.getWeather());
                        onFinishProgress();
                        showContent();
                    } else {
                        onFinishProgress();
                        onFinishError();
                        Toast.makeText(HomeActivity.this, getResources().getString(R.string.error_data), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<WeatherResult> call, Throwable t) {
                t.fillInStackTrace();
                onFinishProgress();
                onFinishError();
                Toast.makeText(HomeActivity.this, getResources().getString(R.string.error_data), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setLayoutValues() {
        getSupportActionBar().setTitle(configuration.getCity());
        getData();
    }

    private void setLayoutValues(Weather weather) {
        getSupportActionBar().setTitle(configuration.getCity());
        Picasso.with(HomeActivity.this)
                .load(BackgroundImageHelper.getImageUrl(weather))
                .into(header);
        Picasso.with(HomeActivity.this)
                .load(UrlHelper.getImageUrl(weather))
                .into(image);
        textViewTemperature.setText(weather.getResults().getChannel().getItem().getCondition().getTemperature());
        textViewUnit.setText(configuration.getUnitAbbreviation());
        textViewDescription.setText(weather.getResults().getChannel().getItem().getCondition().getText());
    }

    public void verifyData() {
        Weather weather = Weather.first(Weather.class);
        if (weather != null &&
                weather.getCity().equalsIgnoreCase(configuration.getCity()) &&
                weather.getUnit().equalsIgnoreCase(configuration.getUnitToString())) {
            WeatherAdapter weatherAdapter = new WeatherAdapter(weather.getResults().getChannel().getItem().getForecast());
            forecastList.setAdapter(weatherAdapter);
            setLayoutValues(weather);
        } else {
            setLayoutValues();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(HomeActivity.this, ConfigurationActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onLoadProgress() {
        hideError();
        hideContent();
        progressbarLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onFinishProgress() {
        progressbarLayout.setVisibility(View.GONE);
    }

    @Override
    public void onFinishError() {
        hideContent();
        errorLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideError() {
        errorLayout.setVisibility(View.GONE);
    }

    @Override
    public void hideContent() {
        contentLayout.setVisibility(View.GONE);
    }

    @Override
    public void showContent() {
        contentLayout.setVisibility(View.VISIBLE);
    }
}
