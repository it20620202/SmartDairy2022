package com.example.smartdairy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartdairy.model.Notes;

import java.util.ArrayList;

public class NoteAdapter  extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    Context context;

    ArrayList<Notes> list;
    private OnItemClickListener mListener;


    public interface OnItemClickListener{
        void onDelete(String noteKey, String Date, int position);
        void onUpdate(String noteKey,String Date, String Topic, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public NoteAdapter(Context context, ArrayList<Notes> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.noteitem,parent,false);
        return new NoteViewHolder(v,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
                Notes notes =list.get(position);
                holder.noteKey.setText(notes.getId());
                holder.Date.setText(notes.getDate());
                holder.Topic.setText(notes.getNoteTopic());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class NoteViewHolder extends RecyclerView.ViewHolder{

        TextView noteKey,Date, Topic;
        CheckBox completed;
        ImageView editBtn;



        public NoteViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            noteKey=itemView.findViewById(R.id.noteKey);
            Date=itemView.findViewById(R.id.item_date);
            Topic =itemView.findViewById(R.id.item_topic);
            completed=itemView.findViewById(R.id.completed);


            completed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onDelete(noteKey.getText().toString(),Topic.getText().toString(), position);
                        }

                    }
                }
            });
            editBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }







    }
}
