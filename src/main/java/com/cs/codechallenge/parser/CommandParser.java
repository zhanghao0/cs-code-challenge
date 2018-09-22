package com.cs.codechallenge.parser;

import com.cs.codechallenge.command.Command;

public interface CommandParser<T extends Command> {
	T parse(String commandText) throws CommandParsingException;
}
