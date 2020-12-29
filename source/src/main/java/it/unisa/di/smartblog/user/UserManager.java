package it.unisa.di.smartblog.user;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserManager {
    static{
        dao = new UserDao();
    }

    public boolean createUser(String username, String email, String password, String repeatPassword) throws CredentialsException, SQLException{

        if(checkCredentialsFormat(username, email, password, repeatPassword)){
            User user = new User(username, password, email);
            dao.saveUser(user);
        }

        /*
        if(!emailAlreadyUsed(email)) {
            if(checkCredentialsFormat(username, email, password, repeatPassword)) {
                User user = new User(username, password, email);
                dao.saveUser(user);
            }
        } else throw new CredentialsException("Email already in use");
        */
        return true;
    }

    public User auth(String email, String password) throws CredentialsException, SQLException{
        if(password==null || password.equals("")) throw new CredentialsException("Password cannot be empty");

        User user = dao.getByEmail(email);
        if(user.getPassword().equals(password)) return user;
        else throw new CredentialsException("Wrong password");
    }

    public Manager isManager(User user) throws CredentialsException{
        try{
            return dao.getManager(user);
        } catch (SQLException e){
            return null;
        }
    }

    public Reviewer isReviewer(User user) throws CredentialsException{
        try{
            return dao.getReviewer(user);
        } catch (SQLException e){
            return null;
        }
    }

    public User getUserInfoByEmail(String email) throws CredentialsException, SQLException{
        User user = dao.getByEmail(email);
        return user;
    }

    private boolean checkCredentialsFormat(String username, String email, String password, String repeatPassword) throws CredentialsException{

        //Controllo email
        if(email == null || email.equals("")){
            throw new CredentialsException("Invalid email");
        }
        if(email.length()>50 || email.length()<8) throw new CredentialsException("Email maximum size exceeded");
        Pattern mail_pattern = Pattern.compile("^\\w+([.-]?\\w+)@\\w+([.-]?\\w+)(\\.\\w{2,3})+$");
        Matcher mail_matcher = mail_pattern.matcher(email);
        if(!mail_matcher.find()) throw new CredentialsException("Invalid email format");
        if(emailAlreadyUsed(email)) throw new CredentialsException("Email already in use");

        //Controllo username
        if(username == null || username.equals("")){
            throw new CredentialsException("Invalid username");
        }
        Pattern username_pattern = Pattern.compile("^\\w{4,50}$");
        Matcher username_matcher = username_pattern.matcher(username);
        if(!username_matcher.find()) throw new CredentialsException("Invalid username format");

        //Controllo password
        if(password == null || password.equals("")){
            throw new CredentialsException("Invalid password");
        }
        Pattern password_pattern = Pattern.compile("^^(?=.*[!@#$%&])(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,50}");
        Matcher password_matcher = password_pattern.matcher(password);
        if(!password_matcher.find()) throw new CredentialsException("Invalid password format");

        //Controllo repeatPassword
        if(!password.equals(repeatPassword)) throw new CredentialsException("Password must match");


        /*
        if(username==null || email==null || password==null || repeatPassword==null) throw new CredentialsException("Field/s cannot be null");
        if(username.equals("") || email.equals("") || password.equals("") || repeatPassword.equals("")) throw new CredentialsException("Field/s cannot be empty");

        if(!password.equals(repeatPassword)) throw new CredentialsException("Password must match");

        if(email.length()>50 || email.length()<8) throw new CredentialsException("Email maximum size exceeded");

        Pattern mail_pattern = Pattern.compile("^\\w+([.-]?\\w+)@\\w+([.-]?\\w+)(\\.\\w{2,3})+$");
        Matcher mail_matcher = mail_pattern.matcher(email);
        if(!mail_matcher.find()) throw new CredentialsException("Invalid email format");

        Pattern username_pattern = Pattern.compile("^\\w{4,50}$");
        Matcher username_matcher = username_pattern.matcher(username);
        if(!username_matcher.find()) throw new CredentialsException("Invalid username format");

        Pattern password_pattern = Pattern.compile("^^(?=.*[!@#$%&])(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,50}");
        Matcher password_matcher = password_pattern.matcher(password);
        if(!password_matcher.find()) throw new CredentialsException("Invalid password format");
        */
        return true;
    }

    private boolean emailAlreadyUsed(String email) throws CredentialsException{
        if(email==null || email.equals("")) throw new CredentialsException("Email cannot be null or empty");

        try{
            dao.getByEmail(email);
            return true;
        } catch(SQLException e){
            return false;
        }
    }

    public boolean deleteUser(int id) throws SQLException{

        return dao.deleteUserById(id);

    }

    private static UserDao dao;
}
