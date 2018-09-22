package com.cs.codechallenge.geometry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.cs.codechallenge.geometry.Dimension;
import com.cs.codechallenge.geometry.Point;

public class DimensionTest {
	
	@Test
	public void givenNegativeWidth_whenConsturctor_thenThrowException() {
		int width = -1;
				
		assertThrows(IllegalArgumentException.class, () -> new Dimension(width, 0));
	}
	
	@Test
	public void givenNegativeHeight_whenConsturctor_thenThrowException() {
		int height = -1;
				
		assertThrows(IllegalArgumentException.class, () -> new Dimension(0, height));
	}
	
	@Test
	public void givenADimension_whenGetWidth_thenReturnCorrectWidth() {
		Dimension dimension = new Dimension(1, 2);
		
		int width = dimension.getWidth();
		
		assertEquals(1, width);
	}
	
	@Test
	public void givenADimension_whenGetHeight_thenReturnCorrectHeight() {
		Dimension dimension = new Dimension(1, 2);
		
		int height = dimension.getHeight();
		
		assertEquals(2, height);
	}
	
	
	@Test
	public void givenTwoDimensionsHaveSameWidthAndHeight_whenEquals_thenReturnTrue() {
		Dimension dimension1 = new Dimension(1, 1);
		Dimension dimension2 = new Dimension(1, 1);
		
		boolean result = dimension1.equals(dimension2);
		
		assertTrue(result);
	}
	
	@Test
	public void givenTwoDimensionsHaveDifferentWidth_whenEquals_thenReturnFalse() {
		Dimension dimension1 = new Dimension(1, 1);
		Dimension dimension2 = new Dimension(2, 1);
		
		boolean result = dimension1.equals(dimension2);
		
		assertFalse(result);
	}
	
	@Test
	public void givenTwoDimensionsHaveDifferentHeight_whenEquals_thenReturnFalse() {
		Dimension dimension1 = new Dimension(1, 1);
		Dimension dimension2 = new Dimension(1, 2);
		
		boolean result = dimension1.equals(dimension2);
		
		assertFalse(result);
	}
	
	@Test
	public void givenADimension_whenEquals_thenReturnTrue() {
		Dimension dimension = new Dimension(1, 1);
		
		boolean result = dimension.equals(dimension);
		
		assertTrue(result);
	}
	
	@Test
	public void givenADimensionAndNull_whenEquals_thenReturnFalse() {
		Dimension dimension = new Dimension(1, 1);
		
		boolean result = dimension.equals(null);
		
		assertFalse(result);
	}
	
	@Test
	public void givenADimensionAndAPoint_whenEquals_thenReturnFalse() {
		Dimension dimension = new Dimension(1, 1);
		Point point = new Point(1, 1);
		
		boolean result = dimension.equals(point);
		
		assertFalse(result);
	}
	
	@Test
	public void givenTwoDimensionsHaveSameWidthAndHeight_whenHashCode_thenReturnSameResult() {
		Dimension dimension1 = new Dimension(1, 1);
		Dimension dimension2 = new Dimension(1, 1);
		
		boolean result = dimension1.hashCode() == dimension2.hashCode();
		
		assertTrue(result);
	}
	
	@Test
	public void givenTwoDimensionsHaveDifferentWidth_whenHashCode_thenReturnDifferentResult() {
		Dimension dimension1 = new Dimension(1, 1);
		Dimension dimension2 = new Dimension(2, 1);
		
		boolean result = dimension1.hashCode() == dimension2.hashCode();
		
		assertFalse(result);
	}
	
	@Test
	public void givenTwoDimensionsHaveDifferentHeight_whenHashCode_thenReturnDifferentResult() {
		Dimension dimension1 = new Dimension(1, 1);
		Dimension dimension2 = new Dimension(1, 2);
		
		boolean result = dimension1.hashCode() == dimension2.hashCode();
		
		assertFalse(result);
	}
	
}
