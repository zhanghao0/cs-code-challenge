package com.cs.codechallenge.parser;

public abstract class CommandParsingException extends Exception {

	private static final long serialVersionUID = -822848061305913621L;

	public CommandParsingException(String message) {
		super(message);
	}

}
