package utils;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Route;

import java.io.IOException;

public class MockGzakRequest extends MockResponse {

    private static String TAKEN_API_PATH = "**/task**";

    /**
     * Mock de taken response
     * The path to the json file should contain the full path to the file starting from src
     *  example: src/test/resources/fileWithMockData.json
     *
     * @param page
     * @param jsonfile with mock data
     */
    public static void mockTakenResponse(Page page, String jsonfile) {
        mockResponse(page, TAKEN_API_PATH, jsonfile);
    }

    /**
     * Mock een lege taken response
     *
     * @param page
     */
    public static void mockLegeTakenResponse(Page page) {
        mockLegeResponse(page, TAKEN_API_PATH);
    }
}
