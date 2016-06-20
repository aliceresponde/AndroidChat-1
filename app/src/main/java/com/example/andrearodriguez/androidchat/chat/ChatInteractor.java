package com.example.andrearodriguez.androidchat.chat;

/**
 * Created by alice on 6/19/16.
 * manage messages
 */

public interface ChatInteractor {
    void sendMessages(String msg);
    void setRecipient(String recipient);

    void subscribe();
    void unsubscribe();
    void destroyListener();

}
