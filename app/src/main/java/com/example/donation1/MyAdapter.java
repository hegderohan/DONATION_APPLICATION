package com.example.donation1;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;

    ArrayList<User> list;


    public MyAdapter(Context context, ArrayList<User> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        User user = list.get(position);
        holder.firstName.setText(user.getUpi_id());
        holder.lastName.setText(user.getCause());
        holder.age.setText(user.getAmount());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView firstName, lastName, age;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            firstName = itemView.findViewById(R.id.tvfirstName);
            lastName = itemView.findViewById(R.id.tvlastName);
            age = itemView.findViewById(R.id.tvage);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
           // Log.i("hello ","presssed");

            ClipboardManager cm=(ClipboardManager)view.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData cd=ClipData.newPlainText("Text",firstName.getText().toString());
            cm.setPrimaryClip(cd);

            Toast.makeText(itemView.getContext(), "Upi Copied "+firstName.getText().toString(), Toast.LENGTH_SHORT).show();
            view.getContext().startActivity(new Intent(view.getContext(),DonateMoney.class));
        }
    }

}