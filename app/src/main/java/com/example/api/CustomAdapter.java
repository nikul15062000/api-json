package com.example.api;

import android.app.Activity;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    Activity activity;
    List<Datum> listvalue;

    public CustomAdapter(MainActivity mainActivity, List<Datum> listvalue) {
        activity=mainActivity;
        this.listvalue=listvalue;
    }

    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(activity).inflate(R.layout.view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position) {

        holder.tv_text.setText(listvalue.get(position).getId().toString());
        holder.email.setText(listvalue.get(position).getEmail());
        holder.firstname.setText(listvalue.get(position).getFirstName());
        holder.lastname.setText(listvalue.get(position).getLastName());
//        holder.avatar.setImageURI(Uri.parse(listvalue.get(position).getAvatar()));
        Picasso.get().load(Uri.parse(listvalue.get(position).getAvatar())).into(holder.avatar);


    }

    @Override
    public int getItemCount() {
        return listvalue.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_text,email,firstname,lastname;
        ImageView avatar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_text=itemView.findViewById(R.id.tv_text);
            email=itemView.findViewById(R.id.email);
            firstname=itemView.findViewById(R.id.firstname);
            lastname=itemView.findViewById(R.id.lastname);
            avatar=itemView.findViewById(R.id.avatar);
        }
    }
}
