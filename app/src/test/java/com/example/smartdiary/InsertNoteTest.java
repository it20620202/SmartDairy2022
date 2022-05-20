package com.example.smartdairy;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import com.example.smartdairy.model.Notes;
/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class InsertNoteTest {
    public class insetNoteTest {
        Notes t;

        @Test
        public void testGrade() {
            t = new Notes();

            // assign values to task object
            t.setDate("2021/2/02");
            t.setNoteTopic("Final Exam");

            // check if data is stored correctly
            assertEquals("2021/2/02", t.getDate());
            assertEquals("Final Exam", t.getDailyNote());
        }


    }
}