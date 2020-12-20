package com.msbs.android.signinfirebasepractice;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.msbs.android.signinfirebasepractice.model.AppExecutors;
import com.msbs.android.signinfirebasepractice.model.NewDatabase;
import com.msbs.android.signinfirebasepractice.model.User;
import com.msbs.android.signinfirebasepractice.view.LoggedInActivity;
import com.msbs.android.signinfirebasepractice.view.UserEditDetailsActivity;
import com.msbs.android.signinfirebasepractice.viewmodel.LoginRegisterViewModel;

public class MainActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button registerButton;

    private int mTaskId = DEFAULT_TASK_ID;
    // Constant for default task id to be used when not in update mode
    private static final int DEFAULT_TASK_ID = -1;
    private NewDatabase mDb;

    private LoginRegisterViewModel loginRegisterViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize member variable for the data base
        mDb = NewDatabase.getInstance(getApplicationContext());

        loginRegisterViewModel = ViewModelProviders.of(this).get(LoginRegisterViewModel.class);
        loginRegisterViewModel.getUserLiveData().observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if (firebaseUser != null) {
                    Intent intent = new Intent(MainActivity.this, UserEditDetailsActivity.class);

                    startActivity(intent);
                }
            }
        });

        emailEditText = findViewById(R.id.fragment_loginregister_email);
        passwordEditText = findViewById(R.id.fragment_loginregister_password);
        loginButton = findViewById(R.id.fragment_loginregister_login);
        registerButton = findViewById(R.id.fragment_loginregister_register);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public void onClick(View view) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                if (email.length() > 0 && password.length() > 0) {
                    loginRegisterViewModel.login(email, password);
                } else {
                    Toast.makeText(MainActivity.this, "Email Address and Password Must Be Entered", Toast.LENGTH_SHORT).show();
                }
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public void onClick(View view) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                if (email.length() > 0 && password.length() > 0) {
                    loginRegisterViewModel.register(email, password);





                } else {
                    Toast.makeText(MainActivity.this, "Email Address and Password Must Be Entered", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}