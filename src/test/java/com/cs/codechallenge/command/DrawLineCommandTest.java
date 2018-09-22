package com.cs.codechallenge.command;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cs.codechallenge.command.DrawLineCommand;
import com.cs.codechallenge.drawing.DrawableLine;
import com.cs.codechallenge.drawing.DrawingContext;

@ExtendWith(MockitoExtension.class)
public class DrawLineCommandTest {

	@Test
	public void givenDrawingContextIsNull_whenConsturctor_thenThrowException() {
		DrawingContext drawingContext = null;
		DrawableLine line = mock(DrawableLine.class);
		
		assertThrows(IllegalArgumentException.class, () -> new DrawLineCommand(drawingContext, line));
	}

	@Test
	public void givenDrawLineIsNull_whenConsturctor_thenThrowException() {
		DrawingContext drawingContext = mock(DrawingContext.class);
		DrawableLine line = null;
		
		assertThrows(IllegalArgumentException.class, () -> new DrawLineCommand(drawingContext, line));
	}

	
	@Test
	public void givenDrawLineIsConstructed_whenExecute_thenDrawingContextMethodsShouldBeInvoked() throws Exception {
		DrawingContext drawingContext = mock(DrawingContext.class);
		DrawableLine line = mock(DrawableLine.class);
		
		DrawLineCommand command = new DrawLineCommand(drawingContext, line);
		
		command.execute();
		
		verify(drawingContext, times(1)).addDrawableComponent(line);
		verify(drawingContext, times(1)).draw();
	}
}
