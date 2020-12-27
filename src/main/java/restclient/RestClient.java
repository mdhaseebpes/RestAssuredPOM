package restclient;

import java.io.File;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.TestUtil;


import static io.restassured.RestAssured.*;


public class RestClient {

    /**
     * This class is having all http methods which will call the apis and having generic methods
     * for getting the response and fetch the values from response
     */

    /**
     * This method is used to create GET request and return the response
     *
     * @param contentFormat
     * @param baseURI
     * @param basePath
     * @param token
     * @param params
     * @param log
     * @return Response of get call
     * author Mohammed Haseeb
     */
    public static Response doGet(String contentFormat, String baseURI, String basePath, Map<String, String> token, Map<String, String>
            params, boolean log) {
        if (setBaseURI(baseURI)) {
            RestClient.setBaseURI(baseURI);
            RequestSpecification request = RestClient.createRequest(contentFormat, token, params, log);
            return executeGetAPI("GET", request, basePath);
        }
        return null;

    }

    public static boolean setBaseURI(String baseURI) {
        if (baseURI == null || baseURI.isEmpty()) {
            System.out.println("Pass the correct URL cannot be null/empty...");
            return false;
        } else {
            try {
                RestAssured.baseURI = baseURI;
                return true;
            } catch (Exception e) {
                System.out.println("Exception has occured while passing the URL");
                return false;
            }
        }

    }

    /**
     * @param contentFormat
     * @param baseURI
     * @param basePath
     * @param token
     * @param params
     * @param log
     * @param obj
     * @return Response of the post request
     */
    public static Response doPost(String contentFormat, String baseURI, String basePath, Map<String, String> token, Map<String, String>
            params, boolean log, Object obj) {
        if (setBaseURI(baseURI)) {
            RestClient.setBaseURI(baseURI);
            RequestSpecification request = RestClient.createRequest(contentFormat, token, params, log);
            addRequestPayload(request, obj);
            return executeGetAPI("POST", request, basePath);
        }
        return null;

    }

    /**
     * This method is pass the payload
     *
     * @param request
     * @param obj
     */
    public static void addRequestPayload(RequestSpecification request, Object obj) {
        if (obj instanceof Map) {
            request.formParams((Map<String, String>) obj);
        } else {
            String payload = TestUtil.doSerialization(obj);
            request.body(payload);
        }
    }

    /**
     * @param contentFormat
     * @param token
     * @param params
     * @param log
     * @return request is returned
     */
    public static RequestSpecification createRequest(String contentFormat, Map<String, String> token, Map<String, String> params, boolean log) {
        RequestSpecification request = null;
        if (log) {
            request = given().log().all();
        } else {
            request = given();
        }
        if (token.size() > 0) {
            request.headers(token);
            // request.header("Authorization", "Bearer " + token);
        }

        if (!(params == null)) {
            request.queryParams(params);
        }
        if (contentFormat != null) {
            if (contentFormat.equalsIgnoreCase("JSON")) {
                request.contentType(ContentType.JSON);
            } else if (contentFormat.equalsIgnoreCase("HTML")) {
                request.contentType(ContentType.HTML);
            } else if (contentFormat.equalsIgnoreCase("TEXT")) {
                request.contentType(ContentType.TEXT);
            } else if (contentFormat.equalsIgnoreCase("multipart")) {
                request.multiPart("image",new File("D:\\Laptop backup\\Interview\\DemoRoundTripBooking-master\\RestAssuredPOM\\src\\main\\Images\\Mohammed.png"));
            }
        }
        return request;
    }


    public static Response executeGetAPI(String httpMethod, RequestSpecification request, String basePath) {
        return executeAPI(httpMethod, request, basePath);
    }

    public static Response executeAPI(String httpMethod, RequestSpecification request, String basePath) {
        Response response = null;

        switch (httpMethod) {
            case "GET":
                response = request.get(basePath);
                break;
            case "POST":
                response = request.post(basePath);
                break;
            case "PUT":
                response = request.put(basePath);
                break;
            case "DELETE":
                response = request.delete(basePath);
                break;

            default:
                System.out.println("Pass correct http Method");
                break;
        }
        return response;
    }
}
