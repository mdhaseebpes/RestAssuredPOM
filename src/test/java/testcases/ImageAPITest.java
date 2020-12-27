package testcases;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import restclient.RestClient;
import utils.Token;

import java.util.HashMap;
import java.util.Map;

public class ImageAPITest {

    String baseURI = "https://api.imgur.com";
    String basePath = "/3/image";



    @Test
    public void uploadImageTest()
    {
       Map<String,String> tokenClient = Token.getClientIDToken();
      //  Map<String,String> tokenBearer = Token.getAccessToken();
        Map<String,String> formMap = new HashMap<>();
        formMap.put("title", "vacation");
        formMap.put("description", "Goa");

       Response responseUpload= RestClient.doPost("multipart",baseURI,basePath,tokenClient,null,true,formMap);
        System.out.println(responseUpload.getStatusCode());
        System.out.println(responseUpload.prettyPrint());
    }
}
