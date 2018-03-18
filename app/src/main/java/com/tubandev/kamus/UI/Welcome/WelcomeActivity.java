package com.tubandev.kamus.UI.Welcome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.tubandev.kamus.R;
import com.tubandev.kamus.UI.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelcomeActivity extends AppCompatActivity implements WelcomeContract.View {

    private WelcomeContract.UserActionListener actionListener;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        getSupportActionBar().hide();
        ButterKnife.bind(this);
        actionListener = new WelcomePresenter(this);
        actionListener.loadData(this);
    }

    @Override
    public void setProgressBarValues(Integer value) {
        progressBar.setProgress(value);
    }

    @Override
    public void finishLoad() {
        Intent i = new Intent(WelcomeActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }


}
