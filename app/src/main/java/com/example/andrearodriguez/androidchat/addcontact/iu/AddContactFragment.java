package com.example.andrearodriguez.androidchat.addcontact.iu;


import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.andrearodriguez.androidchat.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddContactFragment extends DialogFragment implements AddContactView {


    @BindView(R.id.inputEmail)
    EditText inputEmail;
    @BindView(R.id.progressBar)
    ContentLoadingProgressBar progressBar;

    public AddContactFragment() {
        // Required empty public constructor
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_add_contact, null);
        ButterKnife.bind(this , view);

        //Creo el dialogo
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        AlertDialog dialog = builder.create();
        return  dialog;
    }

    @Override
    public void showInput() {
        inputEmail.setVisibility(View.VISIBLE);
    }

    @Override
    public void hintInput() {
        inputEmail.setVisibility(View.GONE);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void contactAdded() {
        Toast.makeText(getActivity(), R.string.addcontact_message_contactAdded, Toast.LENGTH_LONG).show();
    }

    @Override
    public void contactNotAdded() {
        inputEmail.setText("");
        inputEmail.setError(getString(R.string.addcontact_error_message));
    }


}
