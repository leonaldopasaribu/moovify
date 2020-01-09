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
import com.stilldre.moovify.model.Room;

public class RoomAdapter extends FirebaseRecyclerAdapter<Room, RoomAdapter.PastViewHolder> {

    public RoomAdapter(@NonNull FirebaseRecyclerOptions<Room> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull RoomAdapter.PastViewHolder holder, int i, @NonNull Room room) {
        holder.name.setText(room.getName());
    }

    @NonNull
    @Override
    public PastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.room, parent, false);
        return new PastViewHolder(view);
    }

    class PastViewHolder extends RecyclerView.ViewHolder{

        TextView name;

        public PastViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
        }
    }
}
