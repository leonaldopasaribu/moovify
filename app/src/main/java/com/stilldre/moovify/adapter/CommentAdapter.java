package com.stilldre.moovify.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.stilldre.moovify.R;
import com.stilldre.moovify.model.Comment;

public class CommentAdapter extends FirebaseRecyclerAdapter<Comment, CommentAdapter.PastViewHolder> {

    public CommentAdapter(@NonNull FirebaseRecyclerOptions<Comment> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull CommentAdapter.PastViewHolder holder, int i, @NonNull Comment comment) {
        holder.name.setText((comment.getUserName()));
        holder.msg.setText((comment.getMessage()));
    }

    @NonNull
    @Override
    public PastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comment, parent, false);
        return new PastViewHolder(view);
    }

    class PastViewHolder extends RecyclerView.ViewHolder{

        TextView name, msg;

        public PastViewHolder(@NonNull View itemView) {
            super(itemView);
            name= itemView.findViewById(R.id.userName);
            msg = itemView.findViewById(R.id.message);
        }
    }
}
