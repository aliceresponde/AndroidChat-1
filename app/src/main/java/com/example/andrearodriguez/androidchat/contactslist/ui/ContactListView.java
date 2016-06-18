package com.example.andrearodriguez.androidchat.contactslist.ui;

import com.example.andrearodriguez.androidchat.entities.User;

/**
 * Created by alice on 6/15/16.
 */

public interface ContactListView {
    void onContactAdded(User user);
    void onContactChanged(User user);
    void onContactRemoved(User user);
}
