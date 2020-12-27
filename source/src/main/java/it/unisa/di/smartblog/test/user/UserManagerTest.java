package it.unisa.di.smartblog.test.user;

import it.unisa.di.smartblog.spec.SpecsManager;
import it.unisa.di.smartblog.test.TestWriter;
import it.unisa.di.smartblog.test.spec.SpecManagerTest;
import it.unisa.di.smartblog.user.*;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.PrintWriter;
import java.sql.SQLException;

public class UserManagerTest extends TestCase {

    protected void setUp() throws Exception{

        um = new UserManager();

    }

    public static Test suite(PrintWriter writer){
        pw = writer;
        return new TestSuite(UserManagerTest.class);

    }

    public void testCreateUser() throws SQLException {
        boolean flag=false;
        User insertedUser = null;
        User u = new User("pippo", "P1pp0000!", "p1pp099@gmail.com");
        try {

            um.createUser("pippo", "p1pp099@gmail.com", "P1pp0000!", "P1pp0000!");
            insertedUser = um.getUserInfoByEmail("p1pp099@gmail.com");
            assertEquals(u.getUsername(), insertedUser.getUsername());
            assertEquals(u.getEmail(), insertedUser.getEmail());
            assertEquals(u.getPassword(), insertedUser.getPassword());
            flag=true;
            um.deleteUser(insertedUser.getId());

        } catch (CredentialsException e){

            fail("testCreateUser() not passed!");

        } finally {
            TestWriter.printTest(pw, u, insertedUser);
            if(flag) pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
        }

    }

    public void testCreateUserException() throws SQLException{
        User u = new User("pippo", "P1pp0000!","antonio@sisonoio.com");

        try {

            um.createUser("pippo", "antonio@sisonoio.com", "P1pp0000!", "P1pp0000!");
            fail("testCreateUserException() (email already used) not passed!");

        } catch (CredentialsException e){

        }

        try {

            um.createUser("pippo", "p1pp099@gmail.com", "P1pp0000!", "P1pp11111!");
            fail("testCreateUserException() (password doesn't match repeatPassword) not passed!");

        } catch (CredentialsException e){
        }

        try {

            um.createUser("pippo", "p1pp099@gmail.com", "pippo1", "pippo1");
            fail("testCreateUserException() (wrong password format) not passed!");

        } catch (CredentialsException e){

        }

        try {

            um.createUser("pippo", "p1pp099@gmail", "P1pp0000!", "P1pp0000!");
            fail("testCreateUserException() (wrong email format) not passed!");

        } catch (CredentialsException e){
        }

        try {

            um.createUser("lol", "p1pp099@gmail.com", "P1pp0000!", "P1pp0000!");
            fail("testCreateUserException() (wrong username format) not passed!");

        } catch (CredentialsException e){
        }

        try {

            um.createUser("", "p1pp099@gmail.com", "P1pp0000!", "P1pp0000!");
            fail("testCreateUserException() (empty username) not passed!");

        } catch (CredentialsException e){

        }

        try {

            um.createUser("pippo", "", "P1pp0000!", "P1pp0000!");
            fail("testCreateUserException() (empty email) not passed!");

        } catch (CredentialsException e){

        }

        try {

            um.createUser("pippo", "p1pp099@gmail.com", "", "P1pp0000!");
            fail("testCreateUserException() (empty password) not passed!");

        } catch (CredentialsException e){

        }

        try {

            um.createUser("pippo", "p1pp099@gmail.com", "P1pp0000!", "");
            fail("testCreateUserException() (empty repeatPassword) not passed!");

        } catch (CredentialsException e){

        }

        try {

            um.createUser(null, "p1pp099@gmail.com", "P1pp0000!", "P1pp0000!");
            fail("testCreateUserException() (empty username) not passed!");

        } catch (CredentialsException e){

        }

        try {

            um.createUser("pippo", null, "P1pp0000!", "P1pp0000!");
            fail("testCreateUserException() (wrong username format) not passed!");

        } catch (CredentialsException e){

        }

        try {

            um.createUser("pippo", "p1pp099@gmail.com", null, "P1pp0000!");
            fail("testCreateUserException() (wrong username format) not passed!");

        } catch (CredentialsException e){

        }

        try {

            um.createUser("pippo", "p1pp099@gmail.com", "P1pp0000!", null);
            fail("testCreateUserException() (wrong username format) not passed!");

        } catch (CredentialsException e){

        }

        pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
    }

    public void testAuth(){
        boolean flag=false;
        User oracle=null, user=null;
        try{

            user = um.auth("antonio@sisonoio.com", "!Antonio99");
            oracle = new User("antonio", "!Antonio99", "antonio@sisonoio.com");
            oracle.setId(3);
            oracle.setActive(true);
            assertEquals(user, oracle);
            flag=true;

        } catch (CredentialsException e){

            fail("testAuth() not passed!");

        } catch (SQLException e){

            fail("testAuth() not passed!");

        } finally {
            TestWriter.printTest(pw, oracle, user);
            if(flag) pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
        }

    }

