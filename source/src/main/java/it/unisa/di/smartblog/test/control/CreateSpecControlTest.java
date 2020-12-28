package it.unisa.di.smartblog.test.control;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import it.unisa.di.smartblog.review.Review;
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

public class CreateSpecControlTest extends TestCase {
    String base = "http://localhost:8080/smartblog_war_exploded/api";


    protected void setUp() throws Exception{
        pw.println("Class: "+this.getClass().getName());
    }



    public void testUserError(){
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbnRvbmlvQHNpc29ub2lvLmNvbSJ9.uMLvvfhOfb4nxBZTBDY__QXX4uRNmlN3_m4ljR-rcWY";

        try {
            String s = createSpec(token, true);
            fail("testUserError() not passed");
        } catch (Exception e) {
            pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed! ");
        }
    }


    public void testReviewerError(){
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyZXZpZXdlckByZXZpZXdlci5jb20ifQ.vESbU9Ms_nBa92wnFlLlINkD9ZvA4Y7b-mJsYfIGFyU";

        try {
            String s = createSpec(token, true);
            fail("testReviewerError() not passed");
        } catch (Exception e) {
            pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed! ");
        }
    }


    public void testManager(){
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtYW5hZ2VyQG1hbmFnZXIuY29tIn0.BZOq3dhJcWPoL2lfcO1RT_kRTqDNYugerBOYMH014x8";

        try {
            String output = createSpec(token, true);
            JsonObject json = new Gson().fromJson(output, JsonObject.class);
            assertTrue(json.has("message"));
            pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed! ");

            cleanup();
        } catch (Exception e) {
            e.printStackTrace();
            fail("testManager() not passed");
        }
    }


    public void testManagerError(){
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtYW5hZ2VyQG1hbmFnZXIuY29tIn0.BZOq3dhJcWPoL2lfcO1RT_kRTqDNYugerBOYMH014x8";

        try {
            String s = createSpec(token, false);
            fail("testManagerError() not passed");
        } catch (Exception e) {
            pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed! ");
        }
    }

    public String createSpec(String token, boolean rightParams) throws Exception{
        URL url = new URL(base+"/spec/add");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");

        Map<String,String> arguments = new HashMap<>();
        arguments.put("deviceName", "testDevice");
        arguments.put("releaseDate", "2020/07");
        arguments.put("image", "www.google.it");
        arguments.put("OS", "Android");
        arguments.put("CPU", "boh");
        arguments.put("chipset", "boh");
        arguments.put("GPU", "nvidia");
        arguments.put("RAM", "6 GB");
        if(rightParams){
            arguments.put("internalMemory", "100 GB");
            arguments.put("displayInches", "5.6");
            arguments.put("battery", "6000");
            arguments.put("price", "450");
        }


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


    private void cleanup() throws Exception{
        SpecsManager sm = new SpecsManager();
        List<Spec> specs = sm.searchAll();
        Spec selected = specs.get(0);
        for(Spec r:specs) if(r.getId() > selected.getId()) selected=r;

        sm.deleteSpec(selected.getId());
    }




    public static Test suite(PrintWriter writer){
        pw = writer;
        return new TestSuite(CreateSpecControlTest.class);
    }
    private static PrintWriter pw;
}
