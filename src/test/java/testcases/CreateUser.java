package testcases;

import io.qameta.allure.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pojo.User;
import restclient.RestClient;
import utils.Excel;
import utils.Token;

import java.util.Map;

@Epic(" NGO-123 Create an user")
@Feature("NGO -345 user created with parameters")
public class CreateUser {

    String baseUrI = "https://gorest.co.in/";
    String basePath = "public-api/users/";


   static String randomName = RandomStringUtils.randomAlphabetic(8);
    String emailAddress = RandomStringUtils.randomAlphabetic(6) + "@test.com";

    User user = new User("" + randomName + "", "Male", "01-01-1990",""+emailAddress+ "", "+888-888-12334","http://www.labs.com",
            "12 new Avenue Buckhead Atlanta","Active");

   public static  Map<String,String> token = Token.goRestAccessToken();

   @Description("Creating an New user and validating the response")
   @Severity(SeverityLevel.CRITICAL)
    @Test
    public void createNewUser()
    {


       Response response = RestClient.doPost("JSON",baseUrI,basePath,token,null,true,user);
        System.out.println(response.getStatusCode());
        System.out.println(response.prettyPrint());

        JsonPath js = response.jsonPath();
        String actualPage =js.get("data.name").toString();
        Assert.assertEquals(actualPage, randomName);

        String actualLimit = js.get("data.status").toString();
        Assert.assertEquals(actualLimit,"Active");

    }

    @DataProvider
    public Object[][] getUserData() {

       return Excel.readData("usercreate");
    }

    @Description("Creating an New user with Data Provider")
    @Severity(SeverityLevel.NORMAL)
    @Step("{0},{1},{2},{3},{4},{5},{6},{7},{8}")
    @Test(dataProvider = "getUserData")
    public void createUserExcelData(String name, String gender, String dob, String email,
                                    String phone, String website, String address, String status) {
        User user = new User(name, gender, dob, email, phone, website, address, status);
        Response response = RestClient.doPost("JSON", baseUrI, basePath, token, null, true, user);
        System.out.println(response.getStatusCode());
        System.out.println(response.prettyPrint());


    }
}
