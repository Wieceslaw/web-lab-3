package ru.ifmo.se.lab3.services;

public final class CheckService {
    public static boolean check(double x, double y, double r) {
        if (x > 0 && y > 0) {
            return Math.sqrt((x * x) + (y * y)) <= r;
        } else if (x <= 0 && y >= 0) {
            return x >= -r / 2 && y <= r;
        } else if (x <= 0 && y <= 0) {
            return y >= -2 * x - r;
        } else {
            return false;
        }
    }
}
