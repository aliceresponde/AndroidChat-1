package com.example.andrearodriguez.androidchat.contactslist;

/**
 * Created by alice on 6/16/16.
 */

public interface ContactListSessionInteractor {
    String getCurrentUserEmail();
    void changeConnectionStatus(boolean online);
    void signOff();
}
