package org.example.phonebook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneBook {
    private final Map<String, ArrayList<Contact>> contacts;

    public PhoneBook() {
        this.contacts = new HashMap<>();
    }

    public void add(Contact contact) {
        String name = contact.getName();
        if (contacts.containsKey(name)) {
            ArrayList<Contact> phones = contacts.get(name);
            phones.add(contact);
        } else {
            ArrayList<Contact> contactsList = new ArrayList<>();
            contactsList.add(contact);
            contacts.put(name, contactsList);
        }
    }

    public Contact find(String name) {
        ArrayList<Contact> contact = contacts.get(name);
        if (contact != null) {
            return contact.get(0);
        }
        return null;
    }

    public List<Contact> findAll(String name) {
        return contacts.get(name);
    }

}
