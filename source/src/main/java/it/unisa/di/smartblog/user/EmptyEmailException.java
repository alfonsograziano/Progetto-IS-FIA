package it.unisa.di.smartblog.user;

public class EmptyEmailException extends Exception{
    public EmptyEmailException(String message){
        super(message);
    }
}
