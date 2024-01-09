package com.example.church.outils;

import android.content.Context;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.church.R;

import java.util.ArrayList;

public class BibleRecyclerViewAdapter extends RecyclerView.Adapter<BibleRecyclerViewAdapter.MyViewHolder> {

    private final RecyclerViewInterface BibleRecyclerViewInterface;
    ArrayList<SpannableStringBuilder> bible;
    Context context;

    public BibleRecyclerViewAdapter(RecyclerViewInterface bibleRecyclerViewInterface, Context context, ArrayList<SpannableStringBuilder> bible) {
        this.BibleRecyclerViewInterface = bibleRecyclerViewInterface;
        this.bible = bible;
        this.context = context;
    }

    @NonNull
    @Override
    public BibleRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(R.layout.verses_recycler, parent, false);
        return new BibleRecyclerViewAdapter.MyViewHolder(view, BibleRecyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtDescription.setText(bible.get(position), TextView.BufferType.SPANNABLE);
    }


    @Override
    public int getItemCount() {
        return this.bible.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtDescription;
        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface hlRecyclerViewInterface) {
            super(itemView);
            txtDescription = itemView.findViewById(R.id.txtDescription);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (BibleRecyclerViewAdapter.this.BibleRecyclerViewInterface != null){
                        int pos = getAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION){
                            BibleRecyclerViewAdapter.this.BibleRecyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}
