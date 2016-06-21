package com.example.alice.androidchat.login;

import com.example.alice.androidchat.login.events.LoginEvent;

/**
 * Created by andrearodriguez on 6/9/16.
 */
public interface LogingPresenter {
    void onCreate();
    void onResume();
    void onPause();
    void onDestroy();
    void checkForAuthenticationUser();
    void validateLogin(String email, String password);
    void registerNewUser(String email, String password);
    void onEventMainThread(LoginEvent event);
}
