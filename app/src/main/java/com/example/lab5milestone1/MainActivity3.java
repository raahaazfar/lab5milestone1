package com.example.lab5milestone1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity3 extends AppCompatActivity {

    int noteid = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        EditText editTextNote = (EditText) findViewById(R.id.editTextNote);
        Intent intent = getIntent();
        //
        noteid = intent.getIntExtra("noteid",-1);

        if (noteid != -1) {
          Note note = MainActivity2.notes.get(noteid);
          String noteContent = note.getContent();
          editTextNote.setText(noteContent);
        }

    }
    public void onClickSave(View view) {
        //get edittext
        EditText note = (EditText) findViewById(R.id.editTextNote);
        String content = note.getText().toString();

        //initialize SQLiteDatabase
        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes", Context.MODE_PRIVATE, null);

        //3. initiate notes class variable
        DBHelper dbHelper = new DBHelper(sqLiteDatabase);

        //shared preferences:
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.lab5milestone1", MODE_PRIVATE);
        String username = sharedPreferences.getString(MainActivity.usernameKey, "");

        String title;
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String date = dateFormat.format(new Date());

        if (noteid == -1) {
            title = "NOTE_" + (MainActivity2.notes.size() + 1);
            dbHelper.saveNotes(username, title, content, date);
        }
        else {
            title = "NOTE_" + (noteid + 1);
            dbHelper.updateNote(title, date, content, username);
        }

        //go to MainActiviity2
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }

}