package com.example.andrearodriguez.androidchat.contactslist;

import com.example.andrearodriguez.androidchat.contactslist.events.ContactListenerEvent;
import com.example.andrearodriguez.androidchat.contactslist.ui.ContactListView;
import com.example.andrearodriguez.androidchat.lib.EvenBus;
import com.example.andrearodriguez.androidchat.lib.GreenRobotEventBus;

/**
 * Created by alice on 6/17/16.
 */

public class ContactListPresenterImpl implements ContactListPresenter {
    private EvenBus eventBus;
    private ContactListInteractor interactor;
    private ContactListSessionInteractor sessionInteractor;
    private ContactListView view;

    public ContactListPresenterImpl(ContactListView view) {
        this.view = view;
        eventBus  = new GreenRobotEventBus();
        interactor = new ContactListInteractorImp();
        sessionInteractor  = new ContactListSessionInteractorImp();

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void signOff() {

    }

    @Override
    public String getCurrentUserEmail() {
        return null;
    }

    @Override
    public void removeContact(String email) {

    }

    @Override
    public void onEventMainthread(ContactListenerEvent event) {

    }
}
