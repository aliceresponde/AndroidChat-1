package com.example.andrearodriguez.androidchat.signup.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.andrearodriguez.androidchat.R;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setTitle(R.string.signup_title);
    }
}
