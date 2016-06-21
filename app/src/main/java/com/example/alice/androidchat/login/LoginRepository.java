package com.example.alice.androidchat.login;

/**
 * Created by andrearodriguez on 6/9/16.
 */
public interface LoginRepository {
    void signUp (String email, String password);
    void SignIn (String email, String password);
    void checkSession();
}
