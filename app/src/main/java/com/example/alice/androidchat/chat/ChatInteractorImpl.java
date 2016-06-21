package com.example.alice.androidchat.chat;

/**
 * Created by alice on 6/19/16.
 */
public class ChatInteractorImpl implements ChatInteractor {

    private  ChatRepository repository;

    public ChatInteractorImpl() {
        repository  = new ChatRepositoryImpl();
    }

    @Override
    public void sendMessages(String msg) {
        repository.sendMessages(msg);
    }

    @Override
    public void setRecipient(String recipient) {
        repository.setRecipient(recipient);
    }

    @Override
    public void subscribe() {
        repository.subscribe();
    }

    @Override
    public void unsubscribe() {
        repository.unsubscribe();
    }

    @Override
    public void destroyListener() {
        repository.destroyListener();
    }
}
