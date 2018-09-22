package com.cs.codechallenge.parser;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cs.codechallenge.ExitApplicationListener;
import com.cs.codechallenge.command.Command;
import com.cs.codechallenge.drawing.DrawingContext;
import com.cs.codechallenge.parser.BucketFillCommandParser;
import com.cs.codechallenge.parser.CommandCannotBeParsedException;
import com.cs.codechallenge.parser.CommandParser;
import com.cs.codechallenge.parser.CommandParsingException;
import com.cs.codechallenge.parser.CompositeCommandParser;
import com.cs.codechallenge.parser.QuitCommandParser;

@ExtendWith(MockitoExtension.class)
public class CompositeCommandParserTest {

	@Mock
	private DrawingContext drawingContext;
	
	@Test
	public void givenCommandParsersAreNull_whenConsturctor_thenThrowException() {
		List<CommandParser<? extends Command>> commandParsers = null;
				
		assertThrows(IllegalArgumentException.class, () -> new CompositeCommandParser(commandParsers));
	}
	
	@Test
	public void givenCommandParsersAreEmpty_whenConsturctor_thenThrowException() {
		List<CommandParser<? extends Command>> commandParsers = Collections.emptyList();
				
		assertThrows(IllegalArgumentException.class, () -> new CompositeCommandParser(commandParsers));
	}
	
	@Test
	public void givenCommandParsersContainNull_whenConsturctor_thenThrowException() {
		List<CommandParser<? extends Command>> commandParsers = new ArrayList<>();
		commandParsers.add(null);
				
		assertThrows(IllegalArgumentException.class, () -> new CompositeCommandParser(commandParsers));
	}
	
	@Test
	public void givenCommandTextIsInvalid_whenParse_thenThrowException() throws CommandParsingException {
		ExitApplicationListener listener = mock(ExitApplicationListener.class);
		
		List<CommandParser<? extends Command>> commandParsers = new ArrayList<>();
		commandParsers.add(new QuitCommandParser(listener));
		
		String commandText = "e 1 23";
		CompositeCommandParser commandParser = new CompositeCommandParser(commandParsers);
				
		assertThrows(CommandCannotBeParsedException.class, () -> commandParser.parse(commandText));
	}
	
	@Test
	public void givenFirstParserMatches_whenParse_thenReturnCommand() throws CommandParsingException {
		ExitApplicationListener listener = mock(ExitApplicationListener.class);
		
		List<CommandParser<? extends Command>> commandParsers = new ArrayList<>();
		commandParsers.add(new QuitCommandParser(listener));
		commandParsers.add(new BucketFillCommandParser(this.drawingContext));
		
		String commandText = "Q";
		CompositeCommandParser commandParser = new CompositeCommandParser(commandParsers);
				
		Command command = commandParser.parse(commandText);
		
		assertNotNull(command);
	}
	
	@Test
	public void givenSecondParserMatches_whenParse_thenReturnCommand() throws CommandParsingException {
		ExitApplicationListener listener = mock(ExitApplicationListener.class);
		
		List<CommandParser<? extends Command>> commandParsers = new ArrayList<>();
		commandParsers.add(new QuitCommandParser(listener));
		commandParsers.add(new BucketFillCommandParser(this.drawingContext));
		
		String commandText = "B 1 1 o";
		CompositeCommandParser commandParser = new CompositeCommandParser(commandParsers);
				
		Command command = commandParser.parse(commandText);
		
		assertNotNull(command);
	}

}
