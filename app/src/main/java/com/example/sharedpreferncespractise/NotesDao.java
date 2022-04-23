package com.example.sharedpreferncespractise;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;


@Dao
public interface NotesDao {
    @Query("SELECT * FROM  notes ORDER BY dayOfWeek")
    LiveData<List<Note_item>> getAllNotes();

    @Insert
    void insertData(Note_item noteItem);

    @Delete
    void deleteNote(Note_item note_item);

    @Query("DELETE FROM notes")
    void deleteAll();
}
