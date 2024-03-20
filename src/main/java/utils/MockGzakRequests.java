package utils;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Route;

import java.io.IOException;

public class MockGzakRequests {

    private static final String MOCK_DIRECTORY = "src/test/java/tests/gzak/mockdata/";

    /**
     * Mock de taken response
     *
     * @param page
     * @param jsonfile with mock data
     */
    public static void mockTakenResponse(Page page, String jsonfile) {
        page.route("**/task**", route -> {
            mockResponseWithDataInJson(MOCK_DIRECTORY + jsonfile, route);
        });
    }

    /**
     * Mock een lege taken response
     *
     * @param page
     */
    public static void mockLegeTakenResponse(Page page) {
        page.route("**/task**", route -> {
            mockResponseWithDataInJson(MOCK_DIRECTORY + "emptyResponse.json", route);
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
