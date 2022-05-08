package com.example.smartdiary;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.material.textfield.TextInputEditText;

public class NotesRvmodel implements Parcelable {

    private String NoteTopic;
    private  String DailyNote;
    private String NoteId;


    public NotesRvmodel(TextInputEditText noteDate, TextInputEditText noteTopic){

    }

    public NotesRvmodel(String noteTopic, String dailyNote, String noteId) {

        NoteTopic = noteTopic;
        DailyNote = dailyNote;
        NoteId = noteId;
    }

    protected NotesRvmodel(Parcel in) {

        NoteTopic = in.readString();
        DailyNote = in.readString();
        NoteId = in.readString();
    }

    public static final Creator<NotesRvmodel> CREATOR = new Creator<NotesRvmodel>() {
        @Override
        public NotesRvmodel createFromParcel(Parcel in) {
            return new NotesRvmodel(in);
        }

        @Override
        public NotesRvmodel[] newArray(int size) {
            return new NotesRvmodel[size];
        }
    };



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

    public String getNoteId() {
        return NoteId;
    }

    public void setNoteId(String noteId) {
        NoteId = noteId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(NoteTopic);
        dest.writeString(DailyNote);
        dest.writeString(NoteId);
    }
}
