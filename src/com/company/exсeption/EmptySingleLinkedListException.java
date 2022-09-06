package com.company.exсeption;

public class EmptySingleLinkedListException extends Exception {

    public EmptySingleLinkedListException() {
        super("The list is empty!!!");
    }
}
