package com.example.alice.androidchat.signup.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.example.alice.androidchat.R;
import com.example.alice.androidchat.contactslist.ui.ContactListActivity;
import com.example.alice.androidchat.login.LoginPresenterImpl;
import com.example.alice.androidchat.login.LogingPresenter;
import com.example.alice.androidchat.login.ui.LoginView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends AppCompatActivity implements LoginView {

    @BindView(R.id.inputEmail)
    EditText inputEmail;
    @BindView(R.id.txtPassword)
    EditText txtPassword;
    @BindView(R.id.wraperpassword)
    TextInputLayout wraperpassword;
    @BindView(R.id.btnSignInUp)
    Button btnSignInUp;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.layoutMainContainer)
    RelativeLayout layoutMainContainer;

    private LogingPresenter logingPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setTitle(R.string.signup_title);
        ButterKnife.bind(this);


    }

    @Override
    protected void onResume() {
        super.onResume();
        logingPresenter = new LoginPresenterImpl(this);
        logingPresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        logingPresenter.onDestroy();
    }

    @Override
    protected void onDestroy() {
        logingPresenter.onDestroy();
        super.onDestroy();
    }

//    ======================= interface ===================================================

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
        logingPresenter.registerNewUser(inputEmail.getText().toString(), txtPassword.getText().toString());
    }

    @Override
    public void handleSignIn() {
        throw new UnsupportedOperationException(getString(R.string.signup_error_unsuported_operation));
    }

    @Override
    public void navigationToMainScreen() {
        startActivity (new Intent(this, ContactListActivity.class));
    }

    @Override
    public void loginError(String error) {
        throw new UnsupportedOperationException(getString(R.string.signup_error_unsuported_operation));
    }

    @Override
    public void newUserSuccess() {
        Snackbar.make(layoutMainContainer, getString(R.string.loging_notice_signin), Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void newUserError(String error) {
        txtPassword.setText("");
        String msgError = String.format(getString(R.string.loging_error_messagge_signup, error));
        txtPassword.setError(msgError);
    }




    private void setInputs(boolean enabled) {
        inputEmail.setEnabled(enabled);
        txtPassword.setEnabled(enabled);
        btnSignInUp.setEnabled(enabled);
    }
}
