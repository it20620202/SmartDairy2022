package com.example.smartdiary;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;

public class MealDAO {
    private DatabaseReference dbReference;
    public MealDAO(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        dbReference = db.getReference(Meal.class.getSimpleName());
    }
    public Task<Void> add(Meal ml){
        return dbReference.push().setValue(ml);
    }
    public Query get(){
        return dbReference.orderByKey();
    }
    public Task<Void> update(String key , HashMap<String, Object> hashMap){
        return dbReference.child(key).updateChildren(hashMap);
    }
    public Task<Void> delete(String key){
        return dbReference.child(key).removeValue();
    }
}
