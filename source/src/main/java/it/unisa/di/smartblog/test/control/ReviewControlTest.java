package it.unisa.di.smartblog.test.control;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.io.DataInputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class ReviewControlTest extends TestCase {
    String base = "http://localhost:8080/smartblog_war_exploded/api";


    protected void setUp() throws Exception{
        pw.println("Class: "+this.getClass().getName());
    }


    public void testGetPendingInfo() throws  Exception{
        try{
            String s = getPendingReviews("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyZXZpZXdlckByZXZpZXdlci5jb20ifQ.vESbU9Ms_nBa92wnFlLlINkD9ZvA4Y7b-mJsYfIGFyU");
            JsonArray convertedObject = new Gson().fromJson(s, JsonArray.class);
            assertTrue(convertedObject.size()>0);
            pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed! ");

        }catch(Exception e){
            fail("ProfileControlTest.testError not passed");
        }
    }

    public String getPendingReviews(String token) throws Exception {
        URL url = new URL(base+"/review/pending");
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



    public static Test suite(PrintWriter writer){
        pw = writer;
        return new TestSuite(ReviewControlTest.class);
    }
    private static PrintWriter pw;
}
