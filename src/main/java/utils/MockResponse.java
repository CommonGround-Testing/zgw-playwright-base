package utils;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Route;

import java.io.IOException;

public abstract class MockResponse {

    /**
     * Mock de taken response
     * The path to the json file should contain the full path to the file starting from src
     *  example: src/test/resources/fileWithMockData.json
     *
     * @param page
     * @param path String with the url to intercept
     *             example: full api url is https://myadres/myapi?myparameter=muvalue then use ** /myapi**
     * @param jsonfile with mock data
     */
    protected static void mockResponse(Page page, String path, String jsonfile) {
        page.route(path, route -> {
            mockResponseWithDataInJson(jsonfile, route);
        });
    }

    /**
     * Mock een lege taken response
     *
     * @param page
     */
    protected static void mockLegeResponse(Page page, String api) {
        page.route(api, route -> {
            route.fulfill(new Route.FulfillOptions()
                    .setBody("[]"));
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
}
