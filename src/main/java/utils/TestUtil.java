package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtil {

    public static String doSerialization(Object payload){
        String jsonString=null;
        ObjectMapper  obj = new ObjectMapper();
        try {
          jsonString =  obj.writerWithDefaultPrettyPrinter().writeValueAsString(payload);
            System.out.println("Json String >>>" + jsonString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
