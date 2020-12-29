package it.unisa.di.smartblog.test.control;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.DataInputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class SpecControlTest extends TestCase {

    String base = "http://localhost:8080/smartblog_war_exploded/api/";

    protected void setUp() throws Exception{
        pw.println("Class: "+this.getClass().getName());
    }

    public void testSearch() throws  Exception{
        try{
            String s = getDetails(2041);
            JsonObject obj = new Gson().fromJson(s, JsonObject.class);
            assertTrue(obj.has("id") && obj.has("name"));
            pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
        }catch(Exception e){
            fail("testSearch() not passed");
        }
    }

    public void testError() throws  Exception{
        try{
            String s = getDetails(1);
            fail("testError() not passed");
        }catch(Exception e){
            pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
        }
    }

    public String getDetails(int id) throws Exception {
        URL url = new URL(base+"/spec?id="+id);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

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
        return new TestSuite(SpecControlTest.class);
    }
    private static PrintWriter pw;

}
