package com.cs.codechallenge.geometry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.cs.codechallenge.geometry.Dimension;
import com.cs.codechallenge.geometry.Point;

public class PointTest {
	@Test
	public void givenAPoint_whenGetX_thenReturnCorrectX() {
		Point point = new Point(1, 2);
		
		int x = point.getX();
		
		assertEquals(1, x);
	}
	
	@Test
	public void givenAPoint_whenGetY_thenReturnCorrectY() {
		Point point = new Point(1, 2);
		
		int y = point.getY();
		
		assertEquals(2, y);
	}
	
	@Test
	public void givenTwoPointsHaveSameXAndY_whenIsTheSame_thenReturnTrue() {
		Point point1 = new Point(1, 1);
		Point point2 = new Point(1, 1);
		
		boolean result = point1.isTheSame(point2);
		
		assertTrue(result);
	}
	
	@Test
	public void givenTwoPointsHaveDifferentX_whenIsTheSame_thenReturnFalse() {
		Point point1 = new Point(1, 1);
		Point point2 = new Point(2, 1);
		
		boolean result = point1.isTheSame(point2);
		
		assertFalse(result);
	}
	
	@Test
	public void givenTwoPointsHaveDifferentY_whenIsTheSame_thenReturnFalse() {
		Point point1 = new Point(1, 1);
		Point point2 = new Point(1, 2);
		
		boolean result = point1.isTheSame(point2);
		
		assertFalse(result);
	}
	
	
	@Test
	public void givenTwoPointsHaveSameXAndY_whenEquals_thenReturnTrue() {
		Point point1 = new Point(1, 1);
		Point point2 = new Point(1, 1);
		
		boolean result = point1.equals(point2);
		
		assertTrue(result);
	}
	
	@Test
	public void givenTwoPointsHaveDifferentX_whenEquals_thenReturnFalse() {
		Point point1 = new Point(1, 1);
		Point point2 = new Point(2, 1);
		
		boolean result = point1.equals(point2);
		
		assertFalse(result);
	}
	
	@Test
	public void givenTwoPointsHaveDifferentY_whenEquals_thenReturnFalse() {
		Point point1 = new Point(1, 1);
		Point point2 = new Point(1, 2);
		
		boolean result = point1.equals(point2);
		
		assertFalse(result);
	}
	
	@Test
	public void givenAPoint_whenEquals_thenReturnTrue() {
		Point point = new Point(1, 1);
		
		boolean result = point.equals(point);
		
		assertTrue(result);
	}
	
	@Test
	public void givenAPointAndNull_whenEquals_thenReturnFalse() {
		Point point = new Point(1, 1);
		
		boolean result = point.equals(null);
		
		assertFalse(result);
	}
	
	@Test
	public void givenAPointAndADimension_whenEquals_thenReturnFalse() {
		Point point = new Point(1, 1);
		Dimension dimension = new Dimension(1, 1);
		
		boolean result = point.equals(dimension);
		
		assertFalse(result);
	}
	
	@Test
	public void givenTwoPointsHaveSameXAndY_whenHashCode_thenReturnSameResult() {
		Point point1 = new Point(1, 1);
		Point point2 = new Point(1, 1);
		
		boolean result = point1.hashCode() == point2.hashCode();
		
		assertTrue(result);
	}
	
	@Test
	public void givenTwoPointsHaveDifferentX_whenHashCode_thenReturnDifferentResult() {
		Point point1 = new Point(1, 1);
		Point point2 = new Point(2, 1);
		
		boolean result = point1.hashCode() == point2.hashCode();
		
		assertFalse(result);
	}
	
	@Test
	public void givenTwoPointsHaveDifferentY_whenHashCode_thenReturnDifferentResult() {
		Point point1 = new Point(1, 1);
		Point point2 = new Point(1, 2);
		
		boolean result = point1.hashCode() == point2.hashCode();
		
		assertFalse(result);
	}
	

	
	@Test
	public void givenTwoPointsHaveSameX_whenIsOnTheSameVerticalLine_thenReturnTrue() {
		Point point1 = new Point(2, 1);
		Point point2 = new Point(2, 2);
		
		boolean result = point1.isOnTheSameVerticalLine(point2);
		
		assertTrue(result);
	}
	
	@Test
	public void givenTwoPointsHaveDifferentX_whenIsOnTheSameVerticalLine_thenReturnFalse() {
		Point point1 = new Point(1, 1);
		Point point2 = new Point(2, 1);
		
		boolean result = point1.isOnTheSameVerticalLine(point2);
		
		assertFalse(result);
	}
	

	@Test
	public void givenTwoPointsHaveSameY_whenIsOnTheSameHorizontalLine_thenReturnTrue() {
		Point point1 = new Point(1, 2);
		Point point2 = new Point(2, 2);
		
		boolean result = point1.isOnTheSameHorizontalLine(point2);
		
		assertTrue(result);
	}
	
	@Test
	public void givenTwoPointsHaveDifferentY_whenIsOnTheSameHorizontalLine_thenReturnFalse() {
		Point point1 = new Point(1, 2);
		Point point2 = new Point(1, 3);
		
		boolean result = point1.isOnTheSameHorizontalLine(point2);
		
		assertFalse(result);
	}
}
