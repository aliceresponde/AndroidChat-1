package com.example.andrearodriguez.androidchat.contactslist.events;

import com.example.andrearodriguez.androidchat.entities.User;

/**
 * Created by alice on 6/15/16.
 * Creeo constantes para los eventos  sobre los contactos
 *
 * Requiero los siguientes elementos, un User, asi como el tipo de evento
 */
public class ContactListenerEvent {
    public final static  int onContactAdded =0;
    public final static  int onContactChanged =1;
    public final static  int onContactRemoved =2;

    private User user;
    private int eventType;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }
}
