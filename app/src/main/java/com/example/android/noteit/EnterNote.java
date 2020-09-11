package com.example.android.noteit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EnterNote extends AppCompatActivity {

    private EditText title, descrption;
    private Button save, cancel;
    private ModelClass modelClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_note);
        modelClass = new ModelClass();
        title = findViewById(R.id.editTitle);
        descrption = findViewById(R.id.editDescription);
        cancel=findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(EnterNote.this,MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });
        save = findViewById(R.id.saveButton);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Toast.makeText(EnterNote.this, "Note Added Successfully", Toast.LENGTH_SHORT).show();
                    saveNote();
            }
        });
    }

    private void saveNote() {
        String title1 = title.getText().toString();
        String description1 = descrption.getText().toString();
        if (title1.equals("") || description1.equals("")) {
            Toast.makeText(this, "Please fill the details:)", Toast.LENGTH_SHORT).show();
        } else {
            DatabaseHelper databaseHelper = new DatabaseHelper(this);
            ModelClass modelClass = new ModelClass(title1, description1);
            databaseHelper.addNote(modelClass);
            databaseHelper.close();
            Intent i = new Intent(EnterNote.this, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        }
    }
}