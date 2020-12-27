package it.unisa.di.smartblog.test.control;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.DataInputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class AllSpecControlTest extends TestCase {

    String base = "http://localhost:8080/smartblog_war_exploded/api/";

    protected void setUp() throws Exception{
    }

    public void testGetAllSpec() throws Exception {
        URL url = new URL(base+"/spec/all");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        con.setDoOutput(true);
        con.setDoInput(true);

        DataInputStream input = new DataInputStream( con.getInputStream() );
        String output = "";
        for( int c = input.read(); c != -1; c = input.read() )
            output += (char)c;
        input.close();

        JsonArray convertedObject = new Gson().fromJson(output, JsonArray.class);
        if(convertedObject.size() > 0){
            JsonObject spec = (JsonObject) convertedObject.get(0);
            assertTrue(spec.has("id") && spec.has("name"));
        }else{
            fail();
        }

    }

    public static Test suite(){

        return new TestSuite(AllSpecControlTest.class);

    }


}
