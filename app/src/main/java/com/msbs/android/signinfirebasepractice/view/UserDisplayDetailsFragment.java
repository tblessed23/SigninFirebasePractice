package com.msbs.android.signinfirebasepractice.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.msbs.android.signinfirebasepractice.MainActivity;
import com.msbs.android.signinfirebasepractice.R;
import com.msbs.android.signinfirebasepractice.model.NewDatabase;
import com.msbs.android.signinfirebasepractice.model.User;
import com.msbs.android.signinfirebasepractice.model.UserViewModel;

import java.util.ArrayList;
import java.util.List;


public class UserDisplayDetailsFragment extends Fragment {



        //Constant for logging
        private static final String TAG = MainActivity.class.getSimpleName();

        // Member variables for the adapter and RecyclerView
        private RecyclerView mRecyclerView;
        private UserAdapter mAdapter;
        private List<User> mStoryEntries = new ArrayList<>();
        Button mButton;

        /**
         * TextView that is displayed when the list is empty
         */
        private TextView mEmptyStateTextView;

        //Implement Database
        private NewDatabase mDb;

    public UserDisplayDetailsFragment() {
        // Required empty public constructor
    }


        @Override
        public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//
//        }


    }

        @Override
        public View onCreateView (LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState){
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.activity_user_display_details, container, false);
            mRecyclerView = rootView.findViewById(R.id.recyclerViewTasks);

            // Set the RecyclerView to its corresponding view
        // mRecyclerView = rootView.findViewById(R.id.recyclerViewTasks);

        // Set the layout for the RecyclerView to be a linear layout, which measures and
        // positions items within a RecyclerView into a linear list
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Initialize the adapter and attach it to the RecyclerView
        mAdapter = new UserAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);

        DividerItemDecoration decoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(decoration);


        //Initialize Database
        mDb = NewDatabase.getInstance(getContext());

        setupViewModel();


//        if (mStoryEntries.isEmpty()) {
//            mRecyclerView.setVisibility(View.GONE);
//            mEmptyStateTextView.setVisibility(View.VISIBLE);
//        }
//        else {
//            mRecyclerView.setVisibility(View.VISIBLE);
//            mEmptyStateTextView.setVisibility(View.GONE);
//        }


        return rootView;
    }


        private void setupViewModel () {

        //MainViewModel viewModel2 = ViewModelProvider(this).get(MainViewModel.class);
       UserViewModel viewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(UserViewModel.class);
        viewModel.getTasks().observe(getViewLifecycleOwner(), new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> taskEntries) {
                Log.d(TAG, "Updating list of tasks from LiveData in ViewModel");
                mAdapter.setTasks(taskEntries);
            }
        });
    }

    }