package com.example.alice.androidchat.contactslist;

/**
 * Created by alice on 6/17/16.
 */
public class ContactListInteractorImp implements ContactListInteractor {

    private  ContactListRepository repository;

    public ContactListInteractorImp() {
        repository = new ContactListRepositoryImp();
    }

    @Override
    public void subscribe() {
        repository.subscribeToContactListEvents();
    }

    @Override
    public void unsubscribe() {
        repository.unsubscribe();
    }

    @Override
    public void destroyListener() {
        repository.destroyListener();
    }

    @Override
    public void removeContact(String email) {
        repository.removeContact(email);
    }
}
