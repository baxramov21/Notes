package com.example.sharedpreferncespractise;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView todoList;
    private final ArrayList<Note_item> noteItems = new ArrayList<>();
    private Note_adapter adapter;

    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        todoList = findViewById(R.id.recycler_view_notes);
        getData();
        adapter = new Note_adapter(noteItems);
        todoList.setAdapter(adapter);
        todoList.setLayoutManager(new LinearLayoutManager(this));

        adapter.setOnNoteClickListener(new Note_adapter.OnNoteClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(MainActivity.this, position + " element", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(int position) {
                removeElement(position);
            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                removeElement(viewHolder.getAdapterPosition());
            }
        });
        itemTouchHelper.attachToRecyclerView(todoList);
    }

    private void removeElement(int position) {
        Note_item note_item = adapter.getNoteItemArrayList().get(position);
        viewModel.deleteNote(note_item);
    }

    public void onClickCreateNote(View view) {
        Intent intentCreateNewNote = new Intent(this, CreateNoteActivity.class);
        startActivity(intentCreateNewNote);
    }

    private void getData() {
        LiveData<List<Note_item>> noteItemsFromDatabase = viewModel.getLiveData();
        noteItemsFromDatabase.observe(this, new Observer<List<Note_item>>() {
                    @Override
                    public void onChanged(List<Note_item> noteItemsFromLiveData) {
                       adapter.setNoteItemArrayList(noteItemsFromLiveData);
                    }
                });
    }
}