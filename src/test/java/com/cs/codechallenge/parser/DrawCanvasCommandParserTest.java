package com.cs.codechallenge.parser;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cs.codechallenge.command.DrawCanvasCommand;
import com.cs.codechallenge.drawing.CanvasConfig;
import com.cs.codechallenge.drawing.DrawingContext;
import com.cs.codechallenge.io.InvalidValueException;
import com.cs.codechallenge.parser.CommandParsingException;
import com.cs.codechallenge.parser.DrawCanvasCommandParser;
import com.cs.codechallenge.parser.IncorrectNumberOfCommandArgumentException;

@ExtendWith(MockitoExtension.class)
public class DrawCanvasCommandParserTest {

	@Mock
	private DrawingContext drawingContext;
	
	@Test
	public void givenCommandTypeIsNull_whenConsturctor_thenThrowException() {
		String commandType = null;
				
		assertThrows(IllegalArgumentException.class, () -> new DrawCanvasCommandParser(commandType, this.drawingContext, new CanvasConfig()));
	}
	
	@Test
	public void givenDrawingContextIsNull_whenConsturctor_thenThrowException() {
		DrawingContext drawingContext = null;
				
		assertThrows(IllegalArgumentException.class, () -> new DrawCanvasCommandParser(drawingContext));
	}
	
	@Test
	public void givenCanvasConfigIsNull_whenConsturctor_thenThrowException() {
		CanvasConfig drawableCanvasConfig = null;
				
		assertThrows(IllegalArgumentException.class, () -> new DrawCanvasCommandParser("C", this.drawingContext, drawableCanvasConfig));
	}
	
	@Test
	public void givenCommandTextIsNull_whenParse_thenReturnNull() throws CommandParsingException {
		String commandText = null;
		DrawCanvasCommandParser parser = new DrawCanvasCommandParser(this.drawingContext);
		
		DrawCanvasCommand command = parser.parse(commandText);
		
		assertNull(command);
	}
	
	@Test
	public void givenCommandTextIsNotAcceptable_whenParse_thenReturnNull() throws CommandParsingException {
		String commandText = "e";
		DrawCanvasCommandParser parser = new DrawCanvasCommandParser(this.drawingContext);
		
		DrawCanvasCommand command = parser.parse(commandText);
		
		assertNull(command);
	}
	
	@Test
	public void givenCommandTextContainsMoreThan3Tokens_whenParse_thenThrowException() throws CommandParsingException {
		String commandText = "C 1 1 1";
		DrawCanvasCommandParser parser = new DrawCanvasCommandParser(this.drawingContext);
		
		assertThrows(IncorrectNumberOfCommandArgumentException.class, () -> parser.parse(commandText));
	}
	
	@Test
	public void givenCommandTextContainsLessThanFiveTokens_whenParse_thenThrowException() throws CommandParsingException {
		String commandText = "C 1";
		DrawCanvasCommandParser parser = new DrawCanvasCommandParser(this.drawingContext);
		
		assertThrows(IncorrectNumberOfCommandArgumentException.class, () -> parser.parse(commandText));
	}
	
	@Test
	public void givenCommandTextContainsNonInteger_whenParse_thenThrowException() throws CommandParsingException {
		String commandText = "C 1.0 1";
		DrawCanvasCommandParser parser = new DrawCanvasCommandParser(this.drawingContext);
		
		assertThrows(InvalidValueException.class, () -> parser.parse(commandText));
	}
	
	@Test
	public void givenCommandTextContainsLetter_whenParse_thenThrowException() throws CommandParsingException {
		String commandText = "C A 1";
		DrawCanvasCommandParser parser = new DrawCanvasCommandParser(this.drawingContext);
		
		assertThrows(InvalidValueException.class, () -> parser.parse(commandText));
	}
	
	@Test
	public void givenCommandTextIsCorrect_whenParse_thenReturnCommand() throws CommandParsingException {
		String commandText = "C 2 4";		
		DrawCanvasCommandParser parser = new DrawCanvasCommandParser(this.drawingContext);
		
		DrawCanvasCommand command = parser.parse(commandText);
		
		assertNotNull(command);
	}
	
	@Test
	public void givenCommandTextIsCorrectAndLowerCase_whenParse_thenReturnCommand() throws CommandParsingException {
		String commandText = "c 2 4";
		DrawCanvasCommandParser parser = new DrawCanvasCommandParser(this.drawingContext);
		
		DrawCanvasCommand command = parser.parse(commandText);
		
		assertNotNull(command);
	}
	
	@Test
	public void givenCommandTypeIsCustomized_whenParse_thenReturnCommand() throws CommandParsingException {
		String commandType = "CAN";
		String commandText = "CAN 10 5";
		DrawCanvasCommandParser parser = new DrawCanvasCommandParser(commandType, this.drawingContext, new CanvasConfig());
		
		DrawCanvasCommand command = parser.parse(commandText);
		
		assertNotNull(command);
	}
}
