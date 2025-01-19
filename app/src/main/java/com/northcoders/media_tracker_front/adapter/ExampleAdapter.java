package com.northcoders.media_tracker_front.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.northcoders.media_tracker_front.R;

import java.util.List;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {

    private final List<String> itemList;

    public ExampleAdapter(List<String> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_example, parent, false);
        return new ExampleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        // Bind data to the item view
        holder.textView.setText(itemList.get(position));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    // ViewHolder class to hold item views
    static class ExampleViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.itemText);
        }
    }

    public void updateList(List<String> newList) {
        itemList.clear();
        itemList.addAll(newList);
        notifyDataSetChanged();
    }
}
