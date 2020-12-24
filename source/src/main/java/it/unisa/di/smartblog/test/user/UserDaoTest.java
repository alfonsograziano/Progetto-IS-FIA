package it.unisa.di.smartblog.test.user;

import it.unisa.di.smartblog.review.ReviewDao;
import it.unisa.di.smartblog.test.review.ReviewDaoTest;
import it.unisa.di.smartblog.user.CredentialsException;
import it.unisa.di.smartblog.user.Manager;
import it.unisa.di.smartblog.user.User;
import it.unisa.di.smartblog.user.UserDao;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

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
            fail("testGetByEmail() (empty or null email) not passed!");

        } catch (CredentialsException e){

            System.out.println("testGetByEmailException() (empty or null email) passed!");

        }

        try{

            User user = ud.getByEmail("abcd.");
            fail("testGetByEmail() (invalid email) not passed!");

        } catch (SQLException e){

            System.out.println("testGetByEmail() (invalid email) passed!");

        }

    }

    public void testSaveUser() throws SQLException{
        //TODO: Eliminare user dopo l'inserimento

        try {

            User u = new User("pippo", "P1pp0000!", "p1pp099@gmail.com");
            ud.saveUser(u);
            User insertedUser = ud.getByEmail("p1pp099@gmail.com");
            assertEquals(u.getUsername(), insertedUser.getUsername());
            assertEquals(u.getEmail(), insertedUser.getEmail());
            assertEquals(u.getPassword(), insertedUser.getPassword());
            System.out.println("testSaveUser() passed!");

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
            fail("testGetManagerException() (user not reviewer) not passed!");

        } catch(SQLException e){

            System.out.println("testGetManagerException() (user not reviewer) not passed!");

        }

    }


    public static Test suite(){

        return new TestSuite(UserDaoTest.class);

    }


    private static UserDao ud;

}
