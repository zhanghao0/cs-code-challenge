package com.cs.codechallenge.drawing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cs.codechallenge.drawing.Canvas;
import com.cs.codechallenge.drawing.DrawableLine;
import com.cs.codechallenge.drawing.DrawableLineConfig;
import com.cs.codechallenge.geometry.Bounds;
import com.cs.codechallenge.geometry.Dimension;
import com.cs.codechallenge.geometry.Point;

@ExtendWith(MockitoExtension.class)
public class DrawableLineTest {
	@Test
	public void givenDrawableLineConfigIsNull_whenConsturctor_thenThrowException() {
		DrawableLineConfig drawableLineConfig = null;
		Point start = new Point(1, 1);
		Point end = new Point(2, 2);
		
		assertThrows(IllegalArgumentException.class, () -> new DrawableLine(drawableLineConfig, start, end));
	}
	
	@Test
	public void givenStartPointIsNull_whenConsturctor_thenThrowException() {
		DrawableLineConfig drawableLineConfig = new DrawableLineConfig();
		Point start = null;
		Point end = new Point(2, 2);
		
		assertThrows(IllegalArgumentException.class, () -> new DrawableLine(drawableLineConfig, start, end));
	}
	
	@Test
	public void givenEndPointIsNull_whenConsturctor_thenThrowException() {
		DrawableLineConfig drawableLineConfig = new DrawableLineConfig();
		Point start = new Point(1, 1);
		Point end = null;
		
		assertThrows(IllegalArgumentException.class, () -> new DrawableLine(drawableLineConfig, start, end));
	}

	@Test
	public void givenStartPointAndEndPointAreTheSame_whenConsturctor_thenThrowException() {
		DrawableLineConfig drawableLineConfig = new DrawableLineConfig();
		Point start = new Point(1, 1);
		Point end = new Point(1, 1);
		
		assertThrows(IllegalArgumentException.class, () -> new DrawableLine(drawableLineConfig, start, end));
	}

	@Test
	public void givenStartPointAndEndPointAreNotOnSameHorizonalOrVerticalLine_whenConsturctor_thenThrowException() {
		DrawableLineConfig drawableLineConfig = new DrawableLineConfig();
		Point start = new Point(1, 1);
		Point end = new Point(2, 2);
		
		assertThrows(IllegalArgumentException.class, () -> new DrawableLine(drawableLineConfig, start, end));
	}

	
	@Test
	public void givenADrawableLine_whenEquals_thenReturnTrue() {
		DrawableLine line = new DrawableLine(new DrawableLineConfig(), new Point(1, 1), new Point(1, 2));
		
		boolean result = line.equals(line);
		
		assertTrue(result);
	}
	
	@Test
	public void givenADrawableLineAndNull_whenEquals_thenReturnFalse() {
		DrawableLine line = new DrawableLine(new DrawableLineConfig(), new Point(1, 1), new Point(1, 2));
		
		boolean result = line.equals(null);
		
		assertFalse(result);
	}
	
	@Test
	public void givenADrawableLineAndAPoint_whenEquals_thenReturnFalse() {
		DrawableLine line = new DrawableLine(new DrawableLineConfig(), new Point(1, 1), new Point(1, 2));
		Point point = new Point(1, 1);
		
		boolean result = line.equals(point);
		
		assertFalse(result);
	}
	
	@Test
	public void givenTwoDrawableLines_whenEquals_thenReturnFalse() {
		DrawableLine line1 = new DrawableLine(new DrawableLineConfig(), new Point(1, 1), new Point(1, 2));
		DrawableLine line2 = new DrawableLine(new DrawableLineConfig(), new Point(1, 1), new Point(1, 2));
		
		boolean result = line1.equals(line2);
		
		assertFalse(result);
	}
	
