package it.unisa.di.smartblog.test.control;

import it.unisa.di.smartblog.review.ReviewDao;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import javax.net.ssl.HttpsURLConnection;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class SearchControlTest extends TestCase {

    String base = "http://localhost:8080/smartblog_war_exploded/api/";

    protected void setUp() throws Exception{
    }

    public void testSearch() throws Exception {
        URL url = new URL(base+"/search?s=iphone");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        //con.setRequestProperty("Content-length", String.valueOf(query.length()));
        //con.setRequestProperty("Content-Type","application/x-www- form-urlencoded");
        con.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0;Windows98;DigExt)");
        con.setDoOutput(true);
        con.setDoInput(true);

        DataInputStream input = new DataInputStream( con.getInputStream() );
        String output = "";
        for( int c = input.read(); c != -1; c = input.read() )
            output += (char)c;
        input.close();

        System.out.println(output);
        System.out.println("Resp Code:"+con .getResponseCode());
        System.out.println("Resp Message:"+ con .getResponseMessage());
    }

    public static Test suite(){

        return new TestSuite(SearchControlTest.class);

    }


}
