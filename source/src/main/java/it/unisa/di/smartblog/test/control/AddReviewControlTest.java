package it.unisa.di.smartblog.test.control;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import it.unisa.di.smartblog.review.Review;
import it.unisa.di.smartblog.review.ReviewManager;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.DataInputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class AddReviewControlTest extends TestCase {
    String base = "http://localhost:8080/smartblog_war_exploded/api";


    protected void setUp() throws Exception{
        pw.println("Class: "+this.getClass().getName());
    }


    public void testAddReview(){
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyZXZpZXdlckByZXZpZXdlci5jb20ifQ.vESbU9Ms_nBa92wnFlLlINkD9ZvA4Y7b-mJsYfIGFyU";
        try{
            URL url = new URL(base+"/review/add");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");

            Map<String,String> arguments = new HashMap<>();
            arguments.put("specId", "2041");
            arguments.put("totalScore", "5");
            arguments.put("performance", "5");
            arguments.put("display", "3");
            arguments.put("battery", "3");
            arguments.put("camera", "4");
            arguments.put("text", "Test case review");

            StringJoiner sj = new StringJoiner("&");
            for(Map.Entry<String,String> entry : arguments.entrySet())
                sj.add(URLEncoder.encode(entry.getKey(), "UTF-8") + "="
                        + URLEncoder.encode(entry.getValue(), "UTF-8"));
            byte[] out = sj.toString().getBytes(StandardCharsets.UTF_8);

            con.setRequestProperty("Authorization", "Bearer "+ token);
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            con.setRequestProperty("Content-Length", String.valueOf(out.length));

            con.setDoOutput(true);
            con.setDoInput(true);

            con.getOutputStream().write(out);
            con.getOutputStream().close();

            DataInputStream input = new DataInputStream( con.getInputStream() );
            String output = "";
            for( int c = input.read(); c != -1; c = input.read() )
                output += (char)c;
            input.close();

            JsonObject json = new Gson().fromJson(output, JsonObject.class);
            assertTrue(json.has("message"));
            pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed! ");

            cleanup();

        }catch(Exception e){
            e.printStackTrace();
            fail("AddReviewControlTest.testError not passed");
        }
    }

    public void testErrorReview(){
        String token = null;
        try{
            URL url = new URL(base+"/review/add");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");

            Map<String,String> arguments = new HashMap<>();
            arguments.put("specId", "2041");
            arguments.put("totalScore", "5");
            arguments.put("performance", "5");
            arguments.put("display", "3");
            arguments.put("battery", "3");
            arguments.put("camera", "4");
            arguments.put("text", "Test case review");

            StringJoiner sj = new StringJoiner("&");
            for(Map.Entry<String,String> entry : arguments.entrySet())
                sj.add(URLEncoder.encode(entry.getKey(), "UTF-8") + "="
                        + URLEncoder.encode(entry.getValue(), "UTF-8"));
            byte[] out = sj.toString().getBytes(StandardCharsets.UTF_8);

            con.setRequestProperty("Authorization", "Bearer "+ token);
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            con.setRequestProperty("Content-Length", String.valueOf(out.length));

            con.setDoOutput(true);
            con.setDoInput(true);

            con.getOutputStream().write(out);
            con.getOutputStream().close();

            DataInputStream input = new DataInputStream( con.getInputStream() );
            String output = "";
            for( int c = input.read(); c != -1; c = input.read() )
                output += (char)c;
            input.close();

            JsonObject json = new Gson().fromJson(output, JsonObject.class);
            assertTrue(json.has("message"));
            fail("AddReviewControlTest.testError not passed");
        }catch(Exception e){
            pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed! ");
        }
    }


    public void cleanup() throws  Exception{
        ReviewManager rm = new ReviewManager();
        List<Review> reviews = rm.searchPendingReviews();
        Review selected = reviews.get(0);
        for(Review r:reviews) if(r.getId() > selected.getId()) selected=r;

        rm.deleteReview(selected.getId());
    }



    public static Test suite(PrintWriter writer){
        pw = writer;
        return new TestSuite(AddReviewControlTest.class);
    }
    private static PrintWriter pw;
}
