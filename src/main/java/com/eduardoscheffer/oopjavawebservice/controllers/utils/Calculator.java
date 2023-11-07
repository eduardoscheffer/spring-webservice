package com.eduardoscheffer.oopjavawebservice.controllers.utils;

public class Calculator {

    public int add (int a, int b) {
        if (a < 0) throw new IllegalArgumentException("First argument cannot be negative");
        return a + b;
    }

}
