package com.example.contactlist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> implements Filterable {
    private Context context;

    Activity activity;
    private ArrayList temp;
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
        temp = new ArrayList(nameList);
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
         Log.i("Position: ", Integer.toString(position));
        Log.i("List Size: ", String.valueOf(nameList.size()));
        // holder.family.setText(String.valueOf(familyList.get(position)));
         if(position < nameList.size())
          holder.name.setText(String.valueOf(nameList.get(position)));
         if(position >= nameList.size()){
             holder.name.setText(" ");
             holder.image.setImageAlpha(0);
         }

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

    @Override
    public Filter getFilter() {
        return filter;
    }
    Filter filter = new Filter(){
       //Will Run On Background Thread
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<String> filteredList = new ArrayList<>();
            if(constraint.toString().isEmpty()){
                filteredList.addAll(temp);
            }
            else{
                for(Object name : temp){
                   if(name.toString().toLowerCase().contains(constraint.toString().toLowerCase())){
                       filteredList.add(name.toString());
                   }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }
        //Will Run On UI Thread
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            Log.i("Results: ", results.values.toString());
             nameList.clear();
            Log.i("Name List: ", nameList.toString());
             //nameList.addAll((Collection<? extends String>) results.values);
            nameList.addAll((List) results.values);
             notifyDataSetChanged();
        }
    };

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        LinearLayout mainLayout;
        ImageView image;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            image = itemView.findViewById(R.id.imageView3);
            mainLayout = itemView.findViewById(R.id.mainLayout);

        }
    }
}
