package br.com.ibarra.clima.ui.activities;

/**
 * Created by joaoibarra on 20/01/16.
 */
public interface BaseActivity {
    public void onLoadProgress();
    public void onFinishProgress();
    public void onFinishError();
    public void hideError();
    public void showContent();
    public void hideContent();
}
