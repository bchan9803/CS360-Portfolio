package com.bryanchan.cs360_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Item_RecyclerViewAdapter extends RecyclerView.Adapter<Item_RecyclerViewAdapter.MyViewHolder> {
    Context context;
    ArrayList<ItemModel> itemModels;

    public Item_RecyclerViewAdapter(Context context, ArrayList<ItemModel> itemModels) {
        this.context = context;
        this.itemModels = itemModels;
    }

    @NonNull
    @Override
    public Item_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row, parent, false);

        return new Item_RecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Item_RecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.itemNameDisplay.setText(itemModels.get(position).getItemName());
//        holder.itemCount.setText(itemModels.get(position).getItemCount());
        holder.itemCountDisplay.setText(String.valueOf(itemModels.get(position).getItemCount()));
    }

    @Override
    public int getItemCount() {
        return itemModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView itemNameDisplay;
        TextView itemCountDisplay;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

//            itemName = itemName.findViewById(R.id.itemName);
//            itemCount = itemCount.findViewById(R.id.itemCount);

            itemNameDisplay = itemView.findViewById(R.id.itemNameDisplay);
            itemCountDisplay = itemView.findViewById(R.id.itemCountDisplay);
        }
    }

}
