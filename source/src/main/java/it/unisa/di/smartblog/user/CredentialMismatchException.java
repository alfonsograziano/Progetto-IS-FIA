package it.unisa.di.smartblog.user;

public class CredentialMismatchException extends Exception{
    public CredentialMismatchException(String message){
        super(message);
    }
}
