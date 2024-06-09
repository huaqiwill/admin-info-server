package com.example.demo.utils;

public class LoginUser {
    private static int visitCount = 0;

    public static void addVisitCount() {
        LoginUser.visitCount++;
    }

    public static int getVisitCount() {
        return LoginUser.visitCount;
    }
}
