package com.msbs.android.signinfirebasepractice.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.msbs.android.signinfirebasepractice.R;

public class UserDisplayDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_display_details);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new UserDisplayDetailsFragment())
                .commit();

    }
}