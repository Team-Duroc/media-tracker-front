package com.northcoders.media_tracker_front.adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.northcoders.media_tracker_front.R;
//import com.northcoders.media_tracker_front.databinding.ShowSearchResultItemBinding;
import com.northcoders.media_tracker_front.databinding.WatchingItemBinding;
import com.northcoders.media_tracker_front.fragments.EpisodeFragment;
import com.northcoders.media_tracker_front.model.Film;
import com.northcoders.media_tracker_front.model.ShowSearchResult;

import java.util.ArrayList;

public class ShowMovieResultAdapter extends RecyclerView.Adapter<ShowMovieResultAdapter.MovieSearchResultViewHolder> {
    // ArrayList<ShowSearchResult> showSearchResultArrayList;
    static ArrayList<Film> movieSearchResultArrayList;
    Context context;
    RecyclerViewInterface recyclerViewInterface;

    public ShowMovieResultAdapter(ArrayList<Film> watchList, Context context, RecyclerViewInterface recyclerViewInterface){
        this.movieSearchResultArrayList = watchList;
        this.context = context;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public MovieSearchResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        WatchingItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.watching_item,
                parent,
                false);
        return new MovieSearchResultViewHolder(binding, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowMovieResultAdapter.MovieSearchResultViewHolder holder, int position){
        Film film = movieSearchResultArrayList.get(position);
        holder.watchingItemBinding.setWatching(film);
    }

    @Override
    public  int getItemCount(){
        return movieSearchResultArrayList.size();
    }

    public static class MovieSearchResultViewHolder extends RecyclerView.ViewHolder {
        private static WatchingItemBinding watchingItemBinding;

        public MovieSearchResultViewHolder(WatchingItemBinding binding, RecyclerViewInterface recyclerViewInterface) {
            super(binding.getRoot());
            this.watchingItemBinding = binding;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                // Now to get the position of the chosen item from the recycler
                public void onClick(View v) {
                    //Log.i("EVENT Listener",   Integer.toString(getAdapterPosition()));                                    // get position of clicked show
                    //Log.i("EVENT Listener", showSearchResultArrayList.get(getAdapterPosition()).getName());               // get name of clicked show
                    Log.i("EVENT Listener", movieSearchResultArrayList.get(getAdapterPosition()).getId().toString());



                    // go back to home fragment
                    Fragment episodeFragment = new EpisodeFragment();
                    //Put the value
                    Bundle args = new Bundle();
                    args.putString("index", movieSearchResultArrayList.get(getAdapterPosition()).getId().toString() );
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

