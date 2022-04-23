package com.example.sharedpreferncespractise;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private static NotesDatabase database;

    private LiveData<List<Note_item>> liveData;
    public MainViewModel(@NonNull Application application) {
        super(application);

        database = NotesDatabase.getInstance(getApplication());
        liveData = database.notesDao().getAllNotes();
    }

    public LiveData<List<Note_item>> getLiveData() {
        return liveData;
    }

    public void insertNote(Note_item note) {
        new InsertTask().execute(note);
    }

    private static class InsertTask extends AsyncTask<Note_item,Void,Void> {

        @Override
        protected Void doInBackground(Note_item... note_items) {
           if(note_items != null && note_items.length > 0) {
               database.notesDao().insertData(note_items[0]);
           }
           return null;
        }
    }


    public void deleteNote(Note_item note) {
        new deleteTask().execute(note);
    }

    private static class deleteTask extends AsyncTask<Note_item,Void,Void> {

        @Override
        protected Void doInBackground(Note_item... note_items) {
            if(note_items != null && note_items.length > 0) {
                database.notesDao().deleteNote(note_items[0]);
            }
            return null;
        }
    }


    public void deleteAllNote() {
        new DeleteAllTask().execute();
    }

    private static class DeleteAllTask extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... note_items) {
            database.notesDao().deleteAll();
            return null;
        }
    }
}
