package com.example.smartdiary;


import static org.junit.Assert.assertEquals;

import com.example.smartdiary.model.Task;

import org.junit.Test;

public class insetTaskTest {
    Task t;

    @Test
    public void testGrade() {
        t = new Task();

        // assign values to task object
        t.setTask("Study");
        t.setDescription("OOP chapter 5");

        // check if data is stored correctly
        assertEquals("Study", t.getTask());
        assertEquals("OOP chapter 5", t.getDescription());
    }


}