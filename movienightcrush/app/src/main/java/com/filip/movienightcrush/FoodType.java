package com.filip.movienightcrush;

/**
 * Created by RE1010 on 2017-10-10.
 */

public enum FoodType {

    POPCORN(0), //0
    NACHO(1), //1
    CHOCOLATEBAR(2), //2
    POPDRINK(3), //3
    BEER(4), //4
    TICKET(5), //5
    HOTDOG(6), //6
    FRIES(7), //7
    COUNT(8), //8
    EMPTY(9); // 9

    private final int value;

    FoodType(int val)
    {
        value = val;
    }

    public int getValue()
    {
        return value;
    }



}
