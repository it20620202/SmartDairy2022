package com.example.smartdiary;

import com.google.android.material.textfield.TextInputEditText;

public class Notes {
    String Topic;
    TextInputEditText DailyNotes;

    public Notes(String topic, TextInputEditText dailyNotes) {
        Topic = topic;
        DailyNotes = dailyNotes;
    }

    public String getTopic() {
        return Topic;
    }

    public TextInputEditText getDailyNotes() {
        return DailyNotes;
    }
}
