package testcases;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import restclient.RestClient;
import utils.Token;

import java.util.Map;

@Epic("NGO-888 Upload Image")
public class GetImgurAPITest {

    String baseURI = "https://api.imgur.com";
    String basePath = "/3/account/haseebk";
    Map<Object,Object> tokenMap;
    String accessToken;
    String refreshToken;
    String accountId;


    @BeforeMethod
    public void setUp() {
        tokenMap = Token.formAccessToken();
        accessToken = tokenMap.get("access_token").toString();
        refreshToken = tokenMap.get("refresh_token").toString();
        accountId = tokenMap.get("account_id").toString();
    }

    @Description("Uploading an image and validating")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void GetAccountBase()
    {

       Map<String ,String> token =Token.getAccessToken();
       Response responseAccount =RestClient.doGet(null,baseURI,basePath, token,null,true);
        System.out.println(responseAccount.prettyPrint());

        JsonPath js = responseAccount.jsonPath();
        String ID = js.get("data.id").toString();
        Assert.assertEquals(ID,"124385139");

        String url = js.get("data.url").toString();
        Assert.assertEquals(url,"haseebk");
    }


}
