package com.cs.codechallenge.command;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cs.codechallenge.command.DrawRectangleCommand;
import com.cs.codechallenge.drawing.DrawableRectangle;
import com.cs.codechallenge.drawing.DrawingContext;

@ExtendWith(MockitoExtension.class)
public class DrawRectangleCommandTest {

	@Test
	public void givenDrawingContextIsNull_whenConsturctor_thenThrowException() {
		DrawingContext drawingContext = null;
		DrawableRectangle rectangle = mock(DrawableRectangle.class);
		
		assertThrows(IllegalArgumentException.class, () -> new DrawRectangleCommand(drawingContext, rectangle));
	}

	@Test
	public void givenDrawRectangleIsNull_whenConsturctor_thenThrowException() {
		DrawingContext drawingContext = mock(DrawingContext.class);
		DrawableRectangle rectangle = null;
		
		assertThrows(IllegalArgumentException.class, () -> new DrawRectangleCommand(drawingContext, rectangle));
	}

	
	@Test
	public void givenDrawRectangleIsConstructed_whenExecute_thenDrawingContextMethodsShouldBeInvoked() throws Exception {
		DrawingContext drawingContext = mock(DrawingContext.class);
		DrawableRectangle rectangle = mock(DrawableRectangle.class);
		
		DrawRectangleCommand command = new DrawRectangleCommand(drawingContext, rectangle);
		
		command.execute();
		
		verify(drawingContext, times(1)).addDrawableComponent(rectangle);
		verify(drawingContext, times(1)).draw();
	}
}
