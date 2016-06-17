package com.example.andrearodriguez.androidchat.contactslist;

/**
 * Created by alice on 6/16/16.
 * Para los eventos de la lista
 */

public interface ContactListInteractor {
    void  subscribe();
    void  unsubscribe();
    void  destroyListener();

    //este pudo ponerse en un interactor propio. aparte
    void  removeContact(String email);
}
