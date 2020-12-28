package it.unisa.di.smartblog.test.control;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import it.unisa.di.smartblog.review.Review;
import it.unisa.di.smartblog.review.ReviewManager;
import it.unisa.di.smartblog.spec.Spec;
import it.unisa.di.smartblog.spec.SpecsManager;
import it.unisa.di.smartblog.user.User;
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

public class ReviewInspectionControlTest extends TestCase {
    String base = "http://localhost:8080/smartblog_war_exploded/api";


    protected void setUp() throws Exception{
        pw.println("Class: "+this.getClass().getName());
    }

    public void testManager(){
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtYW5hZ2VyQG1hbmFnZXIuY29tIn0.BZOq3dhJcWPoL2lfcO1RT_kRTqDNYugerBOYMH014x8";

        try {
            String s = validateReivew(token, "3", true);
            fail("testManager() not passed");
        } catch (Exception e) {
            pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed! ");
        }
    }

    public void testReviewer(){
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyZXZpZXdlckByZXZpZXdlci5jb20ifQ.vESbU9Ms_nBa92wnFlLlINkD9ZvA4Y7b-mJsYfIGFyU";

        try {
            ReviewManager rm = new ReviewManager();
            SpecsManager sm  = new SpecsManager();
            User user = new User();
            user.setId(3);

            List<Spec> specs = sm.searchAll();
            Spec spec = specs.get(0);
            for(Spec r:specs) if(r.getId() > spec.getId()) spec=r;

            rm.createReview(3,4,3,4,4,"Test review",spec, user);

            List<Review> reviews = rm.searchPendingReviews();
            Review selected = reviews.get(0);
            for(Review r:reviews) if(r.getId() > selected.getId()) selected=r;

            //Questo metodo è il test vero e proprio
            String s = validateReivew(token, String.valueOf(selected.getId()), true);
            JsonObject json = new Gson().fromJson(s, JsonObject.class);
            assertTrue(json.has("message"));
            pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed! ");

            rm.deleteReview(selected.getId());
        } catch (Exception e) {
            fail("testReviewer() not passed");
        }
    }
    public void testUserError(){
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbnRvbmlvQHNpc29ub2lvLmNvbSJ9.uMLvvfhOfb4nxBZTBDY__QXX4uRNmlN3_m4ljR-rcWY";

        try {
            String s = validateReivew(token, "3", true);
            fail("testUserError() not passed");
        } catch (Exception e) {
            pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed! ");
        }
    }


    public String validateReivew(String token, String reviewId, boolean approved) throws  Exception{
        URL url = new URL(base+"/changeReviewStatus");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");

        Map<String,String> arguments = new HashMap<>();
        arguments.put("id", reviewId);
        arguments.put("approved", String.valueOf(approved));

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


    private void cleanup(int id){

    }



    public static Test suite(PrintWriter writer){
        pw = writer;
        return new TestSuite(ReviewInspectionControlTest.class);
    }
    private static PrintWriter pw;
}
