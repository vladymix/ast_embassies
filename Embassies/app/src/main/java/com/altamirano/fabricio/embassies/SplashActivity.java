package com.altamirano.fabricio.embassies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.altamirano.fabricio.embassies.activities.MainActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Intent main = new Intent(this, MainActivity.class);
        this.startActivity(main);

    }
}
