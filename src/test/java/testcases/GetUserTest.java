package testcases;

import com.google.gson.annotations.JsonAdapter;
import io.qameta.allure.*;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.Test;
import restclient.RestClient;
import utils.Token;

import java.util.HashMap;
import java.util.Map;

@Epic("NGO-9920 Fetching user details")
@Feature("NGO-9771 Getting user info")
public class GetUserTest {

    String baseUrI = "https://gorest.co.in/";
    String basePath = "public-api/users";


    @Description("Getting user info and validating")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    public void getAllActiveUser()
    {
        Map<String,String> params = new HashMap<>();
        params.put("status","Active");
        params.put("name","name");

        Map<String ,String> token = Token.goRestAccessToken();

       Response response= RestClient.doGet("JSON",baseUrI,basePath,token,params,true);
        System.out.println(response.getStatusCode());
        System.out.println(response.prettyPrint());

      String allHeaders =  response.headers().toString();
        System.out.println("All Headers " + allHeaders);

       Headers h1= response.getHeaders();

        Headers all = response.headers();
        Map<String,String> map = new HashMap<>();
        for(Header h: all)
        {
            System.out.println(" Key : " + h.getName() + "  Value :  " + h.getValue());

            map.put(h.getName(),h.getValue());
        }

        System.out.println("************" + map);

        System.out.println("Response Body  ----" + response.body().prettyPrint());


      String header =  response.getHeader("Content-Type");
        System.out.println(header);
       JsonPath js = response.jsonPath();
       String actualTotal = js.get("meta.pagination.total").toString();

       Assert.assertEquals(actualTotal,"3");
    }

}
