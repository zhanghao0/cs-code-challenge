package com.cs.codechallenge.parser;

public class InvalidCommandArgumentException extends CommandParsingException {

	private static final long serialVersionUID = 3857281310539324575L;

	public InvalidCommandArgumentException(String message) {
		super(message);
	}

}
