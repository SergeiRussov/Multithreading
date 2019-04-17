package util;

public class Util {

    public static void sleep() {

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.getMessage();
        }
    }
}
