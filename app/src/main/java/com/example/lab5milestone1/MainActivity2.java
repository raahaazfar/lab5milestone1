package com.example.lab5milestone1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.ConditionVariable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    TextView textView;
    public static ArrayList<Note> notes = new ArrayList<>();

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

            //2. get SQLite instance
            Context context = getApplicationContext();
            SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes", Context.MODE_PRIVATE, null);

            //3. initiate notes class variable
            DBHelper dbHelper = new DBHelper(sqLiteDatabase);
            notes = dbHelper.readNotes(str);

            //notes
            ArrayList<String> displayNotes = new ArrayList<>();
            for (Note note : notes) {
                displayNotes.add(String.format("Title:%s\nDate:%s", note.getTitle(), note.getDate()));
            }
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, displayNotes);
            ListView listView = (ListView) findViewById(R.id.noteslistView);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity3.class);
                    intent.putExtra("noteid", position);
                    startActivity(intent);
                }
            });

        }
        else {
            Intent intent = getIntent();
            String str = intent.getStringExtra("message");
            textView.setText("Hello " + str);
        }
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
            Intent intent = new Intent(this, MainActivity3.class);
            startActivity(intent);
            return true;
        }
    }
    
}