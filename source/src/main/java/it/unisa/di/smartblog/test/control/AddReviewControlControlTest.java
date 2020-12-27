package it.unisa.di.smartblog.test.control;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.DataInputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class AddReviewControlControlTest extends TestCase {

    String base = "http://localhost:8080/smartblog_war_exploded/api/";

    protected void setUp() throws Exception{
    }

    public void testCreateReview() throws Exception {
        String userToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbGZvbnNvMkBnbWFpbC5jb20ifQ.RgwX8f_mAMus5ziCmPz7WMP3ysybbVIlYsABfgro3kw";

        URL url = new URL(base+"/review/add");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Authorization", "Bearer "+ userToken);
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");

        con.setDoOutput(true);
        con.setDoInput(true);


        Map<String,String> arguments = new HashMap<>();
        arguments.put("specId", "2046");
        arguments.put("totalScore", "5");
        arguments.put("performance", "5");
        arguments.put("display", "3");
        arguments.put("camera", "4");
        arguments.put("text", "Test case review");

        StringJoiner sj = new StringJoiner("&");
        for(Map.Entry<String,String> entry : arguments.entrySet())
            sj.add(URLEncoder.encode(entry.getKey(), "UTF-8") + "="
                    + URLEncoder.encode(entry.getValue(), "UTF-8"));
        byte[] out = sj.toString().getBytes(StandardCharsets.UTF_8);


       con.getOutputStream().write(out);
       con.getOutputStream().close();


        DataInputStream input = new DataInputStream( con.getInputStream() );
        String output = "";
        for( int c = input.read(); c != -1; c = input.read() )
            output += (char)c;
        input.close();

        System.out.println(output);
        JsonArray convertedObject = new Gson().fromJson(output, JsonArray.class);
        if(convertedObject.size() > 0){
            JsonObject spec = (JsonObject) convertedObject.get(0);
            assertTrue(spec.has("message"));
        }else{
            fail();
        }

    }

    public static Test suite(){

        return new TestSuite(AddReviewControlControlTest.class);

    }


}
