package com.cs.codechallenge.parser;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

import com.cs.codechallenge.ExitApplicationListener;
import com.cs.codechallenge.command.QuitCommand;
import com.cs.codechallenge.parser.CommandParsingException;
import com.cs.codechallenge.parser.IncorrectNumberOfCommandArgumentException;
import com.cs.codechallenge.parser.QuitCommandParser;

public class QuitCommandParserTest {
	
	@Test
	public void givenCommandTypeIsNull_whenConsturctor_thenThrowException() {
		String commandType = null;
		ExitApplicationListener listener = mock(ExitApplicationListener.class);
				
		assertThrows(IllegalArgumentException.class, () -> new QuitCommandParser(commandType, listener));
	}
	
	@Test
	public void givenListenerIsNull_whenConstruct_thenThrowException()  {
		ExitApplicationListener listener = null;
		
		assertThrows(IllegalArgumentException.class, () -> new QuitCommandParser(listener));
	}
	
	
	@Test
	public void givenCommandTextIsNull_whenParse_thenReturnNull() throws CommandParsingException {
		String commandText = null;
		QuitCommandParser parser = this.createParser();
		
		QuitCommand command = parser.parse(commandText);
		
		assertNull(command);
	}
	
	@Test
	public void givenCommandTypeIsNotAcceptable_whenParse_thenReturnNull() throws CommandParsingException {
		String commandText = "e";
		QuitCommandParser parser = this.createParser();
		
		QuitCommand command = parser.parse(commandText);
		
		assertNull(command);
	}
	
	@Test
	public void givenCommandTextContainsMoreThanOneTokens_whenParse_thenThrowException() throws CommandParsingException {
		String commandText = "Q Q";
		QuitCommandParser parser = this.createParser();
		
		assertThrows(IncorrectNumberOfCommandArgumentException.class, () -> parser.parse(commandText));
	}
	
	@Test
	public void givenCommandTextIsCorrect_whenParse_thenReturnCommand() throws CommandParsingException {
		String commandText = "Q";
		QuitCommandParser parser = this.createParser();
		
		QuitCommand command = parser.parse(commandText);
		
		assertNotNull(command);
	}
	
	@Test
	public void givenCommandTextIsCorrectAndLowerCase_whenParse_thenReturnCommand() throws CommandParsingException {
		String commandText = "q";
		QuitCommandParser parser = this.createParser();
		
		QuitCommand command = parser.parse(commandText);
		
		assertNotNull(command);
	}
	
	@Test
	public void givenCommandTextIsLowerCaseAndIgnoreCaseIsFalse_whenParse_thenReturnNull() throws CommandParsingException {
		String commandText = "q";
		QuitCommandParser parser = this.createParser();
		parser.setCommandTypeIgnoreCase(false);
		
		QuitCommand command = parser.parse(commandText);
		
		assertNull(command);
	}
	
	@Test
	public void givenCommandTypeIsCustomized_whenParse_thenReturnCommand() throws CommandParsingException {
		ExitApplicationListener listener = mock(ExitApplicationListener.class);
		String commandType = "QUIT";
		QuitCommandParser parser = new QuitCommandParser(commandType, listener);
		
		QuitCommand command = parser.parse(commandType);
		
		assertNotNull(command);
	}
	
	private QuitCommandParser createParser() {
		ExitApplicationListener listener = mock(ExitApplicationListener.class);
		QuitCommandParser parser = new QuitCommandParser(listener);
		
		return parser;
	}
}
