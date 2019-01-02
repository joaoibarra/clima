package br.com.ibarra.clima.ui.activities;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import br.com.ibarra.clima.R;
import br.com.ibarra.clima.api.models.Forecast;
import br.com.ibarra.clima.helpers.UrlHelper;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by joaoibarra on 20/01/16.
 */
public class WeatherDetailActivity extends AppCompatActivity implements BaseActivity {
    @BindView(R.id.progressbar)
    LinearLayout progressbarLayout;
    @BindView(R.id.error)
    RelativeLayout errorLayout;
    @BindView(R.id.content)
    NestedScrollView contentLayout;
    @BindView(R.id.date)
    TextView textViewDate;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.max_temperature)
    TextView textViewMaxTemperature;
    @BindView(R.id.min_temperature)
    TextView textViewMinTemperature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_detail);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(null);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setLayoutValues();
    }

    private void setLayoutValues() {
        Forecast forecast = EventBus.getDefault().removeStickyEvent(Forecast.class);
        textViewMaxTemperature.setText(forecast.getHigh());
        textViewMinTemperature.setText(forecast.getLow());
        textViewDate.setText(forecast.getDate());
        Picasso.with(this)
                .load(UrlHelper.getImageUrl(forecast.getCode()))
                .into(image);
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
