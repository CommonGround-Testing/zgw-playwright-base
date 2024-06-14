package utils;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Route;
import org.json.JSONObject;
import runner.ZGWTestRunner;

import java.io.IOException;
import java.util.Map;

import static runner.ZGWTestRunner.page;

public class MockGraphqlRequest{

    /**
     * Mock een graphql request zodat je niet afhankelijk bent van de api call
     * The path to the json file should contain the full path to the file starting from src
     *  example: src/test/resources/fileWithMockData.json
     *
     * @param page with Page
     * @param query with string of graphql payload operationName which is used for matching the request
     * @param pathToMockJson with string of the full path to the json file (example: src/test/resources/fileWithMockData.json)
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

    /**
     * @param subDirectory with the subdirectory of the response folder
     *                     by default the subDirectory should be in the src/test/resources/
     * @param queries   with the Map
     *                  The map should contain the following
     *                  - Key with the query parameter of the graphql
     *                  - Value with the json file containing the reponse
     */
    public static void mockMultipleGraphql(String subDirectory, Map<String, String> queries) {
        page.route(ZGWTestRunner.getBaseUrl() + "/graphql", route -> {
            var operationName = new JSONObject(route.request().postData()).get("operationName");
            if (!queries.containsKey((String)operationName)){
                route.resume();
                return;
            }
            try {
                route.fulfill(new Route.FulfillOptions()
                        .setBody(FiletoJson.convert("src/test/resources/" + subDirectory + "/" + queries.get((String)operationName))));
            } catch (IOException e) {
                throw new RuntimeException(e);
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
