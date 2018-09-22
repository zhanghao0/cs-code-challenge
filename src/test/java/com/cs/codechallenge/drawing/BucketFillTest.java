package com.cs.codechallenge.drawing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import com.cs.codechallenge.drawing.BucketFill;
import com.cs.codechallenge.drawing.Canvas;
import com.cs.codechallenge.geometry.Bounds;
import com.cs.codechallenge.geometry.Dimension;
import com.cs.codechallenge.geometry.Point;

@ExtendWith(MockitoExtension.class)
public class BucketFillTest {
	@Test
	public void givenPointIsNull_whenConsturctor_thenThrowException() {
		Point point = null;
		char colorCharacter = ' ';
		
		assertThrows(IllegalArgumentException.class, () -> new BucketFill(point, colorCharacter));
	}
	
	@Test
	public void givenABucketFill_whenEquals_thenReturnTrue() {
		BucketFill bucketFill = new BucketFill(new Point(1, 1), 'o');
		
		boolean result = bucketFill.equals(bucketFill);
		
		assertTrue(result);
	}
	
	@Test
	public void givenTwoBucketFillsHaveDifferentSameId_whenEquals_thenReturnFalse() {
		BucketFill bucketFill1 = new BucketFill(new Point(1, 1), 'o');
		BucketFill bucketFill2 = new BucketFill(new Point(1, 1), 'o');
		
		boolean result = bucketFill1.equals(bucketFill2);
		
		assertFalse(result);
	}
	
	@Test
	public void givenABucketFillAndNull_whenEquals_thenReturnFalse() {
		BucketFill bucketFill = new BucketFill(new Point(1, 1), 'o');
		
		boolean result = bucketFill.equals(null);
		
		assertFalse(result);
	}
	
	@Test
	public void givenABucketFillAndAPoint_whenEquals_thenReturnFalse() {
		BucketFill bucketFill = new BucketFill(new Point(1, 1), 'o');
		Point point = new Point(1, 1);
		
		boolean result = bucketFill.equals(point);
		
		assertFalse(result);
	}
		
	@Test
	public void givenTwoBucketFills_whenHashCode_thenReturnFalse() {
		BucketFill bucketFill1 = new BucketFill(new Point(1, 1), 'o');
		BucketFill bucketFill2 = new BucketFill(new Point(1, 1), 'o');
		
		boolean result = bucketFill1.hashCode() == bucketFill2.hashCode();
		
		assertFalse(result);
	}
	

	@Test
	public void givenBucketFillAtPoint_whenGetBounds_thenReturnBoundsAtSamePoint() {
		BucketFill bucketFill = new BucketFill(new Point(1, 1), 'o');
		Bounds expected = new Bounds(new Point(1, 1), new Dimension(0, 0));
		
		Bounds bounds = bucketFill.getBounds();
		
		assertEquals(expected, bounds);
	}

	@Test
	public void givenBucketFillInARectangle3By3_whenDraw_thenUpdateAreaInsideRectangle() {
		BucketFill bucketFill = new BucketFill(new Point(2, 2), 'o');
		
		Canvas canvas = mock(Canvas.class);
		when(canvas.get(isA(int.class), isA(int.class)))
		 .thenAnswer(new Answer<Character>() {
			@Override
			public Character answer(InvocationOnMock invocation) throws Throwable {
				Object[] args = invocation.getArguments();
				
				if (args[0].equals(2) && args[1].equals(2)) {
					return ' ';
				} else {
					return 'x';
				}
			}
		});
		doNothing().when(canvas).update(isA(int.class), isA(int.class), isA(char.class));
		when(canvas.getBounds()).thenReturn(new Bounds(new Point(1, 1), new Dimension(3, 3)));
		
		bucketFill.draw(canvas);
		
		verify(canvas, times(1)).update(isA(int.class), isA(int.class), isA(char.class));
	}
}
