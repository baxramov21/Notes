package com.example.sharedpreferncespractise;


public class Note_item {

    private String noteTitle;
    private String noteDescription;
    private int dayOfWeek;
    private int priority;
    private int id;

    public Note_item(int id,String noteTitle, String noteDescription, int dayOfWeek, int priority) {
        this.id = id;
        this.noteTitle = noteTitle;
        this.noteDescription = noteDescription;
        this.dayOfWeek = dayOfWeek;
        this.priority = priority;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public int getId() {
        return id;
    }

    public String getNoteDescription() {
        return noteDescription;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public int getPriority() {
        return priority;
    }

    public static String getDayAsInteger(int position) {
        switch(position) {
            case 1:
                return "Dushanba";

            case 2:
                return "Seshanba";

            case 3:
                return "Chorshanba";

            case 4:
                return "Payshanba";

            case 5:
                return "Juma";

            case 6:
                return "Shanba";

            case 7:
                return "Yakshanba";
        }
        return null;
    }
}
