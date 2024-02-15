package utils;

import java.util.Random;

public class MathUtils {

    public static int generateRandomNumber() {
        final Random rand = new Random();
        final int min = 10000;
        final int max = 99999;
        return rand.nextInt((max - min) + 1) + min;
    }
}