    public void testAuthException() throws SQLException, CredentialsException{

        User user = null;

        try{

            user = um.auth(null, "!Antonio99");
            fail("testAuthException() (null email) not passed!");

        } catch (CredentialsException e){


        }

        try{

            user = um.auth("antonio@sisonoio.com", null);
            fail("testAuthException() (null password) not passed!");

        } catch (CredentialsException e){

        }

        try{

            user = um.auth("", "!Antonio99");
            fail("testAuthException() (empty email) not passed!");

        } catch (CredentialsException e){

        }

        try{

            user = um.auth("antonio@sisonoio.com", "");
            fail("testAuthException() (empty password) not passed!");

        } catch (CredentialsException e){

        }

        try{

            user = um.auth("antonio@sisonoio.com", "!Antonio");
            fail("testAuthException() (wrong password) not passed!");

        } catch (CredentialsException e){

        }

        try{

            user = um.auth("superpippo99@gmail.com", "!SuperPippo");
            fail("testAuthException() (user not found) not passed!");

        } catch (SQLException e){

        }

        pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
    }

    public void testIsManager() throws CredentialsException{
        boolean flag=false;
        Manager oracle=null, manager=null;

        try{

            User user = new User();
            user.setEmail("manager@manager.com");
            user.setUsername("manager");
            user.setPassword("!Manager10");
            user.setId(6);

            manager = um.isManager(user);
            oracle = new Manager();
            oracle.setEmail("manager@manager.com");
            oracle.setUsername("manager");
            oracle.setPassword("!Manager10");
            oracle.setPhoneNumber("3334444444");
            assertEquals(manager.getEmail(), oracle.getEmail());
            assertEquals(manager.getPassword(), oracle.getPassword());
            assertEquals(manager.getUsername(), oracle.getUsername());
            assertEquals(manager.getPhoneNumber(), oracle.getPhoneNumber());
            flag=true;

        } catch (Exception e){

            fail("testIsManager() not passed!");

        } finally {
            TestWriter.printTest(pw, oracle, manager);
            if(flag) pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
        }

    }

    public void testIsManagerException() throws CredentialsException {

        try {

            Manager m = um.isManager(null);
            fail("testIsManagerException() (null user) not passed!");

        } catch (CredentialsException e) {


        }


        User user = new User();
        user.setEmail("antonio@sisonoio.com");
        user.setUsername("antonio");
        user.setPassword("!Antonio99");
        user.setId(3);
        if (um.isManager(user) != null) {

            fail("testGetManagerException() (user not manager) not passed!");

        }

        pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");

    }

    public void testIsReviewer(){
        boolean flag=false;
        Reviewer reviewer = null, oracle = null;

        try{

            User user = new User();
            user.setEmail("reviewer@reviewer.com");
            user.setUsername("reviewer");
            user.setPassword("!Reviewer10");
            user.setId(5);

            reviewer = um.isReviewer(user);
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

            fail("testIsReviewer() not passed!");

        } finally {
            TestWriter.printTest(pw, oracle, reviewer);
            if(flag) pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
        }
    }

    public void testIsReviewerException() throws CredentialsException{

        try {

            Reviewer m = um.isReviewer(null);
            fail("testIsReviewerException() (null user) not passed!");

        } catch (CredentialsException e) {


        }


        User user = new User();
        user.setEmail("antonio@sisonoio.com");
        user.setUsername("antonio");
        user.setPassword("!Antonio99");
        user.setId(3);
        if (um.isManager(user) != null) {

            fail("testIsReviewerException() (user not reviewer) not passed!");

        }

        pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");
    }

    public void testGetUserInfoByEmail(){
        boolean flag=false;
        User oracle=null, user=null;

        try{

            user = um.getUserInfoByEmail("antonio@sisonoio.com");
            oracle = new User("antonio", "!Antonio99", "antonio@sisonoio.com");
            oracle.setId(3);
            oracle.setActive(true);
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

    public void testGetUserInfoByEmailException() throws SQLException, CredentialsException{

        try{

            User user = um.getUserInfoByEmail(null);
            fail("testGetUserInfoByEmailException() (null email) not passed!");

        } catch (CredentialsException e){

        }

        try{

            User user = um.getUserInfoByEmail("");
            fail("testGetUserInfoByEmailException() (empty email) not passed!");

        } catch (CredentialsException e){

        }

        pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");

    }

    public void testDeleteUser() throws CredentialsException{

        try {

            um.createUser("testuser", "testuser99@gmail.com", "TestUser!123", "TestUser!123");
            User insertedUser = um.getUserInfoByEmail("testuser99@gmail.com");
            um.deleteUser(insertedUser.getId());
            um.getUserInfoByEmail("testuser99@gmail.com");
            fail("testDeleteUser() not passed!");

        }catch (SQLException e){

            pw.println("\tResult: "+Thread.currentThread().getStackTrace()[1].getMethodName()+" passed!");

        }

    }

    private UserManager um;
    private static PrintWriter pw;
}
