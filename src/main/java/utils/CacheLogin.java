package utils;

import com.microsoft.playwright.options.Cookie;
import runner.ZGWTestRunner;

import java.util.HashMap;
import java.util.List;

public class CacheLogin {

    public static HashMap<String, List<Cookie>> cookies = new HashMap<>();


    /**
     * Method that stores your cookies in a hashmap
     *
     * @param storageName String under which you want to store your cookies in the hashmap
     * @param runnable    A Runnable that executes your login actions, we advise a lambda arrow function with the steps in order to login
     *                    <p>For exmaple:</p>
     *                    <pre> {@code CacheLogin.session("user1Klantportaal", () -> mijnZakenSteps.Login(digidUser));}
     *                    </pre>
     */
    public static void session(String storageName, Runnable runnable) {
        if (cookies.get(storageName) == null) {
            runnable.run();
            cookies.put(storageName, ZGWTestRunner.getContext().cookies());
        } else {
            ZGWTestRunner.getContext().addCookies(cookies.get(storageName));
        }
    }
}
