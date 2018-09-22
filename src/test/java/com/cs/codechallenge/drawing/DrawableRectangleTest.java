package com.cs.codechallenge.drawing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cs.codechallenge.drawing.Canvas;
import com.cs.codechallenge.drawing.DrawableRectangle;
import com.cs.codechallenge.drawing.DrawableRectangleConfig;
import com.cs.codechallenge.geometry.Bounds;
import com.cs.codechallenge.geometry.Dimension;
import com.cs.codechallenge.geometry.Point;

@ExtendWith(MockitoExtension.class)
public class DrawableRectangleTest {
	@Test
	public void givenDrawableRectangleConfigIsNull_whenConsturctor_thenThrowException() {
		DrawableRectangleConfig drawableRectangleConfig = null;
		Point start = new Point(1, 1);
		Point end = new Point(2, 2);
		
		assertThrows(IllegalArgumentException.class, () -> new DrawableRectangle(drawableRectangleConfig, start, end));
	}
	
	@Test
	public void givenStartPointIsNull_whenConsturctor_thenThrowException() {
		DrawableRectangleConfig drawableRectangleConfig = new DrawableRectangleConfig();
		Point start = null;
		Point end = new Point(2, 2);
		
		assertThrows(IllegalArgumentException.class, () -> new DrawableRectangle(drawableRectangleConfig, start, end));
	}
	
	@Test
	public void givenEndPointIsNull_whenConsturctor_thenThrowException() {
		DrawableRectangleConfig drawableRectangleConfig = new DrawableRectangleConfig();
		Point start = new Point(1, 1);
		Point end = null;
		
		assertThrows(IllegalArgumentException.class, () -> new DrawableRectangle(drawableRectangleConfig, start, end));
	}

	@Test
	public void givenStartPointAndEndPointAreTheSame_whenConsturctor_thenThrowException() {
		DrawableRectangleConfig drawableRectangleConfig = new DrawableRectangleConfig();
		Point start = new Point(1, 1);
		Point end = new Point(1, 1);
		
		assertThrows(IllegalArgumentException.class, () -> new DrawableRectangle(drawableRectangleConfig, start, end));
	}

	@Test
	public void givenStartPointAndEndPointAreOnSameHorizonalLine_whenConsturctor_thenThrowException() {
		DrawableRectangleConfig drawableRectangleConfig = new DrawableRectangleConfig();
		Point start = new Point(1, 1);
		Point end = new Point(2, 1);
		
		assertThrows(IllegalArgumentException.class, () -> new DrawableRectangle(drawableRectangleConfig, start, end));
	}

	@Test
	public void givenStartPointAndEndPointAreOnSameVerticalLine_whenConsturctor_thenThrowException() {
		DrawableRectangleConfig drawableRectangleConfig = new DrawableRectangleConfig();
		Point start = new Point(1, 1);
		Point end = new Point(1, 2);
		
		assertThrows(IllegalArgumentException.class, () -> new DrawableRectangle(drawableRectangleConfig, start, end));
	}

	
	@Test
	public void givenADrawableRectangle_whenEquals_thenReturnTrue() {
		DrawableRectangle rectangle = new DrawableRectangle(new DrawableRectangleConfig(), new Point(1, 1), new Point(2, 2));
		
		boolean result = rectangle.equals(rectangle);
		
		assertTrue(result);
	}
	
	@Test
	public void givenADrawableRectangleAndNull_whenEquals_thenReturnFalse() {
		DrawableRectangle rectangle = new DrawableRectangle(new DrawableRectangleConfig(), new Point(1, 1), new Point(2, 2));
		
		boolean result = rectangle.equals(null);
		
		assertFalse(result);
	}
	
	@Test
	public void givenADrawableRectangleAndAPoint_whenEquals_thenReturnFalse() {
		DrawableRectangle rectangle = new DrawableRectangle(new DrawableRectangleConfig(), new Point(1, 1), new Point(2, 2));
		Point point = new Point(1, 1);
		
		boolean result = rectangle.equals(point);
		
		assertFalse(result);
	}
	
	@Test
	public void givenTwoDrawableRectangles_whenEquals_thenReturnFalse() {
		DrawableRectangle rectangle1 = new DrawableRectangle(new DrawableRectangleConfig(), new Point(1, 1), new Point(2, 2));
		DrawableRectangle rectangle2 = new DrawableRectangle(new DrawableRectangleConfig(), new Point(1, 1), new Point(2, 2));
		
		boolean result = rectangle1.equals(rectangle2);
		
		assertFalse(result);
	}
	
