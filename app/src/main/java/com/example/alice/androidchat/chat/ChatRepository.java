package com.example.alice.androidchat.chat;

/**
 * Created by alice on 6/19/16.
 */

public interface ChatRepository {

    //    ==========ChatInteractor==============================================
    void sendMessages(String msg);

    void setRecipient(String recipient);

    void subscribe();

    void unsubscribe();

    void destroyListener();

//    ===========ChatSessionInteractor===========================================

    void changeConnectionStatus(boolean online);

}
