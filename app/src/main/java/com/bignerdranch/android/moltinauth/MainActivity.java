package com.bignerdranch.android.moltinauth;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import moltin.android_sdk.Moltin;

import static android.os.Build.VERSION_CODES.M;
import static java.lang.reflect.Modifier.FINAL;

public class MainActivity extends AppCompatActivity {

    private Moltin moltin;
    private Button mButton;
    private static final String client_id = "8NOvhAZhn5YiNUcRa7NjVTbFnNy7LwNu2hlsyN9ScB";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moltin = new Moltin(this);

        mButton = (Button) findViewById(R.id.authenticate_button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authenticateMoltinAPI(client_id);
            }
        });

    }

    public void authenticateMoltinAPI(String id) {
        try {
            moltin.authenticate(id, new Handler.Callback() {
                @Override
                public boolean handleMessage(Message msg) {
                    if (msg.what == Constants.RESULT_OK) {
                        Toast.makeText(getApplicationContext(), "Authentication Successful.", Toast.LENGTH_LONG).show();
                        return true;
                    } else {
                        Toast.makeText(getApplicationContext(), "Authentication Failed. Try Again.", Toast.LENGTH_LONG).show();
                        return false;
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
