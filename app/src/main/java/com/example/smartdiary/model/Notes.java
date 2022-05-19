package com.example.smartdairy.model;

public class Notes {
    private String Id,Date,NoteTopic,DailyNote;

    public String getId() {return Id;}

    public void setId(String id) { Id = id; }

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

    public String getDailyNote() {
        return DailyNote;
    }

    public void setDailyNote(String dailyNote) {
        DailyNote = dailyNote;
    }

    public Notes() { }
}
