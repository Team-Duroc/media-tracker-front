package com.northcoders.media_tracker_front.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.northcoders.media_tracker_front.R;


public class EpisodeFragment extends Fragment {

    public EpisodeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String value = getArguments().getString("index");
        Toast.makeText(getContext(), value, Toast.LENGTH_SHORT).show();

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_episode, container, false);
    }
}