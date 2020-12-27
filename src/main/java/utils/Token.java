package utils;

import io.restassured.path.json.JsonPath;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class Token {

    public static Map<Object,Object> appTokenMap;
    public static Map<String,String>  tokenMap = new HashMap<String,String>();
    public static String clientId = "5aed051951c2fbd";

    public static Map<Object,Object> formAccessToken()
    {
        Map<String,String> params = new HashMap<>();
        params.put("refresh_token","7b7cb94ba095cffcc5bc3a26cab12a07e7097249");
        params.put("client_id","5aed051951c2fbd");
        params.put("client_secret","16e0a79b18f216bcc672b0b0373d143dbfe3f28f");
        params.put("grant_type","refresh_token");

        JsonPath response = given().
                formParams(params).
                when().post("https://api.imgur.com/oauth2/token").
                then().and().extract().jsonPath();

        System.out.println(response.getMap(""));

        appTokenMap = response.getMap("");
        return appTokenMap;

    }

    //Method return Bearer authorization
    public static Map<String,String> getAccessToken()
    {
        Token.formAccessToken();
        String authToken = appTokenMap.get("access_token").toString();
        System.out.println("Authorization token >>>" + authToken);
        tokenMap.put("Authorization","Bearer " + authToken);
        return tokenMap;
    }


    //method returns Client ID
    public static Map<String, String> getClientIDToken()
    {
        System.out.println("Client id is " + clientId);
       tokenMap.put("Authorization","Client-ID 5aed051951c2fbd");
       return tokenMap;
    }

    //GOrest Authorization method
    String token = "df86d530550a5175424c49a09ee2b6e0a9c1f0fbdb43b2490652132cea550ce7";
    public static Map<String, String> goRestAccessToken()
    {
        tokenMap.put("Authorization","Bearer df86d530550a5175424c49a09ee2b6e0a9c1f0fbdb43b2490652132cea550ce7");
        return tokenMap;
    }
}
