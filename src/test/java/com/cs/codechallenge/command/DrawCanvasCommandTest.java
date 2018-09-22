package com.cs.codechallenge.command;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cs.codechallenge.command.DrawCanvasCommand;
import com.cs.codechallenge.drawing.Canvas;
import com.cs.codechallenge.drawing.DrawingContext;

@ExtendWith(MockitoExtension.class)
public class DrawCanvasCommandTest {

	@Test
	public void givenDrawingContextIsNull_whenConsturctor_thenThrowException() {
		DrawingContext drawingContext = null;
		Canvas canvas = mock(Canvas.class);
		
		assertThrows(IllegalArgumentException.class, () -> new DrawCanvasCommand(drawingContext, canvas));
	}

	@Test
	public void givenCanvasIsNull_whenConsturctor_thenThrowException() {
		DrawingContext drawingContext = mock(DrawingContext.class);
		Canvas canvas = null;
		
		assertThrows(IllegalArgumentException.class, () -> new DrawCanvasCommand(drawingContext, canvas));
	}

	
	@Test
	public void givenCanvasIsConstructed_whenExecute_thenDrawingContextMethodsShouldBeInvoked() throws Exception {
		DrawingContext drawingContext = mock(DrawingContext.class);
		Canvas canvas = mock(Canvas.class);
		
		DrawCanvasCommand command = new DrawCanvasCommand(drawingContext, canvas);
		
		command.execute();
		
		verify(drawingContext, times(1)).addCanvas(canvas);
		verify(drawingContext, times(1)).draw();
	}
}
