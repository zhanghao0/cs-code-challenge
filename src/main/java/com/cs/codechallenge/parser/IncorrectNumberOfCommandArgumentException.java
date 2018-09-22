package com.cs.codechallenge.parser;

public class IncorrectNumberOfCommandArgumentException extends CommandParsingException {

	private static final long serialVersionUID = 4058262886649743691L;

	public IncorrectNumberOfCommandArgumentException(String message) {
		super(message);
	}

}
