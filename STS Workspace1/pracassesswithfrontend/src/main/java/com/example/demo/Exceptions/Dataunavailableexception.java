package com.example.demo.Exceptions;

public class Dataunavailableexception extends Exception {
	
	public Dataunavailableexception(String msg) {
		super(msg);
	}

	public String toString() {
		return super.toString()+super.getMessage();
	}
}
