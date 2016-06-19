package com.example.andrearodriguez.androidchat.addcontact.iu;

/**
 * Created by alice on 6/19/16.
 */

public interface AddContactView {

    void showInput();
    void hintInput();
    void showProgressBar();
    void hideProgressBar();

    void contactAdded();
    void contactNotAdded();


}
