package com.biblioteca.listener;

public interface Listenable {

    void addListener(Listener listener);


    default void removeListener() {

    }

}
