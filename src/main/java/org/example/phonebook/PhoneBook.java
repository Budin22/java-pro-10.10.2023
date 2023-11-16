package org.example.phonebook;

import java.util.List;

public interface PhoneBook<T> {
    void add(T contact);

    T find(String name);

    List<T> findAll(String name);
}
