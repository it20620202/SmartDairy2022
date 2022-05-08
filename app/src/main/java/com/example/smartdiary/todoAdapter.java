package com.example.smartdiary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartdiary.model.Task;

import java.util.ArrayList;


public class todoAdapter extends RecyclerView.Adapter<todoAdapter.todoViewHolder>{

    Context context;
    ArrayList<Task> list;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onDelete(String todoKey,String taskName, int position);
        void onUpdate(String todoKey, String taskName, String description, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public todoAdapter(Context context, ArrayList<Task> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public todoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.todo_item, parent, false);
        return new todoViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull todoViewHolder holder, int position) {
        Task todo = list.get(position);
        holder.todoKey.setText(todo.getId());
        holder.taskName.setText(todo.getTask());
        holder.description.setText(todo.getDescription());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class todoViewHolder extends RecyclerView.ViewHolder{

        TextView todoKey, taskName, description;
        ImageView editBtn;
        CheckBox completed;

        public todoViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            todoKey = itemView.findViewById(R.id.todoKey);
            taskName = itemView.findViewById(R.id.item_task);
            description = itemView.findViewById(R.id.item_description);
            editBtn = itemView.findViewById(R.id.editTask);
            completed = itemView.findViewById(R.id.completed);

            completed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onDelete(todoKey.getText().toString(), taskName.getText().toString(), position);
                        }

                    }

                }
            });

            editBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            String todoKey_txt = todoKey.getText().toString();
                            String taskName_txt = taskName.getText().toString();
                            String description_txt = description.getText().toString();

                            listener.onUpdate(todoKey_txt, taskName_txt, description_txt,position);
                        }

                    }

                }
            });

        }
    }
}
