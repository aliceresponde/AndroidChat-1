package com.example.andrearodriguez.androidchat.addcontact;

import android.util.Log;

/**
 * Created by alice on 6/19/16.
 */
public class AddContactInteractorImpl implements AddContactInteractor {
    private  AddContactRepository repository;

    public AddContactInteractorImpl() {
        repository = new AddContactRepositoryImpl();
    }

    @Override
    public void excecute(String email) {
        Log.i("clickAdd" , "INTERACTOR");

        repository.addContact(email);
    }
}
