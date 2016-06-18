package com.example.andrearodriguez.androidchat.contactslist;

import com.example.andrearodriguez.androidchat.contactslist.events.ContactListenerEvent;
import com.example.andrearodriguez.androidchat.contactslist.ui.ContactListView;
import com.example.andrearodriguez.androidchat.entities.User;
import com.example.andrearodriguez.androidchat.lib.EvenBus;
import com.example.andrearodriguez.androidchat.lib.GreenRobotEventBus;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by alice on 6/17/16.
 */

public class ContactListPresenterImpl implements ContactListPresenter {
    private EvenBus eventBus;
    private ContactListInteractor listInteractor;
    private ContactListSessionInteractor sessionInteractor;
    private ContactListView view;

    public ContactListPresenterImpl(ContactListView view) {
        this.view = view;
        eventBus  = new GreenRobotEventBus();
        listInteractor = new ContactListInteractorImp();
        sessionInteractor  = new ContactListSessionInteractorImp();

    }

    /**
     * Registro al  bus contactListEvent
     */
    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onResume() {
        sessionInteractor.changeConnectionStatus(User.ONLINE);
        listInteractor.subscribe();
    }

    @Override
    public void onPause() {
       sessionInteractor.changeConnectionStatus(User.OFFLINE);
       listInteractor.unsubscribe();
    }


    // TODO :  preguntar porque no se destruyen ambos interactor, esta quedando el del sesion
    /**
     * Desde  el onDestroy
     * desregistro del bus contactLsitEvent
     * destruyo el listener del listInteractor
     * pongo en null la vista
     */
    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        listInteractor.destroyListener();
        view =null;
    }

    /**
     * voy a offline
     * desuscribo del listInteractor
     * destruyo el listener de listInteractor
     * signoff de la session
     */
    @Override
    public void signOff() {
        sessionInteractor.changeConnectionStatus(User.OFFLINE);
        listInteractor.unsubscribe();
        listInteractor.destroyListener();
        sessionInteractor.signOff();
    }

    @Override
    public String getCurrentUserEmail() {
        return sessionInteractor.getCurrentUserEmail();
    }

    @Override
    public void removeContact(String email) {
        listInteractor.removeContact(email);
    }

    /**
     * accciones a las q la vista debe reaccionar view.*
     * Nota:  el metodo onEventMainThread llamado en el presenter , para los casos de los eventos
     *        requiere uns @subscribe  EventBus 3.0.0
     * @param event
     */
    @Override
    @Subscribe
    public void onEventMainthread(ContactListenerEvent event) {
        User user = event.getUser();
        switch (event.getEventType()) {
            //Casos dependen de los tipos de evento  -- constanetes

            case ContactListenerEvent.onContactAdded:
                onContactAdded(user);
                break;
            case ContactListenerEvent.onContactChanged:
                onContactChanged(user);
                break;
            case ContactListenerEvent.onContactRemoved:
                onContactRemoved(user);
                break;
        }
    }

//    ================ For EventTypes - Contact operations on View ===============================

    private void onContactAdded (User user){
        if (view != null) {
            view.onContactAdded(user);
        }
    }

    private void onContactChanged (User user){
        if (view != null) {
            view.onContactChanged(user);
        }
    }

    private void onContactRemoved (User user){
        if (view != null) {
            view.onContactRemoved(user);
        }
    }


}

