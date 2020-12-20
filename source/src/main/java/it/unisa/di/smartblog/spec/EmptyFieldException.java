package it.unisa.di.smartblog.spec;

public class EmptyFieldException extends Exception{
	private static final long serialVersionUID = -6627587229679611188L;
	
	public EmptyFieldException(String message) {
		super(message);
	}
}
