package com.example.andrearodriguez.androidchat.contactslist;

import com.example.andrearodriguez.androidchat.contactslist.events.ContactListenerEvent;

/**
 * Created by alice on 6/15/16.
 */

public interface ContactListPresenter {
    void  onCreate();
    void  onResume();
    void  onPause();
    void  onDestroy();

    void  signOff();
    String getCurrentUserEmail();
    void  removeContact( String email);
    void  onEventMainthread(ContactListenerEvent event);
}
