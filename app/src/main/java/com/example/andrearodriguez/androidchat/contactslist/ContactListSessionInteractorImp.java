package com.example.andrearodriguez.androidchat.contactslist;

/**
 * Created by alice on 6/17/16.
 * llamo al repositorio para q implemente estos metodos
 */
public class ContactListSessionInteractorImp implements ContactListSessionInteractor {
    private ContactListRepository  repository;

    /**
     * inicializar este repositorio en el constructor, no es la mejor de las ideas, para eso
     * se  hace uso de Dagger e injeccion de dependencias
     */
    public ContactListSessionInteractorImp() {
        repository  = new ContactListRepositoryImp();
    }

    @Override
    public void signOff() {
        repository.signOff();

    }

    @Override
    public String getCurrentUserEmail() {
        return repository.getCurrentUserEmail();
    }

    @Override
    public void changeConnectionStatus(boolean online) {
        repository.changeConnectionStatus(online);
    }
}
