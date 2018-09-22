package com.cs.codechallenge.parser;

import java.util.List;

import com.cs.codechallenge.command.Command;

public class CompositeCommandParser implements CommandParser<Command> {
	private final List<CommandParser<? extends Command>> commandParsers;
	
	public CompositeCommandParser(List<CommandParser<? extends Command>> commandParsers) {
		super();
		
		if (commandParsers == null || commandParsers.isEmpty()) {
			throw new IllegalArgumentException("commandParsers should not be null or empty");
		}
		
		for (CommandParser<? extends Command> commandParser : commandParsers) {
			if (commandParser == null) {
				throw new IllegalArgumentException("commandParsers should not be null or empty");
			}
		}
		
		this.commandParsers = commandParsers;
	}

	@Override
	public Command parse(String commandText) throws CommandParsingException {
		for (CommandParser<? extends Command> commandParser : this.commandParsers) {
			Command command = commandParser.parse(commandText);
			
			if (command != null)
				return command;
		}
		
		throw new CommandCannotBeParsedException("The text " + commandText + " cannot be parsed.");
	}

}
