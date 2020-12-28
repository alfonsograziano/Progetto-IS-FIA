package it.unisa.di.smartblog.test.control;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import it.unisa.di.smartblog.review.ReviewManager;
import it.unisa.di.smartblog.spec.Spec;
import it.unisa.di.smartblog.spec.SpecsManager;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.DataInputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class SetSpecScoreControlTest extends TestCase {
    String base = "http://localhost:8080/smartblog_war_exploded/api";


    protected void setUp() throws Exception{
        pw.println("Class: "+this.getClass().getName());
    }

    public void testManager(){
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtYW5hZ2VyQG1hbmFnZXIuY29tIn0.BZOq3dhJcWPoL2lfcO1RT_kRTqDNYugerBOYMH014x8";

        try {
            String s = setSpecScores(token, "2041");
            fail("testManager() not passed");
        } catch (Exception e) {
            pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed! ");
        }
    }

    public void testReviewer(){
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyZXZpZXdlckByZXZpZXdlci5jb20ifQ.vESbU9Ms_nBa92wnFlLlINkD9ZvA4Y7b-mJsYfIGFyU";
        SpecsManager sm = new SpecsManager();

        try {

            List<Spec> specs = sm.searchAll();
            Spec s = specs.get(0);
            for(Spec r:specs) if(r.getId() > s.getId()) s=r;


            String output = setSpecScores(token, String.valueOf(s.getId()));
            JsonObject json = new Gson().fromJson(output, JsonObject.class);
            assertTrue(json.has("message"));
            pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed! ");

            sm.setScores(5, s.getId(), s.getPerformance(), s.getDisplay(),s.getCamera());

        } catch (Exception e) {
            fail("testReviewer() not passed");
        }
    }

    public void testReviewerError(){
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyZXZpZXdlckByZXZpZXdlci5jb20ifQ.vESbU9Ms_nBa92wnFlLlINkD9ZvA4Y7b-mJsYfIGFyU";

        try {
            String s = setSpecScores(token, "1");
            System.out.println("Reponse: "+s);
            fail("testReviewerError() not passed");
        } catch (Exception e) {
            pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed! ");
        }
    }

    public void testError(){
        try {
            String s = setSpecScores(null, "2041");
            fail("testError() not passed");
        } catch (Exception e) {
            pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed! ");
        }
    }

    public void testUserError(){
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbnRvbmlvQHNpc29ub2lvLmNvbSJ9.uMLvvfhOfb4nxBZTBDY__QXX4uRNmlN3_m4ljR-rcWY";

        try {
            String s = setSpecScores(token, "2041");
            fail("testUserError() not passed");
        } catch (Exception e) {
            pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed! ");
        }
    }


    public String setSpecScores(String token, String specId) throws  Exception{
        URL url = new URL(base+"/spec/setscores");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");

        Map<String,String> arguments = new HashMap<>();
        arguments.put("specId", specId);
        arguments.put("performance", "3.5");
        arguments.put("display", "3.2");
        arguments.put("camera", "4.1");

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

        return output;
    }






    public static Test suite(PrintWriter writer){
        pw = writer;
        return new TestSuite(SetSpecScoreControlTest.class);
    }
    private static PrintWriter pw;
}
