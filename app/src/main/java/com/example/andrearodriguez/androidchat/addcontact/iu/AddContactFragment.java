package com.example.andrearodriguez.androidchat.addcontact.iu;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.andrearodriguez.androidchat.R;
import com.example.andrearodriguez.androidchat.addcontact.AddContactPresenter;
import com.example.andrearodriguez.androidchat.addcontact.AddContactPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddContactFragment extends DialogFragment implements AddContactView, DialogInterface.OnShowListener {


    @BindView(R.id.inputEmail)
    EditText inputEmail;
    @BindView(R.id.progressBar)
    ContentLoadingProgressBar progressBar;

    private AddContactPresenter presenter;
    private Unbinder binder;


    public AddContactFragment() {
        presenter = new AddContactPresenterImpl( this );
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle(R.string.addcontact_message_title)
                .setPositiveButton(R.string.addcontact_message_add, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton(R.string.addcontact_message_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });


        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_add_contact, null);
        binder = ButterKnife.bind(this , view);

        //Creo el dialogo
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.setOnShowListener(this);

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
        Log.i("clickAdd", "contactAdded");
        Toast.makeText(getActivity(), R.string.addcontact_message_contactAdded, Toast.LENGTH_LONG).show();
        dismiss();
    }

    @Override
    public void contactNotAdded() {
        inputEmail.setText("");
        inputEmail.setError(getString(R.string.addcontact_error_message));
    }

    /**
     * control when  show or dismiss dialog
     * @param dialogInterface
     */
    @Override
    public void onShow(DialogInterface dialogInterface) {
        final  AlertDialog dialog = (AlertDialog) getDialog();
        if (dialog != null ){
            Button positiveBtn  =  dialog.getButton(Dialog.BUTTON_POSITIVE);
            Button negativeBtn  =  dialog.getButton(Dialog.BUTTON_NEGATIVE);

            positiveBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // accion q el peresentador ala
                    Log.i("clickAdd" , "Fragment");
                presenter.addContact(inputEmail.getText().toString());

                }
            });

            negativeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });

        }
        presenter.onShow();
    }


    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binder.unbind();
    }
}
