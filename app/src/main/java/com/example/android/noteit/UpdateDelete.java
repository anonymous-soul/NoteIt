package com.example.android.noteit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class UpdateDelete extends AppCompatActivity {

    private EditText updateTitle, updateDescription;
    private Button update, delete,cancel1;
    private String getTitle;
    private String getDescription;
    private int getId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        updateTitle = findViewById(R.id.updateTitle);
        updateDescription = findViewById(R.id.updateDescription);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);
        cancel1=findViewById(R.id.cancel1);
        getTitle = getIntent().getStringExtra("titleKey");
        getDescription = getIntent().getStringExtra("descriptionKey");
        getId = getIntent().getIntExtra("idKey", 0);
        updateTitle.setText(getTitle);
        updateDescription.setText(getDescription);
        cancel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateNote();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(UpdateDelete.this, "Note Deleted Succesfully", Toast.LENGTH_SHORT).show();
                deleteNote();
            }
        });
    }

    private void updateNote() {
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        String titl = updateTitle.getText().toString();
        String desp = updateDescription.getText().toString();
        if (titl.equals("") || desp.equals("")) {
            Toast.makeText(this, "Please fill the details:)", Toast.LENGTH_SHORT).show();
        }
        else{
            ModelClass modelClass = new ModelClass(getId, titl, desp);
            databaseHelper.update(modelClass);
            databaseHelper.close();
            Toast.makeText(UpdateDelete.this, "Note Updated Succesfully", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(UpdateDelete.this, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        }
    }

    private void deleteNote() {
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        ModelClass modelClass = new ModelClass(getId, getTitle, getDescription);
        databaseHelper.delete(modelClass);
        databaseHelper.close();
        Intent i = new Intent(UpdateDelete.this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
}