package it.unisa.di.smartblog.test.control;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import it.unisa.di.smartblog.review.ReviewDao;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import javax.net.ssl.HttpsURLConnection;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.PrintWriter;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class SearchControlTest extends TestCase {

    String base = "http://localhost:8080/smartblog_war_exploded/api/";

    protected void setUp() throws Exception{
        pw.println("Class: "+this.getClass().getName());
    }

    public void testSearch() throws Exception {
        URL url = new URL(base+"/search?s=oneplus");
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
        assertEquals(2,convertedObject.size());
        pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed! ");

    }

    public static Test suite(PrintWriter writer){
        pw = writer;
        return new TestSuite(SearchControlTest.class);
    }
    private static PrintWriter pw;

}
