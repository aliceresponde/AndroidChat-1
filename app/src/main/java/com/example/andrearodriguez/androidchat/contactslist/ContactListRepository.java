package com.example.andrearodriguez.androidchat.contactslist;

import com.example.andrearodriguez.androidchat.domain.FirebaseHelper;
import com.google.firebase.database.ChildEventListener;

/**
 * Created by alice on 6/16/16.
 */

public interface ContactListRepository {




    //session
    void signOff();

    String getCurrentUserEmail();

    void changeConnectionStatus(boolean online);

    //interactor
    void subscribeToContactListEvents();

    void unsubscribe();

    void destroyListener();

    //este pudo ponerse en un interactor propio. aparte
    void removeContact(String email);
}
