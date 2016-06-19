package com.example.andrearodriguez.androidchat.addcontact;

import com.example.andrearodriguez.androidchat.addcontact.events.AddContactEvent;

/**
 * Created by alice on 6/19/16.
 */

public interface AddContactPresenter {
    void onShow();
    void onDestroy();

    void addContact(String email);
    void onEventMainThread(AddContactEvent event);


}
