package com.example.sharedpreferncespractise;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Note_adapter extends RecyclerView.Adapter<Note_adapter.NotesViewHolder> {

    private List<Note_item> noteItemArrayList;

    private OnNoteClickListener onNoteClickListener;

    interface OnNoteClickListener {
        void onClick(int position);
        void onLongClick(int position);
    }

    public List<Note_item> getNoteItemArrayList() {
        return noteItemArrayList;
    }

    public void setNoteItemArrayList(List<Note_item> noteItemArrayList) {
        this.noteItemArrayList = noteItemArrayList;
    }

    public void setOnNoteClickListener(OnNoteClickListener onNoteClickListener) {
        this.onNoteClickListener = onNoteClickListener;
    }

    public Note_adapter(ArrayList<Note_item> noteItemArrayList) {
        this.noteItemArrayList = noteItemArrayList;
    }


    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View noteItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item,parent,false);
       return new NotesViewHolder(noteItem);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        Note_item note_item = noteItemArrayList.get(position);
        holder.noteTitle.setText(note_item.getNoteTitle());
        holder.dayOfWeek.setText(Note_item.getDayAsInteger(note_item.getDayOfWeek() + 1));
        holder.noteDescription.setText(note_item.getNoteDescription());
        int colorId;
        int priority = note_item.getPriority();
        switch(priority) {
            case 1:
                colorId = holder.itemView.getResources().getColor(android.R.color.holo_red_light);
                break;
            case 2:
                colorId = holder.itemView.getResources().getColor(android.R.color.holo_orange_dark);
                break;
            default:
                colorId = holder.itemView.getResources().getColor(android.R.color.holo_green_dark);
                break;
        }
        holder.noteTitle.setBackgroundColor(colorId);

    }

    @Override
    public int getItemCount() {
        return noteItemArrayList.size();
    }

    class NotesViewHolder extends RecyclerView.ViewHolder {

        TextView noteTitle;
        TextView noteDescription;
        TextView dayOfWeek;

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            noteTitle = itemView.findViewById(R.id.textViewNoteTitle);
            noteDescription = itemView.findViewById(R.id.textViewNoteDescription);
            dayOfWeek = itemView.findViewById(R.id.textViewDayOfWeek);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onNoteClickListener != null) {
                        onNoteClickListener.onClick(getAdapterPosition());
                    }
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if(onNoteClickListener != null) {
                        onNoteClickListener.onLongClick(getAdapterPosition());
                    }
                    return true;
                }
            });
        }
    }
}