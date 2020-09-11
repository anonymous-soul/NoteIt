package com.example.android.noteit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button credits;
    private FloatingActionButton floatingActionButton;
    private RecyclerView recyclerView;
    private ArrayList<ModelClass> modelClasses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        credits=findViewById(R.id.credits);
        floatingActionButton=findViewById(R.id.floatingActionButton2);
        credits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(getApplicationContext(),CreditsPage.class);
                startActivity(i1);
            }
        });
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        modelClasses = databaseHelper.getAllNotes();
        recyclerView=findViewById(R.id.recyclerView);
        if (modelClasses.size() != 0) {
            try {
                RecAdapter recAdapter=new RecAdapter(modelClasses);
                recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
                recyclerView.setAdapter(recAdapter);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
       floatingActionButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent(getApplicationContext(),EnterNote.class);
               startActivity(intent);
           }
       });
    }
}