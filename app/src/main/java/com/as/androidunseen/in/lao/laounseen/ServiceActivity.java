package com.as.androidunseen.in.lao.laounseen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.as.androidunseen.in.lao.laounseen.fragement.ServiceFragment;

public class ServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.contentServiceFragment, new ServiceFragment())
                    .commit();
        }


    } // Main Method
} // Main Class
