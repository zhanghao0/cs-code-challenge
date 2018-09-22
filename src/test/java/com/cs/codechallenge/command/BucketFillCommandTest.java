package com.cs.codechallenge.command;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cs.codechallenge.command.BucketFillCommand;
import com.cs.codechallenge.drawing.BucketFill;
import com.cs.codechallenge.drawing.DrawingContext;
import com.cs.codechallenge.geometry.Point;

@ExtendWith(MockitoExtension.class)
public class BucketFillCommandTest {

	@Test
	public void givenDrawingContextIsNull_whenConsturctor_thenThrowException() {
		DrawingContext drawingContext = null;
		BucketFill bucketFill = new BucketFill(new Point(2, 2), 'o');
		
		assertThrows(IllegalArgumentException.class, () -> new BucketFillCommand(drawingContext, bucketFill));
	}

	@Test
	public void givenBucketFillIsNull_whenConsturctor_thenThrowException() {
		DrawingContext drawingContext = mock(DrawingContext.class);
		BucketFill bucketFill = null;
		
		assertThrows(IllegalArgumentException.class, () -> new BucketFillCommand(drawingContext, bucketFill));
	}

	
	@Test
	public void givenBucketFillIsConstructed_whenExecute_thenDrawingContextMethodsShouldBeInvoked() throws Exception {
		DrawingContext drawingContext = mock(DrawingContext.class);
		BucketFill bucketFill = new BucketFill(new Point(2, 2), 'o');
		
		BucketFillCommand command = new BucketFillCommand(drawingContext, bucketFill);
		
		command.execute();
		
		verify(drawingContext, times(1)).addDrawableComponent(bucketFill);
		verify(drawingContext, times(1)).draw();
	}
}
