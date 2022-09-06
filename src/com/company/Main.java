package com.company;

import com.company.exeption.EmptySingleLinkedListException;
import com.company.service.SingleList;

public class Main {
    public static void main(String[] args) throws EmptySingleLinkedListException {

        SingleList<Integer> emptyList = new SingleList<>();

        Integer[] nums = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        SingleList<Integer> list = new SingleList<>(nums);

        list.addFirst(0);
        list.addLast(10);
        System.out.println(list);

        list.insertByIndex(8, 11);
        System.out.println(list);

        list.delFirst();
        list.delLast();
        list.deleteByIndex(1);

        System.out.println(list);

        list.changeByIndex(4, 8);
        System.out.println("Single list after changing: " + list);
        System.out.println("The length of empty list: " + emptyList.getSize());
        System.out.println("The length of list: " + list.getSize());
        System.out.println("Is empty list empty: " + emptyList.isListEmpty());
        System.out.println("Is list empty: " + list.isListEmpty());
    }
}