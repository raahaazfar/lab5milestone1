package com.example.lab5milestone1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static String usernameKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        usernameKey = "username";

        SharedPreferences sharedPreferences = getSharedPreferences("com.example.lab5milestone1", Context.MODE_PRIVATE);
        //usernamekey exists
        if (!sharedPreferences.getString(usernameKey, "").equals("")) {
            //get username, start MainActivity2
            String str = sharedPreferences.getString(usernameKey, "");
            goToActivity2(str);
        }
        else {
            //no usernamekey, start MainActivity
            setContentView(R.layout.activity_main);
        }
    }

    public void clickFunction(View view) {
        EditText name = (EditText) findViewById(R.id.name);
        String str = name.getText().toString();

        //Get username and password, add username to SharedPreferences objct
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.lab5milestone1", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("username",str).apply();

        //Go to MainActivity2
        goToActivity2(str);
    }

    public void goToActivity2(String s) {
        Intent intent = new Intent(this, MainActivity2.class);
        intent.putExtra("message",s);
        startActivity(intent);
    }
}