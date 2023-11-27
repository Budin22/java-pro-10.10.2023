package org.example.coffe.order;

public interface OrderBoard<T> {
    void addOrder(String name);
    T deliver();
    T deliver(int num);
    void printOrdersInLine();

}
