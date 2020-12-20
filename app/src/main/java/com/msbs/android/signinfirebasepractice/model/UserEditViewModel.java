package com.msbs.android.signinfirebasepractice.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

//This deals with the StoryDao
public class UserEditViewModel extends ViewModel {

    //  private UserEditRepository mRepository;
    private LiveData<User> task;


    public UserEditViewModel(){ };
    //Add a task member variable for the TaskEntry object wrapped in a LiveData


    // Create a constructor where you call loadTaskById of the taskDao to initialize the tasks variable
    // Note: The constructor should receive the database and the taskId
    public UserEditViewModel(NewDatabase database, String taskId) {
        task = database.userDao().loadStoryById(taskId);
    }



    // Create a getter for the task variable
    public LiveData<User> getTask() {
        return task;
    }

    ;


}
