package it.unisa.di.smartblog.test.control;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
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

public class DeleteSpecControlTest extends TestCase {
    String base = "http://localhost:8080/smartblog_war_exploded/api";


    protected void setUp() throws Exception{
        pw.println("Class: "+this.getClass().getName());
    }



    public void testUserError(){
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbnRvbmlvQHNpc29ub2lvLmNvbSJ9.uMLvvfhOfb4nxBZTBDY__QXX4uRNmlN3_m4ljR-rcWY";

        try {
            String s = deleteSpec(token, 2045);
            fail("testUserError() not passed");
        } catch (Exception e) {
            pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed! ");
        }
    }


    public void testManager(){
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtYW5hZ2VyQG1hbmFnZXIuY29tIn0.BZOq3dhJcWPoL2lfcO1RT_kRTqDNYugerBOYMH014x8";
        SpecsManager sm = new SpecsManager();

        try {
            //Creo una nuova scheda tecnica e poi la cancello
            sm.createSpec("OnePlus Nord N100","2020/4","https://hd2.tudocdn.net/941166?w=139&h=304",
                    "Android 10 OxygenOS 10.5", "4x 1.8 GHz Kryo 240 + 4x 1.6 GHz Kryo 240",
                    "Snapdragon 460 Qualcomm SM4250","Adreno 610","4 GB","64 GB","6.52",5000,167);

            List<Spec> specs = sm.searchAll();
            Spec selected = specs.get(0);
            for(Spec r:specs) if(r.getId() > selected.getId()) selected=r;

            String output = deleteSpec(token, selected.getId());
            JsonObject json = new Gson().fromJson(output, JsonObject.class);
            assertTrue(json.has("message"));
            pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed! ");

        } catch (Exception e) {
            e.printStackTrace();
            fail("testManager() not passed");
        }
    }

    public void testReviewerError(){
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyZXZpZXdlckByZXZpZXdlci5jb20ifQ.vESbU9Ms_nBa92wnFlLlINkD9ZvA4Y7b-mJsYfIGFyU";

        try {
            String s = deleteSpec(token, 2045);
            System.out.println(s);
            fail("testReviewerError() not passed");
        } catch (Exception e) {
            pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed! ");
        }
    }

    public void testCredentialError(){

        try {
            String s = deleteSpec(null, 2045);
            fail("testReviewerError() not passed");
        } catch (Exception e) {
            pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed! ");
        }
    }


    public void testSpecNotFoundError(){
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtYW5hZ2VyQG1hbmFnZXIuY29tIn0.BZOq3dhJcWPoL2lfcO1RT_kRTqDNYugerBOYMH014x8";

        try {
            String s = deleteSpec(token, 1);
            fail("testReviewerError() not passed");
        } catch (Exception e) {
            pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed! ");
        }
    }


    public String deleteSpec(String token, int specId) throws Exception{
        URL url = new URL(base+"/spec/delete");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");

        Map<String,String> arguments = new HashMap<>();
        arguments.put("specId", String.valueOf(specId));

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
        return new TestSuite(DeleteSpecControlTest.class);
    }
    private static PrintWriter pw;
}
