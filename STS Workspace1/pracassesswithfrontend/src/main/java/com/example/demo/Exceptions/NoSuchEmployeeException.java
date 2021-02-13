package com.example.demo.Exceptions;

public class NoSuchEmployeeException extends Exception {
	
	public NoSuchEmployeeException(String msg) {
		super(msg);
	}

	public String toString() {
		return super.toString()+super.getMessage();
	}
}
