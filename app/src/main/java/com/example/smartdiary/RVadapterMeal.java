package com.example.smartdiary;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RVadapterMeal extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    ArrayList<Meal> list = new ArrayList<>();
    public RVadapterMeal(Context ctx){
        this.context =ctx;
    }
    public void setItems(ArrayList<Meal> ml){
        list.addAll(ml);

    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(context).inflate(R.layout.layout_item,parent,false );
        return new MealVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        MealVH vh = (MealVH) holder;
        Meal ml = list.get(position);
        vh.txt_MLname.setText(ml.getMeal_name());
        vh.txt_protein.setText(ml.getMeal_protien());
        vh.txt_fat.setText(ml.getMeal_fat());
        vh.txt_carbs.setText(ml.getMeal_carbs());
        vh.txt_calories.setText(ml.getMeal_calories());
        vh.txt_descript.setText(ml.getMeal_description());
        vh.txt_date.setText(ml.getMeal_date());
        vh.txt_option.setOnClickListener(v->{
            PopupMenu popupMenu = new PopupMenu(context,vh.txt_option);
            popupMenu.inflate(R.menu.option_menu);
            popupMenu.setOnMenuItemClickListener(item->{
                switch (item.getItemId()){
                    case R.id.menu_edit:
                        Intent intent = new Intent(context,create.class);
                        intent.putExtra("EDIT", ml);
                        context.startActivity(intent);
                        break;
                    case R.id.menu_delete:
                        MealDAO dao = new MealDAO();
                        dao.delete(ml.getKey()).addOnSuccessListener(suc->{
                            Toast.makeText(context, "deleted", Toast.LENGTH_SHORT).show();
                            notifyItemRemoved(position);
                        }).addOnFailureListener(er -> {
                            Toast.makeText(context, "fail" + er.getMessage(), Toast.LENGTH_SHORT).show();
                        });
                        break;
                }
                return false;
            });
        popupMenu.show();
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
