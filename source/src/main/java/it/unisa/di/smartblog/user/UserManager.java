package it.unisa.di.smartblog.user;

import it.unisa.di.smartblog.spec.SpecMismatchException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserManager {
    static{
        dao = new UserDao();
    }

    private boolean checkCradentialsFormat(String username, String email, String password, String repeatPassword) throws  UserMismatchException{
        if(username==null || email==null || password==null || repeatPassword==null) throw new UserMismatchException("Field/s cannot be null");
        if(username.equals("") || email.equals("") || password.equals("") || repeatPassword.equals("")) throw new UserMismatchException("Field/s cannot be empty");

        Pattern pattern = Pattern.compile("^([\\w\\.\\-]+)@([\\w\\-]+)((\\.(\\w){2,3})+)$");
        Matcher matcher = pattern.matcher(email);
        if(!matcher.find()) throw new UserMismatchException("Invalid email format");

        if(!password.equals(repeatPassword)) throw new UserMismatchException("Password must match");
    }

    private boolean emailAlreadyUsed(String email){

    }

    private static UserDao dao;
}
