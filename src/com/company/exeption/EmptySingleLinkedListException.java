package com.company.exeption;

public class EmptySingleLinkedListException extends Exception {

    public EmptySingleLinkedListException() {
        super("The list is empty!!!");
    }
}
