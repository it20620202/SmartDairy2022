package com.example.mydiaryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class Goal_list extends AppCompatActivity {

    //create necessary variables
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    RVadapterGoal adapter;
    DAOGoal dao;
    TextView month;
    TextView motivationtd,stepsG,calsG;
    private DatabaseReference gDB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_list);


        //display the current date and month
        month = findViewById(R.id.month);
        Date currentDate = Calendar.getInstance().getTime();
        String formattedDate = DateFormat.getDateInstance(DateFormat.FULL).format(currentDate);
        month.setText(formattedDate);

        //find textViews
        motivationtd = findViewById(R.id.txt_gMotivationtd);
        stepsG= findViewById(R.id.txt_stepsGS);
        calsG = findViewById(R.id.txt_calsGS);

        //Database instance
        gDB = FirebaseDatabase.getInstance().getReference().child("Goal");
        gDB.addValueEventListener(new ValueEventListener() {
            //getItems from DB location
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //assign data to an object
                for(DataSnapshot data : snapshot.getChildren()){
                    Map<String,Object> map = (Map<String,Object>)  data.getValue();
                    Object step = map.get("stepsG");
                    Object calg= map.get("caloriesG");
                    Object moti = map.get("motivation");
                    Object dateG = map.get("dateG");
                    //parse values
                    int sValue = Integer.parseInt(String.valueOf(step));
                    int cValue = Integer.parseInt(String.valueOf(calg));
                    String mValue = String.valueOf(moti);
                    String date = String.valueOf(dateG);
                    //display only if data is on date
                    if (date.equals(formattedDate)){
                        stepsG.setText(String.valueOf(sValue));
                        calsG.setText(String.valueOf(cValue));
                        motivationtd.setText(String.valueOf(mValue));
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //populate the variables
        recyclerView = findViewById(R.id.grv);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter = new RVadapterGoal(this);
        recyclerView.setAdapter(adapter);
        dao = new DAOGoal();
        loadData();

        //when button click redirect to add page
        Button btn_g = findViewById(R.id.add_Gbtn);
        btn_g.setOnClickListener(v->{
            Intent intent = new Intent(Goal_list.this,createGoal.class);
            startActivity(intent);
        });
    }

    //load data
    public void loadData(){
        //display from DB
        dao.get().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Goal> goals = new ArrayList<>();
                for(DataSnapshot data : snapshot.getChildren())
                {
                    Goal go =  data.getValue(Goal.class);
                    go.setKey(data.getKey());
                    goals.add(go);
                }
                adapter.setItems(goals);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}