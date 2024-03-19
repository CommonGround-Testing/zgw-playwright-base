package utils;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Route;
import runner.ZGWTestRunner;

import java.io.IOException;

public class MockRequests {

    /**
     * Mock een graphql request zodat je niet afhankelijk bent van de api call
     *
     * @param page
     * @param query
     * @param pathToMockJson
     */
    public static void mockRequestWithQuery(Page page, String query, String pathToMockJson) {
        page.route(ZGWTestRunner.getBaseUrl() + "/graphql", route -> {
            if (isQueryInRequestBody(query, route)) {
                mockResponseWithDataInJson(pathToMockJson, route);
            } else {
                route.resume();
            }
        });
    }

    private static void mockResponseWithDataInJson(String pathToMockJson, Route route) {
        try {
            route.fulfill(new Route.FulfillOptions()
                    .setBody(FiletoJson.convert(pathToMockJson)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean isQueryInRequestBody(String query, Route route) {
        return route.request().postData().contains(query);
    }
}
