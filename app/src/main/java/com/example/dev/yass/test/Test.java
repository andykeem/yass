package com.example.dev.yass.test;

import android.util.Log;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by dev on 5/23/16.
 */
public class Test {

    protected static final String TAG = "Test";

    protected static void log(String s) {
        Log.d(TAG, s);
    }

    public static void trigonometry() {
        /*Log.d(TAG, "PI: " + Math.PI);
        Random rand = new Random();
        String s = "";
        for (int i = 0; i < 100; i++) {
            int n = (int) (180 / Math.PI);
            double angle = rand.nextInt(n) - 30;
            s += angle + " | ";
        }
        Log.d(TAG, "angle: " + s);*/

        /*Random rand = new Random();
        double angle = (rand.nextDouble() * Math.PI / 3d) - Math.PI / 6d;
        Log.d(TAG, "angle: " + angle);
        Log.d(TAG, "cos(" + angle + "): " + Math.cos(angle));
        Log.d(TAG, "sin(" + angle + "): " + Math.sin(angle));*/
    }

    public static void negative() {
        int n = -100;
        int m = -n;
        log(m + "");
    }

    public static void angle() {
        int size = 10;
        Random rand = new Random();
        double[] randoms = new double[size];
        double[] radians = new double[size];
        double[] degrees = new double[size];
        for (int i = 0; i < size; i++) {
            double rd = rand.nextDouble();
            double radian = ((rd * Math.PI / 3) - (Math.PI / 6));
            double degree = ((radian * 180) / Math.PI);
            randoms[i] = rd;
            radians[i] = radian;
            degrees[i] = degree;
        }
//        log("randoms: " + Arrays.toString(randoms));
//        log("radians: " + Arrays.toString(radians));
        log("degrees: " + Arrays.toString(degrees));
    }

    public static void test() {

//        negative();
        angle();
    }

    public static void main(String[] args) {
        test();
    }
}
