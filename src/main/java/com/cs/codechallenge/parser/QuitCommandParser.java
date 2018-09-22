package com.cs.codechallenge.parser;

import com.cs.codechallenge.ExitApplicationListener;
import com.cs.codechallenge.command.QuitCommand;
import com.cs.codechallenge.io.FieldSet;

public class QuitCommandParser extends AbstractCommandParser<QuitCommand> {
	public static final String COMMAND_TYPE = "Q";
	public static final int COMMAND_ARGUMENT_LENGTH = 1;
	
	private final ExitApplicationListener listener;

	public QuitCommandParser(String commandType, ExitApplicationListener listener) {
		super(commandType);	
		
		if (listener == null) {
			throw new IllegalArgumentException("listener should not be null");
		}
		
		this.listener = listener;
	}

	public QuitCommandParser(ExitApplicationListener listener) {
		this(COMMAND_TYPE, listener);	
	}

	@Override
	protected QuitCommand parse(String commandText, FieldSet fieldSet) throws CommandParsingException {
		String type = fieldSet.readString(0);
		
		if (!isAcceptableCommandType(type))
			return null;

		if (fieldSet.getFieldCount() != COMMAND_ARGUMENT_LENGTH)
			throw new IncorrectNumberOfCommandArgumentException("Too many or few command arguments: " + commandText);
		
		return new QuitCommand(this.listener);
	}

}
