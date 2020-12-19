package com.msbs.android.signinfirebasepractice.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.msbs.android.signinfirebasepractice.FavoritesActivity;
import com.msbs.android.signinfirebasepractice.FavoritesAdapter;
import com.msbs.android.signinfirebasepractice.FavoritesFragment;
import com.msbs.android.signinfirebasepractice.MainActivity;
import com.msbs.android.signinfirebasepractice.R;

import com.msbs.android.signinfirebasepractice.model.User;
import com.msbs.android.signinfirebasepractice.model.UserViewModel;

import com.msbs.android.signinfirebasepractice.viewmodel.LoggedInViewModel;

import java.util.ArrayList;
import java.util.List;

public class LoggedInActivity extends AppCompatActivity {
    private TextView loggedInUserTextView;
    private Button logOutButton;

    private LoggedInViewModel loggedInViewModel;
    private TextView messageTextView;
    // Member variable for the Database


    TextView mDateTime;
    private User stories;
    private int mTaskId = DEFAULT_TASK_ID;
    private static final int DEFAULT_TASK_ID = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);



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

        // Find the View that shows the Lauryn Hill songs category
        Button logout = (Button) findViewById(R.id.search_button);

        // Set a click listener on that View
        logout.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the Lauryn Hill songs View is clicked on.
            @Override
            public void onClick(View view) {
                Intent laurynIntent = new Intent(LoggedInActivity.this, FavoritesActivity.class);
                startActivity(laurynIntent);

            }
        });

    }


}