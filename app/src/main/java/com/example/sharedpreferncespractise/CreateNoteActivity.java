package com.example.sharedpreferncespractise;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

public class CreateNoteActivity extends AppCompatActivity {

    private EditText editTextNoteTitle;
    private EditText editTextNoteDescription;
    private Spinner spinnerDaysOfWeek;

    private int priority = 1;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);
        editTextNoteTitle = findViewById(R.id.editNoteTitle);
        editTextNoteDescription = findViewById(R.id.editNoteDescription);
        spinnerDaysOfWeek = findViewById(R.id.spinnerDaysOfWeek);

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    }

    public void onClickSaveNote(View view) {
        int dayOfWeek = spinnerDaysOfWeek.getSelectedItemPosition() + 1;
        String noteTitle = editTextNoteTitle.getText().toString();
        String noteDescription = editTextNoteDescription.getText().toString();
        if (noteTitle.isEmpty() || noteDescription.isEmpty()) {
            Toast.makeText(this, R.string.pls_write_everything, Toast.LENGTH_SHORT).show();
        } else {
            Note_item noteItem = new Note_item(noteTitle,noteDescription,dayOfWeek,priority);
            viewModel.insertNote(noteItem);
            Intent intent = new Intent(this,MainActivity.class);
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