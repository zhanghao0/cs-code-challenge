package com.cs.codechallenge.parser;

import com.cs.codechallenge.command.Command;
import com.cs.codechallenge.io.FieldSet;
import com.cs.codechallenge.tokenizer.DelimitedLineTokenizer;
import com.cs.codechallenge.tokenizer.LineTokenizer;

public abstract class AbstractCommandParser<T extends Command> implements CommandParser<T> {
	private String commandType;

	private LineTokenizer lineTokenizer = new DelimitedLineTokenizer();
	private boolean commandTypeIgnoreCase = true;

	public AbstractCommandParser(String commandType) {
		super();
		
		if (commandType == null) {
			throw new IllegalArgumentException("commandType should not be null");
		}
		
		this.commandType = commandType;
	}

	public void setLineTokenizer(LineTokenizer lineTokenizer) {
		if (lineTokenizer != null) {
			this.lineTokenizer = lineTokenizer;
		}
	}

	public void setCommandTypeIgnoreCase(boolean commandTypeIgnoreCase) {
		this.commandTypeIgnoreCase = commandTypeIgnoreCase;
	}

	@Override
	public T parse(String commandText) throws CommandParsingException {
		FieldSet fieldSet = this.lineTokenizer.tokenize(commandText);
		
		if (fieldSet == null)
			return null;
		
		if (fieldSet.getFieldCount() < 1)
			return null;
		
		return parse(commandText, fieldSet);
	}
	
	protected boolean isAcceptableCommandType(String type) {
		return this.commandTypeIgnoreCase ? this.commandType.equalsIgnoreCase(type) : this.commandType.equals(type);
	}

	protected abstract T parse(String commandText, FieldSet fieldSet) throws CommandParsingException;
}
