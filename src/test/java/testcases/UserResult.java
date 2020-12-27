package testcases;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pojo.*;
import restclient.RestClient;
import utils.Excel;
import utils.TestUtil;

import java.util.HashMap;
import java.util.Map;

public class UserResult {

    String baseUrI = "https://gorest.co.in/";
    String basePath = "public-api/users/";
    static String randomName = RandomStringUtils.randomAlphabetic(8);
    static String emailAddress = RandomStringUtils.randomAlphabetic(6) + "@test.com";
    String accessToken = "df86d530550a5175424c49a09ee2b6e0a9c1f0fbdb43b2490652132cea550ce7";

    @Test
    public void createUserResult()
    {
        Self sf = new Self("http://www.sf.com");
        Edit ed = new Edit("http://www.ed.com");
        Avatar av = new Avatar("http://www.av.com");

        Links ln = new Links(sf,ed,av);



        UserInfo user = new UserInfo("" + randomName + "", "Male", "01-01-1990",""+emailAddress+ "", "+888-888-12334","http://www.labs.com",
                "12 new Avenue Buckhead Atlanta","Active",ln);
      //  String jsonUserPayload = TestUtil.doSerialization(user);

        Map<String,String> token = new HashMap<>();
        token.put("Authorization","Bearer " + accessToken);

        Response response =RestClient.doPost("JSON",baseUrI,basePath,token,null,true,user);

        System.out.println(response.prettyPrint());

        JsonPath js = response.jsonPath();
        String actualName =js.get("data.name").toString();
        Assert.assertEquals(actualName,randomName);

    }

    @DataProvider
    public Object[][] userExcelInfo()
    {
       return Excel.readData("usercreate");
    }

    @Severity(SeverityLevel.BLOCKER)
    @Step("{0},{1},{2},{3},{4},{5},{6},{7},{8}")
    @Test(dataProvider = "userExcelInfo")
    public void createUserExcelData(String name, String gender, String dob, String email,
                                    String phone, String website, String address, String status)
    {
        Self sf = new Self("http://www.sf.com");
        Edit ed = new Edit("http://www.ed.com");
        Avatar av = new Avatar("http://www.av.com");

        Links ln = new Links(sf,ed,av);

        UserInfo user = new UserInfo(name,gender,dob,email,phone,website,address,status,ln);
        //  String jsonUserPayload = TestUtil.doSerialization(user);

        Map<String,String> token = new HashMap<>();
        token.put("Authorization","Bearer " + accessToken);

        Response response =RestClient.doPost("JSON",baseUrI,basePath,token,null,true,user);

        System.out.println(response.prettyPrint());
/*
        JsonPath js = response.jsonPath();
        String actualName =js.get("data.name").toString();
        Assert.assertEquals(actualName,randomName);
*/
    }

}
