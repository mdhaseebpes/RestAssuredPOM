package utils;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.List;

public class genericMethods {

    // generic methods for response:
    public static JsonPath getJsonPath(Response response) {
        return response.jsonPath();
    }

    public static int getStatusCode(Response response) {
        return response.getStatusCode();
    }

    public static String getHeaderValue(Response response, String headerName) {
        return response.getHeader(headerName);
    }

    public static int getResponseHeaderCount(Response response) {
        Headers headers = response.getHeaders();
        return headers.size();
    }

    public static List<Header> getResponseHeaders(Response response) {
        Headers headers = response.getHeaders();
        List<Header> headerList = headers.asList();
        return headerList;
    }

    public static void getPrettyResponsePrint(Response response) {
        System.out.println("========response String in pretty format======");
        response.prettyPrint();
    }
}
