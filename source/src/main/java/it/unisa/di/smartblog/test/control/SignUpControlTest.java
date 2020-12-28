package it.unisa.di.smartblog.test.control;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
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
import java.util.Map;
import java.util.StringJoiner;

public class SignUpControlTest extends TestCase {

    String base = "http://localhost:8080/smartblog_war_exploded/api";

    protected void setUp() throws Exception{
    }

    public void testDone() throws  Exception{
        try{
            String s = signUp("testUser","test@test.com","!Antonio99","!Antonio99");
            System.out.println(s);
            JsonObject obj = new Gson().fromJson(s, JsonObject.class);
            assertTrue(obj.has("message"));
            pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
        }catch(Exception e){
            e.printStackTrace();
            fail("testDone() not passed");
        }
    }

    public void testError() throws  Exception{
        try{
            String s = signUp("user","email@email.com", "password", "password");
            fail("testError() not passed");
        }catch(Exception e){
            pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
        }
    }

    public String signUp(String username, String email, String password, String rePassword) throws Exception {
        URL url = new URL(base+"/signup");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");

        Map<String,String> arguments = new HashMap<>();
        arguments.put("email", email);
        arguments.put("password", password);
        arguments.put("username", username);
        arguments.put("rePassword", rePassword);

        StringJoiner sj = new StringJoiner("&");
        for(Map.Entry<String,String> entry : arguments.entrySet())
            sj.add(URLEncoder.encode(entry.getKey(), "UTF-8") + "="
                    + URLEncoder.encode(entry.getValue(), "UTF-8"));
        byte[] out = sj.toString().getBytes(StandardCharsets.UTF_8);

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
        return new TestSuite(SignUpControlTest.class);
    }
    private static PrintWriter pw;

}
