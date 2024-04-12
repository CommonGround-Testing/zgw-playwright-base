package utils;

import com.microsoft.playwright.CDPSession;
import com.microsoft.playwright.options.Cookie;
import runner.ZGWTestRunner;

import java.util.HashMap;
import java.util.List;
import java.util.function.BiConsumer;

public class CacheLogin {

    public static HashMap<String, List<Cookie>> cookies = new HashMap<>();

    public static void session(BiConsumer<String, String> consumer, String username, String password) {
        if (cookies.get(username) == null) {
            consumer.accept(username, password);
            cookies.put(username, ZGWTestRunner.getContext().cookies());
        } else {
            ZGWTestRunner.getContext().addCookies(cookies.get(username));
        }
        CDPSession cdp = ZGWTestRunner.getContext().newCDPSession(ZGWTestRunner.getPage());
        
    }
}
