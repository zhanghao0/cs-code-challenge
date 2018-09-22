package com.cs.codechallenge.parser;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cs.codechallenge.command.DrawRectangleCommand;
import com.cs.codechallenge.drawing.DrawableRectangleConfig;
import com.cs.codechallenge.drawing.DrawingContext;
import com.cs.codechallenge.io.InvalidValueException;
import com.cs.codechallenge.parser.CommandParsingException;
import com.cs.codechallenge.parser.DrawRectangleCommandParser;
import com.cs.codechallenge.parser.IncorrectNumberOfCommandArgumentException;

@ExtendWith(MockitoExtension.class)
public class DrawRectangleCommandParserTest {

	@Mock
	private DrawingContext drawingContext;
	
	@Test
	public void givenCommandTypeIsNull_whenConsturctor_thenThrowException() {
		String commandType = null;
				
		assertThrows(IllegalArgumentException.class, () -> new DrawRectangleCommandParser(commandType, this.drawingContext, new DrawableRectangleConfig()));
	}
	
	@Test
	public void givenDrawingContextIsNull_whenConsturctor_thenThrowException() {
		DrawingContext drawingContext = null;
				
		assertThrows(IllegalArgumentException.class, () -> new DrawRectangleCommandParser(drawingContext));
	}
	
	@Test
	public void givenDrawableRectangleConfigIsNull_whenConsturctor_thenThrowException() {
		DrawableRectangleConfig drawableRectangleConfig = null;
				
		assertThrows(IllegalArgumentException.class, () -> new DrawRectangleCommandParser("R", this.drawingContext, drawableRectangleConfig));
	}
	
	@Test
	public void givenCommandTextIsNull_whenParse_thenReturnNull() throws CommandParsingException {
		String commandText = null;
		DrawRectangleCommandParser parser = new DrawRectangleCommandParser(this.drawingContext);
		
		DrawRectangleCommand command = parser.parse(commandText);
		
		assertNull(command);
	}
	
	@Test
	public void givenCommandTextIsNotAcceptable_whenParse_thenReturnNull() throws CommandParsingException {
		String commandText = "e";
		DrawRectangleCommandParser parser = new DrawRectangleCommandParser(this.drawingContext);
		
		DrawRectangleCommand command = parser.parse(commandText);
		
		assertNull(command);
	}
	
	@Test
	public void givenCommandTextContainsMoreThanFiveTokens_whenParse_thenThrowException() throws CommandParsingException {
		String commandText = "R 1 1 1 1 1";
		DrawRectangleCommandParser parser = new DrawRectangleCommandParser(this.drawingContext);
		
		assertThrows(IncorrectNumberOfCommandArgumentException.class, () -> parser.parse(commandText));
	}
	
	@Test
	public void givenCommandTextContainsLessThanFiveTokens_whenParse_thenThrowException() throws CommandParsingException {
		String commandText = "R 1 1 1";
		DrawRectangleCommandParser parser = new DrawRectangleCommandParser(this.drawingContext);
		
		assertThrows(IncorrectNumberOfCommandArgumentException.class, () -> parser.parse(commandText));
	}
	
	@Test
	public void givenCommandTextContainsNonInteger_whenParse_thenThrowException() throws CommandParsingException {
		String commandText = "R 1 1 1.0 1";
		DrawRectangleCommandParser parser = new DrawRectangleCommandParser(this.drawingContext);
		
		assertThrows(InvalidValueException.class, () -> parser.parse(commandText));
	}
	
	@Test
	public void givenCommandTextContainsLetter_whenParse_thenThrowException() throws CommandParsingException {
		String commandText = "R 1 1 A 1";
		DrawRectangleCommandParser parser = new DrawRectangleCommandParser(this.drawingContext);
		
		assertThrows(InvalidValueException.class, () -> parser.parse(commandText));
	}
	
	@Test
	public void givenSameStartPointAndEndPoint_whenParse_thenThrowException() throws CommandParsingException {
		String commandText = "R 1 1 1 1";
		DrawRectangleCommandParser parser = new DrawRectangleCommandParser(this.drawingContext);
		
		assertThrows(IllegalArgumentException.class, () -> parser.parse(commandText));
	}
	
	@Test
	public void givenStartPointAndEndPointOnSameHorizontalLine_whenParse_thenThrowException() throws CommandParsingException {
		String commandText = "R 1 2 3 2";
		DrawRectangleCommandParser parser = new DrawRectangleCommandParser(this.drawingContext);
		
		assertThrows(IllegalArgumentException.class, () -> parser.parse(commandText));
	}
	
	@Test
	public void givenStartPointAndEndPointOnSameVerticalLine_whenParse_thenThrowException() throws CommandParsingException {
		String commandText = "R 1 2 1 3";
		DrawRectangleCommandParser parser = new DrawRectangleCommandParser(this.drawingContext);
		
		assertThrows(IllegalArgumentException.class, () -> parser.parse(commandText));
	}
	
	@Test
	public void givenCommandTextIsCorrect_whenParse_thenReturnCommand() throws CommandParsingException {
		String commandText = "R 1 2 3 4";		
		DrawRectangleCommandParser parser = new DrawRectangleCommandParser(this.drawingContext);
		
		DrawRectangleCommand command = parser.parse(commandText);
		
		assertNotNull(command);
	}
	
	@Test
	public void givenCommandTextIsCorrectAndLowerCase_whenParse_thenReturnCommand() throws CommandParsingException {
		String commandText = "r 1 2 3 4";
		DrawRectangleCommandParser parser = new DrawRectangleCommandParser(this.drawingContext);
		
		DrawRectangleCommand command = parser.parse(commandText);
		
		assertNotNull(command);
	}
	
	@Test
	public void givenCommandTypeIsCustomized_whenParse_thenReturnCommand() throws CommandParsingException {
		String commandType = "RECT";
		String commandText = "RECT 1 2 3 4";
		DrawRectangleCommandParser parser = new DrawRectangleCommandParser(commandType, this.drawingContext, new DrawableRectangleConfig());
		
		DrawRectangleCommand command = parser.parse(commandText);
		
		assertNotNull(command);
	}
}
