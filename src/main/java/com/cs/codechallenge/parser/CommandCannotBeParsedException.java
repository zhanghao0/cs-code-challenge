package com.cs.codechallenge.parser;

public class CommandCannotBeParsedException extends CommandParsingException {

	private static final long serialVersionUID = 5108848741324407164L;

	public CommandCannotBeParsedException(String message) {
		super(message);
	}

}
