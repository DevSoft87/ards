package com.devsoft.contacts.model;

import java.util.List;

/**
 * Created by Sergey on 30.04.2016.
 */
public class Contacts {
    private List<Contact> contacts = null;

    public Contacts() {
    }

    public Contacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
}
