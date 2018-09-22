package com.cs.codechallenge.parser;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cs.codechallenge.command.BucketFillCommand;
import com.cs.codechallenge.drawing.DrawingContext;
import com.cs.codechallenge.io.InvalidValueException;
import com.cs.codechallenge.parser.BucketFillCommandParser;
import com.cs.codechallenge.parser.CommandParsingException;
import com.cs.codechallenge.parser.IncorrectNumberOfCommandArgumentException;

@ExtendWith(MockitoExtension.class)
public class BucketFillCommandParserTest {

	@Mock
	private DrawingContext drawingContext;
	
	@Test
	public void givenCommandTypeIsNull_whenConsturctor_thenThrowException() {
		String commandType = null;
				
		assertThrows(IllegalArgumentException.class, () -> new BucketFillCommandParser(commandType, this.drawingContext));
	}
	
	@Test
	public void givenDrawingContextIsNull_whenConsturctor_thenThrowException() {
		DrawingContext drawingContext = null;
				
		assertThrows(IllegalArgumentException.class, () -> new BucketFillCommandParser(drawingContext));
	}
	
	@Test
	public void givenCommandTextIsNull_whenParse_thenReturnNull() throws CommandParsingException {
		String commandText = null;
		BucketFillCommandParser parser = new BucketFillCommandParser(this.drawingContext);
		
		BucketFillCommand command = parser.parse(commandText);
		
		assertNull(command);
	}
	
	@Test
	public void givenCommandTextIsNotAcceptable_whenParse_thenReturnNull() throws CommandParsingException {
		String commandText = "e";
		BucketFillCommandParser parser = new BucketFillCommandParser(this.drawingContext);
		
		BucketFillCommand command = parser.parse(commandText);
		
		assertNull(command);
	}
	
	@Test
	public void givenCommandTextContainsMoreThan3Tokens_whenParse_thenThrowException() throws CommandParsingException {
		String commandText = "B 1 1 1 1 ";
		BucketFillCommandParser parser = new BucketFillCommandParser(this.drawingContext);
		
		assertThrows(IncorrectNumberOfCommandArgumentException.class, () -> parser.parse(commandText));
	}
	
	@Test
	public void givenCommandTextContainsLessThan3Tokens_whenParse_thenThrowException() throws CommandParsingException {
		String commandText = "B 1 1 ";
		BucketFillCommandParser parser = new BucketFillCommandParser(this.drawingContext);
		
		assertThrows(IncorrectNumberOfCommandArgumentException.class, () -> parser.parse(commandText));
	}
	
	@Test
	public void givenCommandTextContainsNonInteger_whenParse_thenThrowException() throws CommandParsingException {
		String commandText = "B 1 1.0 o";
		BucketFillCommandParser parser = new BucketFillCommandParser(this.drawingContext);
		
		assertThrows(InvalidValueException.class, () -> parser.parse(commandText));
	}
	
	@Test
	public void givenColorCharacterHas2Letters_whenParse_thenThrowException() throws CommandParsingException {
		String commandText = "B 1 1 ab";
		BucketFillCommandParser parser = new BucketFillCommandParser(this.drawingContext);
		
		assertThrows(InvalidValueException.class, () -> parser.parse(commandText));
	}
	
	@Test
	public void givenCommandTextIsCorrect_whenParse_thenReturnCommand() throws CommandParsingException {
		String commandText = "B 1 1 o";		
		BucketFillCommandParser parser = new BucketFillCommandParser(this.drawingContext);
		
		BucketFillCommand command = parser.parse(commandText);
		
		assertNotNull(command);
	}
	
	@Test
	public void givenCommandTypeIsCustomized_whenParse_thenReturnCommand() throws CommandParsingException {
		String commandType = "BF";
		String commandText = "BF 1 2 o";
		BucketFillCommandParser parser = new BucketFillCommandParser(commandType, this.drawingContext);
		
		BucketFillCommand command = parser.parse(commandText);
		
		assertNotNull(command);
	}
}
