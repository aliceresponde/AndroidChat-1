package com.example.andrearodriguez.androidchat.login;

import android.util.Log;

import com.example.andrearodriguez.androidchat.lib.EvenBus;
import com.example.andrearodriguez.androidchat.lib.GreenRobotEventBus;
import com.example.andrearodriguez.androidchat.login.events.LoginEvent;
import com.example.andrearodriguez.androidchat.login.ui.LoginView;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by andrearodriguez on 6/9/16.
 */
public class LoginPresenterImpl implements LogingPresenter{

    private EvenBus eventBus;
    private LoginView loginView;
    private LoginInteractor loginInteractor;
    private static String TAG  = LoginPresenterImpl.class.getSimpleName();

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        this.loginInteractor =new LoginInteractorImpl();
        this.eventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void onCreate() {
        Log.e("LoginPresenterImpl", "onCreate");
        eventBus.register(this);

    }

    @Override
    public void onDestroy() {
        loginView = null;
        eventBus.unregister(this);

    }

    @Override
    public void checkForAuthenticationUser() {
        if(loginView != null){
            loginView.disableInputs();
            loginView.showProgress();
        }
        loginInteractor.checkSession();
    }

    @Override
    public void validateLogin(String email, String password) {
        if(loginView != null){
            loginView.disableInputs();
            loginView.showProgress();
        }
        loginInteractor.doSignIn(email, password);

    }

    @Override
    public void registerNewUser(String email, String password) {

        if(loginView != null){
            loginView.disableInputs();
            loginView.showProgress();
        }
        loginInteractor.doSignUp(email, password);
    }

    @Subscribe
    @Override
    public void onEventMainThread(LoginEvent event) {
        Log.e("LoginPresenterImpl","onEventMainThread");
        Log.e( TAG, "onEventMainThread event"  +  event.getEventType() + " message --> " + event.getErrorMessage() );
        switch (event.getEventType()){
            case LoginEvent.onSignInSuccess:
                onSignInSucces();
                break;
            case LoginEvent.onSignInError:
                onSignInError(event.getErrorMessage());
                break;
            case LoginEvent.onSignUpSuccess:
                onSignUpSucces();
                break;
            case LoginEvent.onSignUpError:
                onSignUpError(event.getErrorMessage());
                break;
            case LoginEvent.onFailedRecoverSession:
                onFailedRecoverSession();
                break;
        }
    }

    private void onFailedRecoverSession() {
        if(loginView != null){
            loginView.hideProgress();
            loginView.enableInputs();
        }

    }

    private void onSignInSucces(){
        if(loginView != null){
            loginView.navigationToMainScreen();

        }

    }
    private void onSignUpSucces(){
        if(loginView != null){
            loginView.newUserSuccess();

        }

    }
    private void onSignInError(String error){
        if(loginView != null){
            loginView.hideProgress();
            loginView.enableInputs();
            loginView.loginError(error);

        }

    }
    private void onSignUpError(String error){
        if(loginView != null){
            loginView.hideProgress();
            loginView.enableInputs();
            loginView.newUserError(error);
        }

    }
}
