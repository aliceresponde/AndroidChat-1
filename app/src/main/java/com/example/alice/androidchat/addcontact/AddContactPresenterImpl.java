package com.example.alice.androidchat.addcontact;

import android.util.Log;

import com.example.alice.androidchat.addcontact.events.AddContactEvent;
import com.example.alice.androidchat.addcontact.iu.AddContactView;
import com.example.alice.androidchat.lib.EvenBus;
import com.example.alice.androidchat.lib.GreenRobotEventBus;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by alice on 6/19/16.
 */
public class AddContactPresenterImpl implements  AddContactPresenter{
    private AddContactView view;
    private EvenBus eventBus;
    private AddContactInteractor interactor;



    public AddContactPresenterImpl(AddContactView view) {
        this.view = view;
        this.eventBus  = GreenRobotEventBus.getInstance();
        this.interactor = new AddContactInteractorImpl();
    }


//     =========================EVENT BUS =============================================
    @Override
    public void onShow() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        view =null;
        eventBus.unregister(this);
    }

    @Subscribe
    @Override
    public void onEventMainThread(AddContactEvent event) {
        Log.i("onEventMainThread" , "Fragment  ... error? " +  event.isError());
        if (view != null ){
            view.hideProgressBar();
            view.showInput();

            if (event.isError()){
                view.contactNotAdded();
            }else {
                view.contactAdded();
            }
        }
    }

//    =================================== INTERACTOR ===================================

    @Override
    public void addContact(String email) {
        Log.i("clickAdd" , "PRESENTER IMP");
        if (view != null){
            view.hintInput();
            view.showProgressBar();
        }

        interactor.excecute(email);
    }
}
