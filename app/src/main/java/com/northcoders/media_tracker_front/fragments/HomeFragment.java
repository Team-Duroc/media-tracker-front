package com.northcoders.media_tracker_front.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.northcoders.media_tracker_front.MainActivity;
import com.northcoders.media_tracker_front.R;
import com.northcoders.media_tracker_front.adapter.ExampleAdapter;
import com.northcoders.media_tracker_front.adapter.MyAdapter;
import com.northcoders.media_tracker_front.adapter.RecyclerViewInterface;
import com.northcoders.media_tracker_front.adapter.ShowSearchResultAdapter;
import com.northcoders.media_tracker_front.adapter.WatchHistoryAdapter;
import com.northcoders.media_tracker_front.databinding.FragmentWatchedBinding;
import com.northcoders.media_tracker_front.databinding.FragmentHomeBinding;
import com.northcoders.media_tracker_front.model.ShowSearchResult;
import com.northcoders.media_tracker_front.model.WatchHistory;
import com.northcoders.media_tracker_front.viewmodel.ShowSearchResultViewModel;
import com.northcoders.media_tracker_front.viewmodel.WatchHistoryViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements RecyclerViewInterface {
    private RecyclerView recyclerView;
    private ExampleAdapter adapter;
    private List<String> itemList;
    private SearchView searchView;
    private Button buttonAdd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Initialize RecyclerView
        searchView = view.findViewById(R.id.SearchViewHome);
        buttonAdd = view.findViewById(R.id.buttonAdd);
        recyclerView = view.findViewById(R.id.recyclerview);

        // Initialize RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize Data
        itemList = getSampleData();

        // Set up Adapter
        adapter = new ExampleAdapter(itemList);
        recyclerView.setAdapter(adapter);

        // Add Button Click Listener
        buttonAdd.setOnClickListener(v -> {
            String input = searchView.getQuery().toString().trim();   // .getText().toString().trim();
            if (!input.isEmpty()) {
                addItemToRecyclerView(input);
                //editTextInput.setText(""); // Clear the input field
            }
        });

        // Handle Search Query
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterRecyclerView(newText);
                return true;
            }
        });

        return view;
    }

    private List<String> getSampleData() {
        List<String> data = new ArrayList<>();
        data.add("hello");
        data.add("good by");
        data.add("Item 3");
        data.add("Item 4");
        data.add("Item 5");
        return data;
    }

    private void addItemToRecyclerView(String item) {
        itemList.add(item); // Add the new item to the list
        adapter.notifyItemInserted(itemList.size() - 1); // Notify adapter of the new item
        recyclerView.scrollToPosition(itemList.size() - 1); // Scroll to the new item
    }

    @Override
    public void onItemClick(int position) {

    }

    private void filterRecyclerView(String query) {
        List<String> filteredList = new ArrayList<>();
        for (String item : itemList) {
            if (item.toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(item);
            }
        }
        adapter.updateList(filteredList); // Update the adapter with the filtered list
    }
}

/* last code
public class HomeFragment extends Fragment implements RecyclerViewInterface {
//public class HomeFragment extends Fragment {
    /*
    private SearchView searchView;
    private FragmentHomeBinding binding;
    RecyclerView recyclerView;
    RecyclerView.Adapter rcAdapter;
    ArrayList<ShowSearchResult> showSearchResultArrayList;
    ShowSearchResultAdapter showSearchResultAdapter;
    ShowSearchResultViewModel viewModel;
*/
/*
    ShowSearchResultAdapter showSearchResultAdapter;
    ArrayList<ShowSearchResult> showSearchResultArrayList;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<String> dataList = new ArrayList<>();
    ShowSearchResultViewModel viewModel;
    private FragmentHomeBinding binding;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ShowSearchResultViewModel.class);
        getShowSearchResult("Star Wars");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Initialize SearchView
        SearchView searchView = view.findViewById(R.id.SearchViewHome);

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Populate the RecyclerView (example data)
        dataList.add("Apple");
        dataList.add("Banana");
        dataList.add("Orange");
        dataList.add("Grapes");

        // Set up adapter for RecyclerView
        /*
        adapter = new MyAdapter(dataList);
        recyclerView.setAdapter(adapter);
         */

        /*
        // Set up adapter for RecyclerView
        adapter = new ShowSearchResultAdapter(showSearchResultArrayList, this.getContext(), this);
        recyclerView.setAdapter(adapter);

        // Set the query text listener for the SearchView
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Handle query submit (optional)
                Log.i("GET request", "I am here");
                //getShowSearchResult(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Filter the RecyclerView based on the query text
               filterList(newText);
                return false;
            }
        });
        return view;
    }

    private  void getShowSearchResult(String query) {
        viewModel.getShowSearchResult(query).observe(this, new Observer<List<ShowSearchResult>>() {
            @Override
            public void onChanged(List<ShowSearchResult> showSearchResultList) {
                showSearchResultArrayList = (ArrayList<ShowSearchResult>) showSearchResultList;
                displayShowSearchResultInRecyclerView();
            }
        });
    }

    private void filterList(String query) {
        List<String> filteredList = new ArrayList<>();
        for (String item : dataList) {
            if (item.toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(item);
            }
        }

        // Update the adapter with the filtered list
        adapter = new ShowSearchResultAdapter(showSearchResultArrayList, this.getContext(), this);
        recyclerView.setAdapter(adapter);
    }

    private void displayShowSearchResultInRecyclerView() {
        recyclerView = binding.recyclerview;
        showSearchResultAdapter = new ShowSearchResultAdapter(showSearchResultArrayList, this.getContext(), this);
        recyclerView.setAdapter(showSearchResultAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setHasFixedSize(true);
        showSearchResultAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(int position) {

    }
}
*/



/*
//old code
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ShowSearchResultViewModel.class);
        getShowSearchResult("Star Wars");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);

        // search field

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        // Initialize SearchView
        SearchView searchView = view.findViewById(R.id.SearchViewHome);
        searchView.clearFocus();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //getShowSearchResult(query);
                Log.i("GET request", query);
                Log.i("GET request", "I am here");
                //getShowSearchResult(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.i("GET request", newText);
                return false;
            }

        });
        return binding.getRoot();  // needed to see the recyclerview
        //return view;                 // needed for search field
    }

    private  void getShowSearchResult(String query) {
        viewModel.getShowSearchResult(query).observe(this, new Observer<List<ShowSearchResult>>() {
            @Override
            public void onChanged(List<ShowSearchResult> showSearchResultList) {
                showSearchResultArrayList = (ArrayList<ShowSearchResult>) showSearchResultList;
                displayShowSearchResultInRecyclerView();
            }
        });
    }

    private void displayShowSearchResultInRecyclerView() {
        recyclerView = binding.recyclerview;
        showSearchResultAdapter = new ShowSearchResultAdapter(showSearchResultArrayList, this.getContext(), this);
        recyclerView.setAdapter(showSearchResultAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setHasFixedSize(true);
        showSearchResultAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(int position) {

    }

}

 */