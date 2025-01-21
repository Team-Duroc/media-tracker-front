package com.northcoders.media_tracker_front.adapter;

import static android.content.ContentValues.TAG;

import static com.google.android.material.internal.ContextUtils.getActivity;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import com.northcoders.media_tracker_front.R;
import com.northcoders.media_tracker_front.fragments.EpisodeFragment;
import com.northcoders.media_tracker_front.fragments.HomeFragment;
import com.northcoders.media_tracker_front.fragments.ShowSearchFragment;
import com.northcoders.media_tracker_front.model.ShowSearchResult;
import com.northcoders.media_tracker_front.model.WatchHistory;
import java.util.ArrayList;
import com.northcoders.media_tracker_front.databinding.ShowSearchResultItemBinding;
import android.os.Bundle;

public class ShowSearchResultAdapter extends RecyclerView.Adapter<ShowSearchResultAdapter.ShowSearchResultViewHolder> {
    // ArrayList<ShowSearchResult> showSearchResultArrayList;
    static ArrayList<ShowSearchResult> showSearchResultArrayList;
    Context context;
    RecyclerViewInterface recyclerViewInterface;

    public ShowSearchResultAdapter(ArrayList<ShowSearchResult> watchList, Context context, RecyclerViewInterface recyclerViewInterface){
        this.showSearchResultArrayList = watchList;
        this.context = context;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public ShowSearchResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        ShowSearchResultItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.show_search_result_item,
                parent,
                false);
        return new ShowSearchResultViewHolder(binding, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowSearchResultAdapter.ShowSearchResultViewHolder holder, int position){
        ShowSearchResult showSearchResult = showSearchResultArrayList.get(position);
        holder.showSearchResultItemBinding.setShowSearchResult(showSearchResult);
    }

    @Override
    public  int getItemCount(){
        return showSearchResultArrayList.size();
    }

    public static class ShowSearchResultViewHolder extends RecyclerView.ViewHolder {
        private static ShowSearchResultItemBinding showSearchResultItemBinding;

        public ShowSearchResultViewHolder(ShowSearchResultItemBinding binding, RecyclerViewInterface recyclerViewInterface) {
            super(binding.getRoot());
            this.showSearchResultItemBinding = binding;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                // Now to get the position of the chosen item from the recycler
                public void onClick(View v) {
                    //Log.i("EVENT Listener",   Integer.toString(getAdapterPosition()));                                    // get position of clicked show
                    //Log.i("EVENT Listener", showSearchResultArrayList.get(getAdapterPosition()).getName());               // get name of clicked show
                    Log.i("EVENT Listener", showSearchResultArrayList.get(getAdapterPosition()).getId().toString());



                    // go back to home fragment
                    Fragment episodeFragment = new EpisodeFragment();
                    //Put the value
                    Bundle args = new Bundle();
                    args.putString("index", showSearchResultArrayList.get(getAdapterPosition()).getId().toString() );
                    episodeFragment.setArguments(args);
                    //Inflate the fragment
                    //((FragmentActivity) v.getContext()).getSupportFragmentManager().beginTransaction().add(R.id.frameLayoutFragment, episodeFragment).commit();
                    ((FragmentActivity) v.getContext()).getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frameLayoutFragment, episodeFragment)
                            .addToBackStack(null)
                            .commit();

                    if(recyclerViewInterface != null){
                        // Get position of the adapter
                        int position = getAdapterPosition();
                        // Set the position to the interface
                        if(position != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}

