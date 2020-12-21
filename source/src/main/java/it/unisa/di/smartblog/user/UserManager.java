package it.unisa.di.smartblog.user;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserManager {
    static{
        dao = new UserDao();
    }

    public boolean createUser(String username, String email, String password, String repeatPassword) throws UserMismatchException, EmptyEmailException, SQLException{
        if(!emailAlreadyUsed(email)) {
            if(checkCredentialsFormat(username, email, password, repeatPassword)) {
                User user = new User(username, email, password);
                dao.saveUser(user);
            }
        }

        return true;
    }

    public User auth(String email, String password) throws CredentialMismatchException, EmptyEmailException, SQLException{
        User user = dao.getByEmail(email);
        if(user.getPassword().equals(password)) return user;
        else throw new CredentialMismatchException("Invalid credentials");
    }

    public User getUserInfoByEmail(String email) throws EmptyEmailException, SQLException{
        User user = dao.getByEmail(email);
        return user;
    }

    private boolean checkCredentialsFormat(String username, String email, String password, String repeatPassword) throws  UserMismatchException{
        if(username==null || email==null || password==null || repeatPassword==null) throw new UserMismatchException("Field/s cannot be null");
        if(username.equals("") || email.equals("") || password.equals("") || repeatPassword.equals("")) throw new UserMismatchException("Field/s cannot be empty");

        if(!password.equals(repeatPassword)) throw new UserMismatchException("Password must match");

        if(email.length()>50 || email.length()<8) throw new UserMismatchException("Email maximum size exceeded");

        Pattern pattern = Pattern.compile("^\\w+([.-]?\\w+)@\\w+([.-]?\\w+)(.\\w{2,3})+$");
        Matcher matcher = pattern.matcher(email);
        if(!matcher.find()) throw new UserMismatchException("Invalid email format");

        pattern = Pattern.compile("/w{4,50}");
        matcher = pattern.matcher(username);
        if(!matcher.find()) throw new UserMismatchException("Invalid username format");

        pattern = Pattern.compile("^((?=.[A-Z])(?=.[a-z])(?=.*[0-9])).{8,50}$");
        matcher = pattern.matcher(password);
        if(!matcher.find()) throw new UserMismatchException("Invalid password format");

        return true;
    }

    private boolean emailAlreadyUsed(String email) throws EmptyEmailException{
        if(email==null || email.equals("")) throw new EmptyEmailException("Email cannot be null or empty");

        try{
            dao.getByEmail(email);
            return true;
        } catch(SQLException e){
            return false;
        }
    }

    private static UserDao dao;
}
