package it.unisa.di.smartblog.test.user;

import it.unisa.di.smartblog.review.ReviewDao;
import it.unisa.di.smartblog.test.review.ReviewDaoTest;
import it.unisa.di.smartblog.user.*;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.SQLException;

public class UserDaoTest extends TestCase {

    protected void setUp() throws Exception{

        ud = new UserDao();

    }

    public void testGetByEmail(){

        try{

            User user = ud.getByEmail("antonio@sisonoio.com");
            User oracle = new User("antonio", "!Antonio99", "antonio@sisonoio.com");
            oracle.setId(3);
            oracle.setActive(true);
            assertEquals(user, oracle);
            System.out.println("testGetByEmail() passed!");

        } catch (CredentialsException e){

            fail("testGetByEmail() not passed!");

        } catch (SQLException e){

            fail("testGetByEmail() not passed!");

        }

    }

    public void testGetByEmailException() throws SQLException, CredentialsException{

        try{

            User user = ud.getByEmail(null);
            fail("testGetByEmail() (null email) not passed!");

        } catch (CredentialsException e){

            System.out.println("testGetByEmailException() (null email) passed!");

        }

        try{

            User user = ud.getByEmail("");
            fail("testGetByEmail() (empty email) not passed!");

        } catch (CredentialsException e){

            System.out.println("testGetByEmailException() (empty email) passed!");

        }

        try{

            User user = ud.getByEmail("abcd.");
            fail("testGetByEmail() (invalid email) not passed!");

        } catch (SQLException e){

            System.out.println("testGetByEmail() (invalid email) passed!");

        }

    }

    public void testSaveUser() throws SQLException{

        try {

            User u = new User("pippo", "P1pp0000!", "p1pp099@gmail.com");
            ud.saveUser(u);
            User insertedUser = ud.getByEmail("p1pp099@gmail.com");
            assertEquals(u.getUsername(), insertedUser.getUsername());
            assertEquals(u.getEmail(), insertedUser.getEmail());
            assertEquals(u.getPassword(), insertedUser.getPassword());
            System.out.println("testSaveUser() passed!");
            ud.deleteUserById(insertedUser.getId());

        } catch (CredentialsException e){

            fail("testSaveUser() not passed!");

        }

    }

    public void testGetManager(){

        try{

            User user = new User();
            user.setEmail("manager@manager.com");
            user.setUsername("manager");
            user.setPassword("!Manager10");
            user.setId(6);

            Manager manager = ud.getManager(user);
            Manager oracle = new Manager();
            oracle.setEmail("manager@manager.com");
            oracle.setUsername("manager");
            oracle.setPassword("!Manager10");
            oracle.setPhoneNumber("3334444444");
            assertEquals(manager.getEmail(), oracle.getEmail());
            assertEquals(manager.getPassword(), oracle.getPassword());
            assertEquals(manager.getUsername(), oracle.getUsername());
            assertEquals(manager.getPhoneNumber(), oracle.getPhoneNumber());
            System.out.println("testGetManager() passed!");

        } catch (Exception e){

            System.out.println("testGetManager() not passed!");

        }

    }

    public void testGetManagerException() throws SQLException, CredentialsException{

        try{

            ud.getManager(null);
            fail("testGetManagerException() (null user) not passed!");

        }catch (CredentialsException e){

            System.out.println("testGetManagerException() (null user) passed!");

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

            System.out.println("testGetManagerException() (user not manager) not passed!");

        }

    }

    public void testGetReviewer(){

        try{

            User user = new User();
            user.setEmail("reviewer@reviewer.com");
            user.setUsername("reviewer");
            user.setPassword("!Reviewer10");
            user.setId(5);

            Reviewer reviewer = ud.getReviewer(user);
            Reviewer oracle = new Reviewer();
            oracle.setEmail("reviewer@reviewer.com");
            oracle.setUsername("reviewer");
            oracle.setPassword("!Reviewer10");
            oracle.setPhoneNumber("3333333333");
            oracle.setRank("senior");
            assertEquals(reviewer.getEmail(), oracle.getEmail());
            assertEquals(reviewer.getPassword(), oracle.getPassword());
            assertEquals(reviewer.getUsername(), oracle.getUsername());
            assertEquals(reviewer.getPhoneNumber(), oracle.getPhoneNumber());
            System.out.println("testGetReviewer() passed!");

        } catch (Exception e){

            System.out.println("testGetReviewer() not passed!");

        }

    }

    public void testGetReviewerException() throws SQLException, CredentialsException{

        try{

            ud.getReviewer(null);
            fail("testGetReviewerException() (null user) not passed!");

        }catch (CredentialsException e){

            System.out.println("testGetReviewerException() (null user) passed!");

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

            System.out.println("testGetReviewerException() (user not reviewer) not passed!");

        }

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

            System.out.println("testDeleteUserById() passed!");

        }

    }

    public static Test suite(){

        return new TestSuite(UserDaoTest.class);

    }


    private static UserDao ud;
    private static DataSource ds;

}
