package com.msbs.android.signinfirebasepractice.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.FirebaseUser;
import com.msbs.android.signinfirebasepractice.model.AuthAppRepository;

public class LoggedInViewModel extends AndroidViewModel {
    private AuthAppRepository authAppRepository;
    private MutableLiveData<FirebaseUser> userLiveData;
    private MutableLiveData<Boolean> loggedOutLiveData;

    public LoggedInViewModel(@NonNull Application application) {
        super(application);

        authAppRepository = new AuthAppRepository(application);
        userLiveData = authAppRepository.getUserLiveData();
        loggedOutLiveData = authAppRepository.getLoggedOutLiveData();
    }

    public void logOut() {
        authAppRepository.logOut();
    }

    public MutableLiveData<FirebaseUser> getUserLiveData() {
        return userLiveData;
    }

    public MutableLiveData<Boolean> getLoggedOutLiveData() {
        return loggedOutLiveData;
    }

//    public void signInWithGoogle(EmailAuthCredential googleAuthCredential) {
//        userLiveData = authAppRepository.firebaseSignInWithGoogle(googleAuthCredential);
//    }
}

