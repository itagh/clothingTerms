package com.nouf.projects.clothingtermsdesign;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class myAdapter extends RecyclerView.Adapter<myAdapter.myViewHolder> {
Context context;
ArrayList<machines> machines;

    public myAdapter(Context c , ArrayList<machines> m ){
        context = c;
        machines = m;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new myViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.machine_arb.setText(machines.get(position).getArabic_term());
        holder.machine_eng.setText(machines.get(position).getEnglish_term());
        Picasso.get().load(machines.get(position).getImgUri_term()).into(holder.machine_image);



    }

    @Override
    public int getItemCount() {
        return machines.size();
    }

    class myViewHolder extends  RecyclerView.ViewHolder{

       // ImageButton play_sign;
        ImageView  machine_image;
        TextView machine_arb;
        TextView  machine_eng;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);


            machine_arb = (TextView) itemView.findViewById(R.id.machine_arb);
            machine_eng = (TextView) itemView.findViewById(R.id.machine_eng);
            machine_image = (ImageView) itemView.findViewById(R.id.machine_image);
        //    play_sign = (ImageButton) itemView.findViewById(R.id.play_sign);
        }
    }
}
