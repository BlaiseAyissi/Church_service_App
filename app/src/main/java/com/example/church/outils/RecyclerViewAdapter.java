package com.example.church.outils;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.church.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private final RecyclerViewInterface hlRecyclerViewInterface;
    ArrayList<String> hl;
    Context context;

    public RecyclerViewAdapter(RecyclerViewInterface hlRecyclerViewInterface, Context context, ArrayList<String> hl) {
        this.hlRecyclerViewInterface = hlRecyclerViewInterface;
        this.hl = hl;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(R.layout.chapter_verse_recycler, parent, false);
        return new RecyclerViewAdapter.MyViewHolder(view, hlRecyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.txtDescription.setText(hl.get(position));
    }

    @Override
    public int getItemCount() {
        return this.hl.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtDescription;
        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface hlRecyclerViewInterface) {
            super(itemView);
            txtDescription = itemView.findViewById(R.id.txtDescription);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (RecyclerViewAdapter.this.hlRecyclerViewInterface != null){
                        int pos = getAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION){
                            RecyclerViewAdapter.this.hlRecyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}
