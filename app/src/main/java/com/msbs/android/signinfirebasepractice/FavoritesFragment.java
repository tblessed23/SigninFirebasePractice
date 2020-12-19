package com.msbs.android.signinfirebasepractice;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.msbs.android.signinfirebasepractice.model.AppExecutors;
import com.msbs.android.signinfirebasepractice.model.NewDatabase;
import com.msbs.android.signinfirebasepractice.model.User;
import com.msbs.android.signinfirebasepractice.model.UserViewModel;

import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment {

    // Constant for logging
    private static final String TAG = FavoritesFragment.class.getSimpleName();
    private NewDatabase mDb;
    private FavoritesAdapter mAdapter;

    private RecyclerView.LayoutManager layoutManager;
private RecyclerView mRecyclerView;

    public FavoritesFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDb = NewDatabase.getInstance(getActivity());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_favorites, container, false);

        setHasOptionsMenu(true);
        mRecyclerView = rootView.findViewById(R.id.recyclerViewFavorites);

        // use a grid layout manager

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        //mRecyclerView.setHasFixedSize(true);

        // Create a new adapter that takes an empty list of moviess as input
        mAdapter = new FavoritesAdapter(getActivity(), new ArrayList<User>());
        mRecyclerView.setAdapter(mAdapter);


        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        mRecyclerView.setAdapter(mAdapter);





        setUpViewModel();
        return rootView;
    }


    private void setUpViewModel() {
        //Insert Update andDelete do not have to observe changes in the database. This is for retrieving tass.
        //LiveData is for retrieving data, AppExcutors for the other three
        // Log.d(TAG, "Actively retrieving the tasks from the DataBase");
       UserViewModel viewModel = new ViewModelProvider((ViewModelStoreOwner) getActivity()).get(UserViewModel.class);
        viewModel.getTasks().observe((LifecycleOwner) getActivity(), (Observer<? super List<User>>) new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> favoritesEntries) {

                mAdapter.setTasks(favoritesEntries);
            }
        });
    }
}