package it.unisa.di.smartblog.test.control;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.DataInputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ProfileControlTest extends TestCase {

    String base = "http://localhost:8080/smartblog_war_exploded/api";

    protected void setUp() throws Exception{
    }

    public void testError() throws  Exception{
        try{
            String s = getProfileInfo(null);
            fail("ProfileControlTest.testError not passed");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void testUser() throws  Exception{
        try{
            String s = getProfileInfo("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbnRvbmlvQHNpc29ub2lvLmNvbSJ9.uMLvvfhOfb4nxBZTBDY__QXX4uRNmlN3_m4ljR-rcWY");
        }catch(Exception e){
            fail("ProfileControlTest.testError not passed");
        }
    }

    public void testManager() throws  Exception{
        try{
            String s = getProfileInfo("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtYW5hZ2VyQG1hbmFnZXIuY29tIn0.BZOq3dhJcWPoL2lfcO1RT_kRTqDNYugerBOYMH014x8");
        }catch(Exception e){
            fail("ProfileControlTest.testError not passed");
        }
    }

    public void testReviewer() throws  Exception{
        try{
            String s = getProfileInfo("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyZXZpZXdlckByZXZpZXdlci5jb20ifQ.vESbU9Ms_nBa92wnFlLlINkD9ZvA4Y7b-mJsYfIGFyU");
        }catch(Exception e){
            fail("ProfileControlTest.testError not passed");
        }
    }

    public String getProfileInfo(String token) throws Exception {
        URL url = new URL(base+"/profile");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Authorization", "Bearer "+ token);

        con.setDoOutput(true);
        con.setDoInput(true);

        DataInputStream input = new DataInputStream( con.getInputStream() );
        String output = "";
        for( int c = input.read(); c != -1; c = input.read() )
            output += (char)c;
        input.close();
        return output;
    }



    public static Test suite(){

        return new TestSuite(ProfileControlTest.class);

    }


}
