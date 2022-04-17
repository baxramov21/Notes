package com.example.sharedpreferncespractise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class CreateNoteActivity extends AppCompatActivity {

    private EditText editTextNoteTitle;
    private EditText editTextNoteDescription;
    private Spinner spinnerDaysOfWeek;

    private int priority = 1;
    private NotesDataBaseHelper dataBaseHelper;
    private SQLiteDatabase sqLiteDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);
        editTextNoteTitle = findViewById(R.id.editNoteTitle);
        editTextNoteDescription = findViewById(R.id.editNoteDescription);
        spinnerDaysOfWeek = findViewById(R.id.spinnerDaysOfWeek);
        dataBaseHelper = new NotesDataBaseHelper(this);
        sqLiteDatabase = dataBaseHelper.getWritableDatabase();
    }

    public void onClickSaveNote(View view) {
        int dayOfWeek = spinnerDaysOfWeek.getSelectedItemPosition() + 1;
        String noteTitle = editTextNoteTitle.getText().toString();
        String noteDescription = editTextNoteDescription.getText().toString();
        if (noteTitle.isEmpty() || noteDescription.isEmpty()) {
            Toast.makeText(this, "Iltimos maydonlarni toldiring", Toast.LENGTH_SHORT).show();
        } else {
            ContentValues contentValues = new ContentValues();
            contentValues.put(NotesContract.NotesEntry.COLUMN_DAY_OF_WEEK,dayOfWeek);
            contentValues.put(NotesContract.NotesEntry.COLUMN_DESCRIPTION,noteDescription);
            contentValues.put(NotesContract.NotesEntry.COLUMN_TITLE,noteTitle);
            contentValues.put(NotesContract.NotesEntry.COLUMN_PRIORITY,priority);
            sqLiteDatabase.insert(NotesContract.NotesEntry.TABLE_NAME,null,contentValues);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    public void onClickSetPriority(View view) {
       RadioButton radioButton = (RadioButton) view;
        int priorityRadioButtonId = radioButton.getId();
       if(priorityRadioButtonId == R.id.radioButton1) {
           priority = 1;
       } else if(priorityRadioButtonId == R.id.radioButton2) {
           priority = 2;
       } else {
           priority = 3;
       }
    }
}