	@Test
	public void givenTwoDrawableRectangles_whenHashCode_thenReturnDifferent() {
		DrawableRectangle rectangle1 = new DrawableRectangle(new DrawableRectangleConfig(), new Point(1, 1), new Point(2, 2));
		DrawableRectangle rectangle2 = new DrawableRectangle(new DrawableRectangleConfig(), new Point(1, 1), new Point(2, 2));
		
		boolean result = rectangle1.hashCode() == rectangle2.hashCode();
		
		assertFalse(result);
	}
	
	
	@Test
	public void givenStartAtTheTopLeftOfEnd_whenGetBounds_thenReturnCorrectBounds() {
		DrawableRectangle rectangle = new DrawableRectangle(new DrawableRectangleConfig(), new Point(1, 1), new Point(2, 3));
		Bounds expected = new Bounds(new Point(1, 1), new Dimension(2, 3));
		
		Bounds bounds = rectangle.getBounds();
		
		assertEquals(expected, bounds);
	}
	
	@Test
	public void givenStartAtTheBottomRightOfEnd_whenGetBounds_thenReturnCorrectBounds() {
		DrawableRectangle rectangle = new DrawableRectangle(new DrawableRectangleConfig(), new Point(2, 3), new Point(1, 1));
		Bounds expected = new Bounds(new Point(1, 1), new Dimension(2, 3));
		
		Bounds bounds = rectangle.getBounds();
		
		assertEquals(expected, bounds);
	}
	
	@Test
	public void givenStartAtTheTopRightOfEnd_whenGetBounds_thenReturnCorrectBounds() {
		DrawableRectangle rectangle = new DrawableRectangle(new DrawableRectangleConfig(), new Point(4, 1), new Point(2, 3));
		Bounds expected = new Bounds(new Point(2, 1), new Dimension(3, 3));
		
		Bounds bounds = rectangle.getBounds();
		
		assertEquals(expected, bounds);
	}
	
	@Test
	public void givenStartAtTheBottomLeftOfEnd_whenGetBounds_thenReturnCorrectBounds() {
		DrawableRectangle rectangle = new DrawableRectangle(new DrawableRectangleConfig(), new Point(1, 4), new Point(2, 3));
		Bounds expected = new Bounds(new Point(1, 3), new Dimension(2, 2));
		
		Bounds bounds = rectangle.getBounds();
		
		assertEquals(expected, bounds);
	}
	
	
	@Test
	public void givenStartAtTheTopLeftOfEnd_whenDraw_thenUpdateCorrectly() {
		DrawableRectangle rectangle = new DrawableRectangle(new DrawableRectangleConfig(), new Point(1, 1), new Point(2, 3));
		
		Canvas canvas = mock(Canvas.class);
		doNothing().when(canvas).update(isA(int.class), isA(int.class), isA(char.class));
		
		rectangle.draw(canvas);
		
		verify(canvas, times(6)).update(isA(int.class), isA(int.class), isA(char.class));
	}
	
	@Test
	public void givenStartAtTheBottomRightOfEnd_whenDraw_thenUpdateCorrectly() {
		DrawableRectangle rectangle = new DrawableRectangle(new DrawableRectangleConfig(), new Point(2, 3), new Point(1, 1));
		
		Canvas canvas = mock(Canvas.class);
		doNothing().when(canvas).update(isA(int.class), isA(int.class), isA(char.class));
		
		rectangle.draw(canvas);
		
		verify(canvas, times(6)).update(isA(int.class), isA(int.class), isA(char.class));
	}
	
	@Test
	public void givenStartAtTheTopRightOfEnd_whenDraw_thenUpdateCorrectly() {
		DrawableRectangle rectangle = new DrawableRectangle(new DrawableRectangleConfig(), new Point(4, 1), new Point(2, 3));
		
		Canvas canvas = mock(Canvas.class);
		doNothing().when(canvas).update(isA(int.class), isA(int.class), isA(char.class));
		
		rectangle.draw(canvas);
		
		verify(canvas, times(8)).update(isA(int.class), isA(int.class), isA(char.class));
	}
	
	@Test
	public void givenStartAtTheBottomLeftOfEnd_whenDraw_thenUpdateCorrectly() {
		DrawableRectangle rectangle = new DrawableRectangle(new DrawableRectangleConfig(), new Point(1, 4), new Point(2, 3));
		
		Canvas canvas = mock(Canvas.class);
		doNothing().when(canvas).update(isA(int.class), isA(int.class), isA(char.class));
		
		rectangle.draw(canvas);
		
		verify(canvas, times(4)).update(isA(int.class), isA(int.class), isA(char.class));
	}
}
