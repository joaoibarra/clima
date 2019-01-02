package br.com.ibarra.clima.ui.activities;

/**
 * Created by joaoibarra on 20/01/16.
 */
public interface BaseActivity {
    void onLoadProgress();
    void onFinishProgress();
    void onFinishError();
    void hideError();
    void showContent();
    void hideContent();
}
