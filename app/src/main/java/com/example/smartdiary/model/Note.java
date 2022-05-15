package com.example.smartdiary.model;

public class Note {

    private String Date,NoteTopic,DailyNote;

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getNoteTopic() {
        return NoteTopic;
    }

    public void setNoteTopic(String noteTopic) {
        NoteTopic = noteTopic;
    }

    public String getDailyNote(String DailyNote) {
        return DailyNote;
    }

    public void setDailyNote(String dailyNote) {
        DailyNote = dailyNote;
    }

    public Note() { }
}