	@Test
	public void givenTwoDrawableLines_whenHashCode_thenReturnDifferent() {
		DrawableLine line1 = new DrawableLine(new DrawableLineConfig(), new Point(1, 1), new Point(1, 2));
		DrawableLine line2 = new DrawableLine(new DrawableLineConfig(), new Point(1, 1), new Point(1, 2));
		
		boolean result = line1.hashCode() == line2.hashCode();
		
		assertFalse(result);
	}
	
	
	@Test
	public void givenStartAtTheLeftOfEnd_whenGetBounds_thenReturnCorrectBounds() {
		DrawableLine line = new DrawableLine(new DrawableLineConfig(), new Point(1, 1), new Point(2, 1));
		Bounds expected = new Bounds(new Point(1, 1), new Dimension(2, 1));
		
		Bounds bounds = line.getBounds();
		
		assertEquals(expected, bounds);
	}
	
	@Test
	public void givenStartAtTheRightOfEnd_whenGetBounds_thenReturnCorrectBounds() {
		DrawableLine line = new DrawableLine(new DrawableLineConfig(), new Point(2, 1), new Point(1, 1));
		Bounds expected = new Bounds(new Point(1, 1), new Dimension(2, 1));
		
		Bounds bounds = line.getBounds();
		
		assertEquals(expected, bounds);
	}
	@Test
	public void givenStartAboveEnd_whenGetBounds_thenReturnCorrectBounds() {
		DrawableLine line = new DrawableLine(new DrawableLineConfig(), new Point(1, 1), new Point(1, 2));
		Bounds expected = new Bounds(new Point(1, 1), new Dimension(1, 2));
		
		Bounds bounds = line.getBounds();
		
		assertEquals(expected, bounds);
	}
	
	@Test
	public void givenStartBelowEnd_whenGetBounds_thenReturnCorrectBounds() {
		DrawableLine line = new DrawableLine(new DrawableLineConfig(), new Point(1, 2), new Point(1, 1));
		Bounds expected = new Bounds(new Point(1, 1), new Dimension(1, 2));
		
		Bounds bounds = line.getBounds();
		
		assertEquals(expected, bounds);
	}
	
	
	@Test
	public void givenVerticalLineWithStartAtTheTop_whenDraw_thenUpdateCorrectly() {
		DrawableLine line = new DrawableLine(new DrawableLineConfig(), new Point(2, 1), new Point(2, 5));
		
		Canvas canvas = mock(Canvas.class);
		doNothing().when(canvas).update(isA(int.class), isA(int.class), isA(char.class));
		
		line.draw(canvas);
		
		verify(canvas, times(5)).update(isA(int.class), isA(int.class), isA(char.class));
	}
	
	@Test
	public void givenVerticalLineWithStartAtTheBottom_whenDraw_thenUpdateCorrectly() {
		DrawableLine line = new DrawableLine(new DrawableLineConfig(), new Point(2, 5), new Point(2, 1));
		
		Canvas canvas = mock(Canvas.class);
		doNothing().when(canvas).update(isA(int.class), isA(int.class), isA(char.class));
		
		line.draw(canvas);
		
		verify(canvas, times(5)).update(isA(int.class), isA(int.class), isA(char.class));
	}
	
	@Test
	public void givenHorizontalLineWithStartAtTheLeft_whenDraw_thenUpdateCorrectly() {
		DrawableLine line = new DrawableLine(new DrawableLineConfig(), new Point(3, 2), new Point(8, 2));
		
		Canvas canvas = mock(Canvas.class);
		doNothing().when(canvas).update(isA(int.class), isA(int.class), isA(char.class));
		
		line.draw(canvas);
		
		verify(canvas, times(6)).update(isA(int.class), isA(int.class), isA(char.class));
	}
	
	@Test
	public void givenHorizontalLineWithStartAtTheRight_whenDraw_thenUpdateCorrectly() {
		DrawableLine line = new DrawableLine(new DrawableLineConfig(), new Point(8, 2), new Point(3, 2));
		
		Canvas canvas = mock(Canvas.class);
		doNothing().when(canvas).update(isA(int.class), isA(int.class), isA(char.class));
		
		line.draw(canvas);
		
		verify(canvas, times(6)).update(isA(int.class), isA(int.class), isA(char.class));
	}
}
