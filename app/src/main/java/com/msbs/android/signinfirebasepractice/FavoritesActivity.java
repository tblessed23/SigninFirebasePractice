package com.msbs.android.signinfirebasepractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class FavoritesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new FavoritesFragment())
                .commit();

    }
}