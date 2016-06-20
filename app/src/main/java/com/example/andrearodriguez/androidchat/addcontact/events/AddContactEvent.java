package com.example.andrearodriguez.androidchat.addcontact.events;

/**
 * Created by alice on 6/19/16.
 */
public class AddContactEvent {
    boolean error = false;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
