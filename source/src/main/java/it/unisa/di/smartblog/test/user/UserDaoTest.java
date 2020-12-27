package it.unisa.di.smartblog.test.user;

import it.unisa.di.smartblog.review.Review;
import it.unisa.di.smartblog.test.TestWriter;
import it.unisa.di.smartblog.user.*;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.io.PrintWriter;
import java.sql.SQLException;

public class UserDaoTest extends TestCase {


    protected void setUp() throws Exception{
        ud = new UserDao();
    }


    public void testGetByEmail(){
        User oracle = null, user = null;
        boolean flag = false;

        try{
            oracle = new User("antonio", "!Antonio99", "antonio@sisonoio.com");
            oracle.setId(3);
            oracle.setActive(true);

            user = ud.getByEmail("antonio@sisonoio.com");

            assertEquals(user, oracle);

            flag=true;

        } catch (CredentialsException e){

            fail("testGetByEmail() not passed!");

        } catch (SQLException e){

            fail("testGetByEmail() not passed!");

        } finally {
            TestWriter.printTest(pw, oracle, user);
            if(flag) pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
        }

    }

    public void testGetByEmailException() throws SQLException, CredentialsException{

        try{

            User user = ud.getByEmail(null);
            fail("testGetByEmailException() (null email) not passed!");

        } catch (CredentialsException e){

        }

        try{

            User user = ud.getByEmail("");
            fail("testGetByEmailException() (empty email) not passed!");

        } catch (CredentialsException e){

        }

        try{

            User user = ud.getByEmail("abcd.");
            fail("testGetByEmailException() (invalid email) not passed!");

        } catch (SQLException e){

        }


        pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");

    }

    public void testSaveUser() throws SQLException{
        User u = null, insertedUser = null;
        boolean flag = false;

        try {

            u = new User("pippo", "P1pp0000!", "p1pp099@gmail.com");
            ud.saveUser(u);
            insertedUser = ud.getByEmail("p1pp099@gmail.com");
            assertEquals(u.getUsername(), insertedUser.getUsername());
            assertEquals(u.getEmail(), insertedUser.getEmail());
            assertEquals(u.getPassword(), insertedUser.getPassword());
            ud.deleteUserById(insertedUser.getId());

            flag=true;

        } catch (CredentialsException e){

            fail("testSaveUser() not passed!");

        } finally {
            TestWriter.printTest(pw, u, insertedUser);
            if(flag) pw.println("\tResult: "+ Thread.currentThread().getStackTrace()[1].getMethodName() +" passed!");
        }

    }

    public void testGetManager(){
        Manager oracle = null, manager = null;
        boolean flag=false;

        try{

            User user = new User();
            user.setEmail("manager@manager.com");
            user.setUsername("manager");
            user.setPassword("!Manager10");
            user.setId(6);

            manager = ud.getManager(user);
            oracle = new Manager();
            oracle.setEmail("manager@manager.com");
            oracle.setUsername("manager");
            oracle.setPassword("!Manager10");
            oracle.setPhoneNumber("3334444444");
            assertEquals(manager.getEmail(), oracle.getEmail());
            assertEquals(manager.getPassword(), oracle.getPassword());
            assertEquals(manager.getUsername(), oracle.getUsername());
            assertEquals(manager.getPhoneNumber(), oracle.getPhoneNumber());

            flag = true;

        } catch (Exception e){

            fail("testGetManager() not passed!");

        } finally {
            TestWriter.printTest(pw, oracle, manager);
            if(flag) pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
        }

    }

    public void testGetManagerException() throws SQLException, CredentialsException{

        try{
            ud.getManager(null);
            fail("testGetManagerException() (null user) not passed!");

        }catch (CredentialsException e){

        }

        try{

            User user = new User();
            user.setEmail("antonio@sisonoio.com");
            user.setUsername("antonio");
            user.setPassword("!Antonio99");
            user.setId(3);
            ud.getManager(user);
            fail("testGetManagerException() (user not manager) not passed!");

        } catch(SQLException e){

        }

        pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
    }

    public void testGetReviewer(){
        Reviewer oracle=null, reviewer=null;
        boolean flag=false;

        try{

            User user = new User();
            user.setEmail("reviewer@reviewer.com");
            user.setUsername("reviewer");
            user.setPassword("!Reviewer10");
            user.setId(5);

            reviewer = ud.getReviewer(user);
            oracle = new Reviewer();
            oracle.setEmail("reviewer@reviewer.com");
            oracle.setUsername("reviewer");
            oracle.setPassword("!Reviewer10");
            oracle.setPhoneNumber("3333333333");
            oracle.setRank("senior");
            assertEquals(reviewer.getEmail(), oracle.getEmail());
            assertEquals(reviewer.getPassword(), oracle.getPassword());
            assertEquals(reviewer.getUsername(), oracle.getUsername());
            assertEquals(reviewer.getPhoneNumber(), oracle.getPhoneNumber());

            flag=true;

        } catch (Exception e){

            fail("testGetReviewer() not passed!");

        } finally {
            TestWriter.printTest(pw, oracle, reviewer);
            if(flag) pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
        }

    }

    public void testGetReviewerException() throws SQLException, CredentialsException{

        try{

            ud.getReviewer(null);
            fail("testGetReviewerException() (null user) not passed!");

        }catch (CredentialsException e){

        }

        try{

            User user = new User();
            user.setEmail("antonio@sisonoio.com");
            user.setUsername("antonio");
            user.setPassword("!Antonio99");
            user.setId(3);
            ud.getReviewer(user);
            fail("testGetReviewerException() (user not reviewer) not passed!");

        } catch(SQLException e){

        }

        pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
    }

    public void testDeleteUserById() throws CredentialsException{

        try {

            User u = new User("testuser", "TestUser!123", "testuser99@gmail.com");
            ud.saveUser(u);
            User insertedUser = ud.getByEmail("testuser99@gmail.com");
            ud.deleteUserById(insertedUser.getId());
            ud.getByEmail("testuser99@gmail.com");
            fail("testDeleteUserById() not passed!");

        }catch (SQLException e){

            pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");

        }

    }

    public static Test suite(PrintWriter writer){
        pw = writer;
        return new TestSuite(UserDaoTest.class);

    }

    private static PrintWriter pw;
    private static UserDao ud;
}
