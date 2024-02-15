package utils;

import java.util.Date;
import java.util.Random;

public class TestDataGenerator {

    public static String emailAdres() {

        return String.format("testmijn+%d@denhaag.nl", (new Random().nextInt(9999 - 1000) + 1000));
    }

    public static String telefoonNummer() {

        return String.format("06%d", (new Random().nextInt(99999999 - 10000000) + 10000000));
    }

    public static String getNewZaakIdentificatie() {
        return String.format("testCase%s", new Date().getTime());
    }
}
