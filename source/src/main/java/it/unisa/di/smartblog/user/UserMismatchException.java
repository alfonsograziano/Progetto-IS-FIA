package it.unisa.di.smartblog.user;

public class UserMismatchException extends Exception{
    public UserMismatchException(String message){
        super(message);
    }
}
