package com.cs.codechallenge.geometry;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.cs.codechallenge.geometry.Bounds;
import com.cs.codechallenge.geometry.Dimension;
import com.cs.codechallenge.geometry.Point;

public class BoundsTest {
	
	@Test
	public void givenPointIsNull_whenConsturctor_thenThrowException() {
		Point point = null;
		Dimension dimension = new Dimension(1, 1);
				
		assertThrows(IllegalArgumentException.class, () -> new Bounds(point, dimension));
	}
	
	@Test
	public void givenDimensioIsNull_whenConsturctor_thenThrowException() {
		Point point = new Point(1, 1);
		Dimension dimension = null;
				
		assertThrows(IllegalArgumentException.class, () -> new Bounds(point, dimension));
	}
	
	
	@Test
	public void givenTwoBoundssHaveSamePointAndDimension_whenEquals_thenReturnTrue() {
		Bounds bounds1 = new Bounds(new Point(1, 1), new Dimension(2, 2));
		Bounds bounds2 = new Bounds(new Point(1, 1), new Dimension(2, 2));
		
		boolean result = bounds1.equals(bounds2);
		
		assertTrue(result);
	}
	
	@Test
	public void givenTwoBoundssHaveDifferentPoint_whenEquals_thenReturnFalse() {
		Bounds bounds1 = new Bounds(new Point(1, 1), new Dimension(2, 2));
		Bounds bounds2 = new Bounds(new Point(1, 2), new Dimension(2, 2));
		
		boolean result = bounds1.equals(bounds2);
		
		assertFalse(result);
	}
	
	@Test
	public void givenTwoBoundssHaveDifferentDimension_whenEquals_thenReturnFalse() {
		Bounds bounds1 = new Bounds(new Point(1, 1), new Dimension(2, 2));
		Bounds bounds2 = new Bounds(new Point(1, 1), new Dimension(1, 2));
		
		boolean result = bounds1.equals(bounds2);
		
		assertFalse(result);
	}
	
	@Test
	public void givenABounds_whenEquals_thenReturnTrue() {
		Bounds bounds = new Bounds(new Point(1, 1), new Dimension(2, 2));
		
		boolean result = bounds.equals(bounds);
		
		assertTrue(result);
	}
	
	@Test
	public void givenABoundsAndNull_whenEquals_thenReturnFalse() {
		Bounds bounds = new Bounds(new Point(1, 1), new Dimension(2, 2));
		
		boolean result = bounds.equals(null);
		
		assertFalse(result);
	}
	
	@Test
	public void givenABoundsAndAPoint_whenEquals_thenReturnFalse() {
		Bounds bounds = new Bounds(new Point(1, 1), new Dimension(2, 2));
		Point point = new Point(1, 1);
		
		boolean result = bounds.equals(point);
		
		assertFalse(result);
	}
	
	
	@Test
	public void givenTwoBoundssHaveSamePointAndDimension_whenHashCode_thenReturnSameResult() {
		Bounds bounds1 = new Bounds(new Point(1, 1), new Dimension(2, 2));
		Bounds bounds2 = new Bounds(new Point(1, 1), new Dimension(2, 2));
		
		boolean result = bounds1.hashCode() == bounds2.hashCode();
		
		assertTrue(result);
	}
	
	@Test
	public void givenTwoBoundssHaveDifferentPoint_whenHashCode_thenReturnDifferentResult() {
		Bounds bounds1 = new Bounds(new Point(1, 1), new Dimension(2, 2));
		Bounds bounds2 = new Bounds(new Point(1, 2), new Dimension(2, 2));
		
		boolean result = bounds1.hashCode() == bounds2.hashCode();
		
		assertFalse(result);
	}
	
