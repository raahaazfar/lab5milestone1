package com.example.lab5milestone1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.ConditionVariable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationBarView;

public class MainActivity2 extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //get sharedpreferences
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.lab5milestone1", MODE_PRIVATE);
        textView = (TextView) findViewById(R.id.textView);
        if (!sharedPreferences.getString(MainActivity.usernameKey, "").equals("")) {
            //get username, display message
            String str = sharedPreferences.getString(MainActivity.usernameKey, "");
            textView.setText("Hello " + str);
        }
        else {
            Intent intent = getIntent();
            String str = intent.getStringExtra("message");
            textView.setText("Hello " + str);
        }

/**
        textView = (TextView) findViewById(R.id.textView);
        Intent intent = getIntent();
        String str = intent.getStringExtra("message");
        textView.setText("Hello ");
*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.logout) {
            Intent intent = new Intent(this, MainActivity.class);
            SharedPreferences sharedPreferences = getSharedPreferences("com.example.lab5milestone1", Context.MODE_PRIVATE);
            sharedPreferences.edit().remove(MainActivity.usernameKey).apply();
            startActivity(intent);
            return true;
        }
        else {
            return false;
        }
    }

    /**
    public void goToActivity1() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
     */
}