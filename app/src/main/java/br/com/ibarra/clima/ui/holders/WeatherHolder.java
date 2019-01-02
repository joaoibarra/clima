package br.com.ibarra.clima.ui.holders;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import br.com.ibarra.clima.R;
import br.com.ibarra.clima.api.models.Forecast;
import br.com.ibarra.clima.ui.activities.WeatherDetailActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by joaoibarra on 20/01/16.
 */

public class WeatherHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener {
    @Nullable
    @BindView(R.id.date)
    TextView textViewDate;
    @Nullable
    @BindView(R.id.max_temperature)
    TextView textViewMaxTemperature;
    @Nullable
    @BindView(R.id.min_temperature)
    TextView textViewMinTemperature;
    @Nullable
    @BindView(R.id.temperature)
    TextView textViewTemperature;
    @Nullable
    @BindView(R.id.unit)
    TextView textViewUnit;
    @Nullable
    @BindView(R.id.description)
    TextView textViewDescription;
    @Nullable
    @BindView(R.id.image)
    ImageView image;

    Forecast forecast;

    public WeatherHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
        view.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(view.getContext(), WeatherDetailActivity.class);
        EventBus.getDefault().postSticky(getForecast());
        view.getContext().startActivity(intent);
    }

    public TextView getTextViewDate() {
        return textViewDate;
    }

    public TextView getTextViewMaxTemperature() {
        return textViewMaxTemperature;
    }

    public TextView getTextViewMinTemperature() {
        return textViewMinTemperature;
    }

    public TextView getTextViewTemperature() {
        return textViewTemperature;
    }

    public TextView getTextViewUnit() {
        return textViewUnit;
    }

    public TextView getTextViewDescription() {
        return textViewDescription;
    }

    public Forecast getForecast() {
        return forecast;
    }

    public void setForecast(Forecast forecast) {
        this.forecast = forecast;
    }

    @Nullable
    public ImageView getImage() {
        return image;
    }

    public void setImage(@Nullable ImageView image) {
        this.image = image;
    }
}