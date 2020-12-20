package com.msbs.android.signinfirebasepractice.model;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import org.jetbrains.annotations.NotNull;

public class UserEditViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final NewDatabase mDb;
    private final String mTaskId;


    // Initialize the member variables in the constructor with the parameters received
    public  UserEditViewModelFactory(NewDatabase database, String taskId) {
        mDb = database;
        mTaskId = taskId;


    }

    // Uncomment the following method
    @NotNull
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new UserEditViewModel(mDb, mTaskId);
    }


}