	@Test
	public void givenTwoBoundssHaveDifferentDimension_whenHashCode_thenReturnDifferentResult() {
		Bounds bounds1 = new Bounds(new Point(1, 1), new Dimension(2, 2));
		Bounds bounds2 = new Bounds(new Point(1, 1), new Dimension(1, 2));
		
		boolean result = bounds1.hashCode() == bounds2.hashCode();
		
		assertFalse(result);
	}
	
	
	@Test
	public void given1stBoundsAtTheRightOf2ndBounds_whenContainsBounds_thenReturnFalse() {
		Bounds bounds1 = new Bounds(new Point(2, 1), new Dimension(2, 2));
		Bounds bounds2 = new Bounds(new Point(1, 1), new Dimension(2, 2));
		
		boolean result = bounds1.contains(bounds2);
		
		assertFalse(result);
	}
	
	@Test
	public void given1stBoundsBelow2ndBounds_whenContainsBounds_thenReturnFalse() {
		Bounds bounds1 = new Bounds(new Point(1, 2), new Dimension(2, 2));
		Bounds bounds2 = new Bounds(new Point(1, 1), new Dimension(2, 2));
		
		boolean result = bounds1.contains(bounds2);
		
		assertFalse(result);
	}
	
	@Test
	public void given1stBoundsHasSmallerWidthThan2ndBounds_whenContainsBounds_thenReturnFalse() {
		Bounds bounds1 = new Bounds(new Point(1, 1), new Dimension(1, 2));
		Bounds bounds2 = new Bounds(new Point(1, 1), new Dimension(2, 2));
		
		boolean result = bounds1.contains(bounds2);
		
		assertFalse(result);
	}
	
	@Test
	public void given1stBoundsHasSmallerHeightThan2ndBounds_whenContainsBounds_thenReturnFalse() {
		Bounds bounds1 = new Bounds(new Point(1, 1), new Dimension(2, 1));
		Bounds bounds2 = new Bounds(new Point(1, 1), new Dimension(2, 2));
		
		boolean result = bounds1.contains(bounds2);
		
		assertFalse(result);
	}
	
	@Test
	public void given1stBoundsContains2ndBounds_whenContainsBounds_thenReturnTrue() {
		Bounds bounds1 = new Bounds(new Point(1, 1), new Dimension(2, 2));
		Bounds bounds2 = new Bounds(new Point(1, 1), new Dimension(2, 2));
		
		boolean result = bounds1.contains(bounds2);
		
		assertTrue(result);
	}

	
	@Test
	public void givenPointAtTheLeftOfBounds_whenContainsBounds_thenReturnFalse() {
		Point point = new Point(0, 1);
		Bounds bounds = new Bounds(new Point(1, 1), new Dimension(2, 2));
		
		boolean result = bounds.contains(point.getX(), point.getY());
		
		assertFalse(result);
	}
	
	@Test
	public void givenPointAtTheRightOfBounds_whenContainsBounds_thenReturnFalse() {
		Point point = new Point(4, 1);
		Bounds bounds = new Bounds(new Point(1, 1), new Dimension(2, 2));
		
		boolean result = bounds.contains(point.getX(), point.getY());
		
		assertFalse(result);
	}
	
	@Test
	public void givenPointAboveBounds_whenContainsBounds_thenReturnFalse() {
		Point point = new Point(1, 0);
		Bounds bounds = new Bounds(new Point(1, 1), new Dimension(2, 2));
		
		boolean result = bounds.contains(point.getX(), point.getY());
		
		assertFalse(result);
	}
	
	@Test
	public void givenPointBelowBounds_whenContainsBounds_thenReturnFalse() {
		Point point = new Point(1, 4);
		Bounds bounds = new Bounds(new Point(1, 1), new Dimension(2, 2));
		
		boolean result = bounds.contains(point.getX(), point.getY());
		
		assertFalse(result);
	}
	
	@Test
	public void givenPointInsideBounds_whenContainsBounds_thenReturnTrue() {
		Point point = new Point(1, 1);
		Bounds bounds = new Bounds(new Point(1, 1), new Dimension(2, 2));
		
		boolean result = bounds.contains(point.getX(), point.getY());
		
		assertTrue(result);
	}
}
