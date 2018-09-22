package com.cs.codechallenge.drawing;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import com.cs.codechallenge.drawing.Canvas;
import com.cs.codechallenge.drawing.DrawableComponent;
import com.cs.codechallenge.drawing.InvalidDrawableComponentException;
import com.cs.codechallenge.drawing.SimpleDrawingContext;

public class SimpleDrawingContextTest {

	@Test
	public void givenCanvasIsNull_whenAddCanvas_thenThrowException() {
		Canvas canvas = null;		
		SimpleDrawingContext context = new SimpleDrawingContext();
		
		assertThrows(IllegalArgumentException.class, () -> context.addCanvas(canvas));
	}

	@Test
	public void givenACanvasHasBeenAdded_whenAddCanvas_thenThrowException() throws Exception {
		SimpleDrawingContext context = new SimpleDrawingContext();
		Canvas canvas1 = mock(Canvas.class);
		Canvas canvas2 = mock(Canvas.class);
		
		context.addCanvas(canvas1);
		
		assertThrows(InvalidDrawableComponentException.class, () -> context.addCanvas(canvas2));
	}

	
	@Test
	public void givenComponentIsNull_whenAddDrawableComponent_thenThrowException() {
		DrawableComponent component = null;		
		SimpleDrawingContext context = new SimpleDrawingContext();
		
		assertThrows(IllegalArgumentException.class, () -> context.addDrawableComponent(component));
	}

	@Test
	public void givenCanvasHasNotBeenAdded_whenAddDrawableComponent_thenThrowException() throws Exception {
		SimpleDrawingContext context = new SimpleDrawingContext();
		DrawableComponent component = mock(DrawableComponent.class);
				
		assertThrows(InvalidDrawableComponentException.class, () -> context.addDrawableComponent(component));
	}

	@Test
	public void givenCanvasHasBeenAdded_whenAddDrawableComponent_thenComponentAdded() throws Exception {
		SimpleDrawingContext context = new SimpleDrawingContext();
		Canvas canvas = mock(Canvas.class);
		DrawableComponent component = mock(DrawableComponent.class);
		
		context.addCanvas(canvas);
		
		context.addDrawableComponent(component);
				
		verify(canvas, times(1)).addDrawableComponent(isA(DrawableComponent.class));
	}
	

	@Test
	public void givenCanvasHasNotBeenAdded_whenDraw_thenNotDrawn() throws Exception {
		SimpleDrawingContext context = new SimpleDrawingContext();
		Canvas canvas = mock(Canvas.class);
		
		context.draw();
				
		verify(canvas, never()).draw();
	}

	@Test
	public void givenCanvasHasBeenAdded_whenDraw_thenCanvasIsDrawn() throws Exception {
		SimpleDrawingContext context = new SimpleDrawingContext();
		Canvas canvas = mock(Canvas.class);
		
		context.addCanvas(canvas);
		
		context.draw();
				
		verify(canvas, times(1)).draw();
	}
}
