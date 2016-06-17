package com.example.andrearodriguez.androidchat.login.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.example.andrearodriguez.androidchat.R;
import com.example.andrearodriguez.androidchat.contactslist.ui.ContactListActivity;
import com.example.andrearodriguez.androidchat.login.LoginPresenterImpl;
import com.example.andrearodriguez.androidchat.login.LogingPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginView {

    @BindView(R.id.txtEmail)
    EditText txtEmail;
    @BindView(R.id.txtPassword)
    EditText txtPassword;
    @BindView(R.id.btnSignin)
    Button btnSignin;
    @BindView(R.id.btnSignInUp)
    Button btnSignInUp;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.layoutMainContainer)
    RelativeLayout Container;

    private LogingPresenter logingPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        logingPresenter = new LoginPresenterImpl(this);
        logingPresenter.onCreate();
        logingPresenter.checkForAuthenticationUser();
    }

    @Override
    protected void onDestroy() {
        logingPresenter.onDestroy();
        super.onDestroy();
    }

    @OnClick({R.id.btnSignin, R.id.btnSignInUp})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSignin:
                handleSignin();
                break;
            case R.id.btnSignInUp:
                handleSingnInUp();
                break;
        }
    }

    private void handleSingnInUp() {

        Log.e("AndroidChatAppSignup", txtEmail.getText().toString());
    }

    private void handleSignin() {
        Log.e("AndroidChatAppSignin", txtEmail.getText().toString());
    }

    @Override
    public void enableInputs() {
        setInputs(true);

    }

    @Override
    public void disableInputs() {

        setInputs(false);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);

    }

    @OnClick(R.id.btnSignInUp)
    @Override
    public void handleSignUp() {
        logingPresenter.registerNewUser(txtEmail.getText().toString(),
                            txtPassword.getText().toString());

    }

    @OnClick(R.id.btnSignin)
    @Override
    public void handleSignIn() {
        logingPresenter.validateLogin(txtEmail.getText().toString(),
                            txtPassword.getText().toString());

    }

    @Override
    public void navigationToMainScreen() {
        startActivity (new Intent(this, ContactListActivity.class));

    }

    @Override
    public void loginError(String error) {
        txtPassword.setText("");
        String msgError = String.format(getString(R.string.loging_error_messagge_signin, error));

        txtPassword.setError(msgError);

    }

    @Override
    public void newUserSuccess() {
        Snackbar.make(Container , getString(R.string.loging_notice_signin),Snackbar.LENGTH_SHORT).show();

    }

    @Override
    public void newUserError(String error) {
        txtPassword.setText("");
        String msgError = String.format(getString(R.string.loging_error_messagge_signup, error));

        txtPassword.setError(msgError);

    }
    private void setInputs (boolean enabled){
        txtEmail.setEnabled(enabled);
        txtPassword.setEnabled(enabled);
        btnSignin.setEnabled(enabled);
        btnSignInUp.setEnabled(enabled);
    }
}
