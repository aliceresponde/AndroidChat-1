package com.example.andrearodriguez.androidchat.login;

import android.util.Log;

import com.example.andrearodriguez.androidchat.domain.FirebaseHelper;
import com.example.andrearodriguez.androidchat.entities.User;
import com.example.andrearodriguez.androidchat.lib.EvenBus;
import com.example.andrearodriguez.androidchat.lib.GreenRobotEventBus;
import com.example.andrearodriguez.androidchat.login.events.LoginEvent;
import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Map;

/**
 * Created by andrearodriguez on 6/11/16.
 */
public class LoginRepositoryImpl implements LoginRepository {

    private FirebaseHelper helper;
    private Firebase datareference;
    private Firebase myUserReference;

    private final String TAG = LoginRepositoryImpl.class.getSimpleName();


    public LoginRepositoryImpl() {
        this.helper = FirebaseHelper.getInstance();
        this.datareference = helper.getDataReference();
        this.myUserReference = helper.getMyUserReference();
    }

    @Override
    public void signUp(final String email, final String password) {
        datareference.createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> stringObjectMap) {
                postEvent(LoginEvent.onSignUpSuccess);
                SignIn(email , password);
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                postEvent(LoginEvent.onSignUpError, firebaseError.getMessage());
            }
        });



    }

    /**
     * Login
     *
     * @param email
     * @param password
     */
    @Override
    public void SignIn(String email, String password) {
        Log.e(TAG ,"SignIn" );

        datareference.authWithPassword(email, password, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                //reference to myUser

                Log.e(TAG ,"onAuthenticated" );

                myUserReference = helper.getMyUserReference();
                myUserReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Log.e(TAG ,"onAuthenticated->onDataChange" );


                        User currentUser  = dataSnapshot.getValue(User.class);

                        if (currentUser == null){// No tengo usuario registrado
                            String email = helper.getAuthUserEmail();
                            if ( email != null) {
                                currentUser = new User();
                                myUserReference.setValue(currentUser);
                            }
                        }

                        helper.changeUserConnectionStatus(User.ONLINE);
                        postEvent(LoginEvent.onSignInSuccess);
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {
                        Log.e(TAG ,"onAuthenticated -->onCancelled" );

                    }
                });

            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                Log.e(TAG, "onAuthenticationError");
                postEvent(LoginEvent.onSignInError , firebaseError.getMessage());
            }
        });
    }

    @Override
    public void checkSession() {
        Log.e(TAG, "checkSession");

        postEvent(LoginEvent.onFailedRecoverSession);

    }

    private void postEvent(int type, String errorMessage) {
        LoginEvent loginEvent = new LoginEvent();
        loginEvent.setEventType(type);
        if (errorMessage != null) {
            loginEvent.setErrorMessage(errorMessage);
        }

        EvenBus evenBus = GreenRobotEventBus.getInstance();
        evenBus.post(loginEvent);

    }

    private void postEvent(int type) {
        postEvent(type, null);

    }
}
