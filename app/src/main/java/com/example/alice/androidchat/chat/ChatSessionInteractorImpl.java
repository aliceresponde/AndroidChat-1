package com.example.alice.androidchat.chat;

/**
 * Created by alice on 6/19/16.
 */
public class ChatSessionInteractorImpl implements ChatSessionInteractor {
    private  ChatRepository repository;

    public ChatSessionInteractorImpl() {
        repository  = new ChatRepositoryImpl();

    }

    @Override
    public void changeConnectionStatus(boolean online) {
        repository.changeConnectionStatus(online);
    }
}
