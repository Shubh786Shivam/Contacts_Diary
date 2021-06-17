package com.example.contactlist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private Context context;

    Activity activity;
    private ArrayList id_list, familyList, nameList, townList, streetList, countryList, houseNumberList, postcodeList, telephoneList, nameCardList;
    CustomAdapter(Activity activity, Context context, ArrayList id_list, ArrayList familyList,
                  ArrayList nameList, ArrayList townList, ArrayList streetList, ArrayList countryList, ArrayList houseNumberList,
                  ArrayList postcodeList, ArrayList telephoneList, ArrayList nameCardList){
        this.activity = activity;
        this.context = context;
         this.id_list = id_list;
        this.nameList = nameList;
        this.familyList = familyList;
        this.townList = townList;
        this.streetList = streetList;
        this.countryList = countryList;
        this.houseNumberList = houseNumberList;
        this.postcodeList = postcodeList;
        this.telephoneList = telephoneList;
        this.nameCardList = nameCardList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        // holder.family.setText(String.valueOf(familyList.get(position)));
        holder.name.setText(String.valueOf(nameList.get(position)));
        //holder.mobile.setText(String.valueOf(telephoneList.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(id_list.get(position)));
                intent.putExtra("fam", String.valueOf(familyList.get(position)));
                intent.putExtra("firs", String.valueOf(nameList.get(position)));
                intent.putExtra("house", String.valueOf(houseNumberList.get(position)));
                intent.putExtra("stree", String.valueOf(streetList.get(position)));
                intent.putExtra("tow", String.valueOf(townList.get(position)));
                intent.putExtra("countr", String.valueOf(countryList.get(position)));
                intent.putExtra("pin", String.valueOf(postcodeList.get(position)));
                intent.putExtra("tele", String.valueOf(telephoneList.get(position)));
                intent.putExtra("nameCa", String.valueOf(nameCardList.get(position)));

                 //context.startActivity(intent);


                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return familyList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            mainLayout = itemView.findViewById(R.id.mainLayout);

        }
    }
}
