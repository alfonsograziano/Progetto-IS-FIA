package it.unisa.di.smartblog.review;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.List;

public class ReviewDao {
    static{
        try {
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            ds = (DataSource) envCtx.lookup("smartblog");

        } catch(NamingException e){
            e.printStackTrace();
        }
    }
/*
    public synchronized Review getById(int id){

    }

    public synchronized List<Review> getBySpecId(int id){

    }

    public synchronized List<Review> getByUser(String email){

    }

    public synchronized List<Review> getPending(){

    }

    public synchronized boolean saveReview(Review review){

        return true;
    }

    public synchronized boolean deleteReview(int id){

    }
*/
    private static DataSource ds;
}
