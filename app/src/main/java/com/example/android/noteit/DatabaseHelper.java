package com.example.android.noteit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.Display;
import android.widget.EditText;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "notes_manager";
    private static final String TABLE_NAME = "notes";

    // Coloumn Names
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_DESCRIPTION = "description";

    // Coloumn Combinations
    private static final String[] COLS_ID_TITLE_NOTE = new String[] {KEY_ID,KEY_TITLE,KEY_DESCRIPTION};

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_NOTES_TABLE = "CREATE TABLE " + TABLE_NAME + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT"+", "
                + KEY_TITLE + " TEXT NOT NULL"+ ", "
                + KEY_DESCRIPTION + " TEXT"
                + ")";
        db.execSQL(CREATE_NOTES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE = "DROP TABLE IF EXISTS "+ TABLE_NAME;
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    //CRUD OPERATIONS

    public void addNote(ModelClass modelClass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, modelClass.getTitle());
        values.put(KEY_DESCRIPTION, modelClass.getDescription());
        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    public void delete(ModelClass modelClass)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_NAME,KEY_ID+"=?",new String[] {String.valueOf(modelClass.getId())});
        db.close();
    }

    public void update(ModelClass modelClass)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String sql = "UPDATE notes \n" +
                "SET title = ?, \n" +
                "description = ? \n" +
                "WHERE id = ?;\n";
        db.execSQL(sql, new String[]{modelClass.getTitle(),modelClass.getDescription(),String.valueOf(modelClass.getId())});
        db.close();
    }


    public ArrayList<ModelClass> getAllNotes(){
        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<ModelClass> noteList = new ArrayList<>();

        Cursor cursor = db.query(TABLE_NAME,COLS_ID_TITLE_NOTE,null,null,null,null,null);

        if(cursor!= null && cursor.moveToFirst()){

            do{
                ModelClass modelClass = new ModelClass();
                modelClass.setId(Integer.parseInt(cursor.getString(0)));
                modelClass.setTitle(cursor.getString(1));
                modelClass.setDescription(cursor.getString(2));
                noteList.add(modelClass);
            }while (cursor.moveToNext());
        }
        db.close();
        return noteList;
    }
}