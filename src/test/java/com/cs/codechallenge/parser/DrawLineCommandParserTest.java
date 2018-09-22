package com.cs.codechallenge.parser;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cs.codechallenge.command.DrawLineCommand;
import com.cs.codechallenge.drawing.DrawableLineConfig;
import com.cs.codechallenge.drawing.DrawingContext;
import com.cs.codechallenge.io.InvalidValueException;
import com.cs.codechallenge.parser.CommandParsingException;
import com.cs.codechallenge.parser.DrawLineCommandParser;
import com.cs.codechallenge.parser.IncorrectNumberOfCommandArgumentException;

@ExtendWith(MockitoExtension.class)
public class DrawLineCommandParserTest {

	@Mock
	private DrawingContext drawingContext;
	
	@Test
	public void givenCommandTypeIsNull_whenConsturctor_thenThrowException() {
		String commandType = null;
				
		assertThrows(IllegalArgumentException.class, () -> new DrawLineCommandParser(commandType, this.drawingContext, new DrawableLineConfig()));
	}
	
	@Test
	public void givenDrawingContextIsNull_whenConsturctor_thenThrowException() {
		DrawingContext drawingContext = null;
				
		assertThrows(IllegalArgumentException.class, () -> new DrawLineCommandParser(drawingContext));
	}
	
	@Test
	public void givenDrawableLineConfigIsNull_whenConsturctor_thenThrowException() {
		DrawableLineConfig drawableLineConfig = null;
				
		assertThrows(IllegalArgumentException.class, () -> new DrawLineCommandParser("L", this.drawingContext, drawableLineConfig));
	}
	
	@Test
	public void givenCommandTextIsNull_whenParse_thenReturnNull() throws CommandParsingException {
		String commandText = null;
		DrawLineCommandParser parser = new DrawLineCommandParser(this.drawingContext);
		
		DrawLineCommand command = parser.parse(commandText);
		
		assertNull(command);
	}
	
	@Test
	public void givenCommandTextIsNotAcceptable_whenParse_thenReturnNull() throws CommandParsingException {
		String commandText = "e";
		DrawLineCommandParser parser = new DrawLineCommandParser(this.drawingContext);
		
		DrawLineCommand command = parser.parse(commandText);
		
		assertNull(command);
	}
	
	@Test
	public void givenCommandTextContainsMoreThan5Tokens_whenParse_thenThrowException() throws CommandParsingException {
		String commandText = "L 1 1 1 1 1";
		DrawLineCommandParser parser = new DrawLineCommandParser(this.drawingContext);
		
		assertThrows(IncorrectNumberOfCommandArgumentException.class, () -> parser.parse(commandText));
	}
	
	@Test
	public void givenCommandTextContainsLessThan5Tokens_whenParse_thenThrowException() throws CommandParsingException {
		String commandText = "L 1 1 1";
		DrawLineCommandParser parser = new DrawLineCommandParser(this.drawingContext);
		
		assertThrows(IncorrectNumberOfCommandArgumentException.class, () -> parser.parse(commandText));
	}
	
	@Test
	public void givenCommandTextContainsNonInteger_whenParse_thenThrowException() throws CommandParsingException {
		String commandText = "L 1 1 1.0 1";
		DrawLineCommandParser parser = new DrawLineCommandParser(this.drawingContext);
		
		assertThrows(InvalidValueException.class, () -> parser.parse(commandText));
	}
	
	@Test
	public void givenCommandTextContainsLetter_whenParse_thenThrowException() throws CommandParsingException {
		String commandText = "L 1 1 A 1";
		DrawLineCommandParser parser = new DrawLineCommandParser(this.drawingContext);
		
		assertThrows(InvalidValueException.class, () -> parser.parse(commandText));
	}
	
	@Test
	public void givenSameStartPointAndEndPoint_whenParse_thenThrowException() throws CommandParsingException {
		String commandText = "L 1 1 1 1";
		DrawLineCommandParser parser = new DrawLineCommandParser(this.drawingContext);
		
		assertThrows(IllegalArgumentException.class, () -> parser.parse(commandText));
	}
	
	@Test
	public void givenStartPointAndEndPointNotOnSameHorizontalOrVerticalLine_whenParse_thenThrowException() throws CommandParsingException {
		String commandText = "L 1 2 3 4";
		DrawLineCommandParser parser = new DrawLineCommandParser(this.drawingContext);
		
		assertThrows(IllegalArgumentException.class, () -> parser.parse(commandText));
	}
	
	@Test
	public void givenStartPointAndEndPointOnSameHorizontalLine_whenParse_thenReturnCommand() throws CommandParsingException {
		String commandText = "L 1 2 3 2";
		DrawLineCommandParser parser = new DrawLineCommandParser(this.drawingContext);

		DrawLineCommand command = parser.parse(commandText);
		
		assertNotNull(command);
	}
	
	@Test
	public void givenStartPointAndEndPointOnSameVerticalLine_whenParse_thenReturnCommand() throws CommandParsingException {
		String commandText = "l 1 2 1 4";		
		DrawLineCommandParser parser = new DrawLineCommandParser(this.drawingContext);
		
		DrawLineCommand command = parser.parse(commandText);
		
		assertNotNull(command);
	}
	
	@Test
	public void givenCommandTextIsCorrectAndLowerCase_whenParse_thenReturnCommand() throws CommandParsingException {
		String commandText = "l 1 2 3 2";
		DrawLineCommandParser parser = new DrawLineCommandParser(this.drawingContext);
		
		DrawLineCommand command = parser.parse(commandText);
		
		assertNotNull(command);
	}
	
	@Test
	public void givenCommandTypeIsCustomized_whenParse_thenReturnCommand() throws CommandParsingException {
		String commandType = "LINE";
		String commandText = "LINE 1 2 3 2";
		DrawLineCommandParser parser = new DrawLineCommandParser(commandType, this.drawingContext, new DrawableLineConfig());
		
		DrawLineCommand command = parser.parse(commandText);
		
		assertNotNull(command);
	}

}
