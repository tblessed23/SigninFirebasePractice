package com.msbs.android.signinfirebasepractice.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.msbs.android.signinfirebasepractice.FavoritesAdapter;
import com.msbs.android.signinfirebasepractice.MainActivity;
import com.msbs.android.signinfirebasepractice.R;
import com.msbs.android.signinfirebasepractice.model.AppDatabase;
import com.msbs.android.signinfirebasepractice.model.AppExecutors;
import com.msbs.android.signinfirebasepractice.model.User;
import com.msbs.android.signinfirebasepractice.model.UserViewModel;

import com.msbs.android.signinfirebasepractice.viewmodel.LoggedInViewModel;

import java.util.ArrayList;
import java.util.List;

import static com.msbs.android.signinfirebasepractice.view.Constants.USER;

public class LoggedInActivity extends AppCompatActivity {
    private TextView loggedInUserTextView;
    private Button logOutButton;

    private LoggedInViewModel loggedInViewModel;
    private TextView messageTextView;
    // Member variable for the Database
    private AppDatabase mDb;
    private FavoritesAdapter mAdapter;
private RecyclerView mRecyclerView;

    TextView mDateTime;
    private User stories;
    private int mTaskId = DEFAULT_TASK_ID;
    private static final int DEFAULT_TASK_ID = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);



        mRecyclerView = findViewById(R.id.recyclerViewFavorites);

        // use a grid layout manager

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        //mRecyclerView.setHasFixedSize(true);

        // Create a new adapter that takes an empty list of moviess as input
        mAdapter = new FavoritesAdapter(this, new ArrayList<User>());
        mRecyclerView.setAdapter(mAdapter);


        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        mRecyclerView.setAdapter(mAdapter);


        loggedInViewModel = ViewModelProviders.of(this).get(LoggedInViewModel.class);
        loggedInViewModel.getUserLiveData().observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if (firebaseUser != null) {
                    loggedInUserTextView.setText("Logged In User: " + firebaseUser.getEmail());
                    logOutButton.setEnabled(true);
                } else {
                    logOutButton.setEnabled(false);
                }
            }
        });


        loggedInViewModel.getLoggedOutLiveData().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean loggedOut) {
                if (loggedOut) {
                    Toast.makeText(LoggedInActivity.this, "User Logged Out", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoggedInActivity.this, MainActivity.class);
                    startActivity(intent);

                }
            }
        });

        loggedInUserTextView = findViewById(R.id.fragment_loggedin_loggedInUser);
        logOutButton = findViewById(R.id.fragment_loggedin_logOut);

        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loggedInViewModel.logOut();
            }
        });




        setUpViewModel();

    }

    private void setUpViewModel() {
        //Insert Update andDelete do not have to observe changes in the database. This is for retrieving tass.
        //LiveData is for retrieving data, AppExcutors for the other three
        // Log.d(TAG, "Actively retrieving the tasks from the DataBase");
        UserViewModel viewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(UserViewModel.class);
        viewModel.getTasks().observe((LifecycleOwner) this, (Observer<? super List<User>>) new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> favoritesEntries) {

                mAdapter.setTasks(favoritesEntries);
            }
        });
    }
}