package com.cs.codechallenge.drawing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cs.codechallenge.drawing.BucketFill;
import com.cs.codechallenge.drawing.Canvas;
import com.cs.codechallenge.drawing.CanvasConfig;
import com.cs.codechallenge.drawing.DrawableComponent;
import com.cs.codechallenge.drawing.DrawableLine;
import com.cs.codechallenge.drawing.DrawableLineConfig;
import com.cs.codechallenge.drawing.DrawableRectangle;
import com.cs.codechallenge.drawing.DrawableRectangleConfig;
import com.cs.codechallenge.drawing.GraphicsDevice;
import com.cs.codechallenge.drawing.InvalidDrawableComponentException;
import com.cs.codechallenge.geometry.Bounds;
import com.cs.codechallenge.geometry.Dimension;
import com.cs.codechallenge.geometry.Point;

@ExtendWith(MockitoExtension.class)
public class CanvasTest {
	
	@Test
	public void givenDrawCanvasConfigIsNull_whenConsturctor_thenThrowException() {
		CanvasConfig drawCanvasConfig = null;
		Dimension dimension = new Dimension(0, 0);
				
		assertThrows(IllegalArgumentException.class, () -> new Canvas(drawCanvasConfig, dimension));
	}
	
	@Test
	public void givenDimensionIsNull_whenConsturctor_thenThrowException() {
		CanvasConfig drawCanvasConfig = new CanvasConfig();
		Dimension dimension = null;
		
		assertThrows(IllegalArgumentException.class, () -> new Canvas(drawCanvasConfig, dimension));
	}
	
	@Test
	public void givenWidthIs0_whenConsturctor_thenThrowException() {
		CanvasConfig drawCanvasConfig = new CanvasConfig();
		Dimension dimension = new Dimension(0, 1);
		
		assertThrows(IllegalArgumentException.class, () -> new Canvas(drawCanvasConfig, dimension));
	}
	
	@Test
	public void givenWidthIs51_whenConsturctor_thenThrowException() {
		CanvasConfig drawCanvasConfig = new CanvasConfig();
		Dimension dimension = new Dimension(51, 1);
		
		assertThrows(IllegalArgumentException.class, () -> new Canvas(drawCanvasConfig, dimension));
	}
	
	@Test
	public void givenHeightIs0_whenConsturctor_thenThrowException() {
		CanvasConfig drawCanvasConfig = new CanvasConfig();
		Dimension dimension = new Dimension(1, 0);
		
		assertThrows(IllegalArgumentException.class, () -> new Canvas(drawCanvasConfig, dimension));
	}
	
	@Test
	public void givenHeightIs51_whenConsturctor_thenThrowException() {
		CanvasConfig drawCanvasConfig = new CanvasConfig();
		Dimension dimension = new Dimension(1, 51);
		
		assertThrows(IllegalArgumentException.class, () -> new Canvas(drawCanvasConfig, dimension));
	}

	
	@Test
	public void givenComponentIsNull_whenAddDrawableComponent_thenThrowException() {
		CanvasConfig drawCanvasConfig = new CanvasConfig();
		Dimension dimension = new Dimension(1, 1);
		Canvas canvas = new Canvas(drawCanvasConfig, dimension);
		DrawableComponent component = null;
		
		assertThrows(IllegalArgumentException.class, () -> canvas.addDrawableComponent(component));
	}

	@Test
	public void givenComponentIsOutsideCanvas_whenAddDrawableComponent_thenThrowException() {
		CanvasConfig drawCanvasConfig = new CanvasConfig();
		Dimension dimension = new Dimension(5, 5);
		Canvas canvas = new Canvas(drawCanvasConfig, dimension);
		DrawableComponent component = new DrawableLine(new DrawableLineConfig(), new Point(0, 0), new Point(0, 5));
		
		assertThrows(InvalidDrawableComponentException.class, () -> canvas.addDrawableComponent(component));
	}
	
	@Test
	public void givenComponentAddedTwice_whenAddDrawableComponent_then1ComponentIsAdded() throws Exception {
		CanvasConfig drawCanvasConfig = new CanvasConfig();
		Dimension dimension = new Dimension(1, 1);
		Canvas canvas = new Canvas(drawCanvasConfig, dimension);
		
		BucketFill component = new BucketFill(new Point(1, 1), 'o');
		
		canvas.addDrawableComponent(component);
		
		assertEquals(1, canvas.getComponentCount());
		
		canvas.addDrawableComponent(component);
		
		assertEquals(1, canvas.getComponentCount());
	}
	
	@Test
	public void given2ComponentAreAdded_whenGetComponentCount_thenComponentCountShouldBe2() throws Exception {
		CanvasConfig drawCanvasConfig = new CanvasConfig();
		Dimension dimension = new Dimension(5, 5);
		Canvas canvas = new Canvas(drawCanvasConfig, dimension);
		
		BucketFill component1 = new BucketFill(new Point(1, 1), 'o');
		BucketFill component2 = new BucketFill(new Point(2, 2), 'o');
		
		canvas.addDrawableComponent(component1);
		canvas.addDrawableComponent(component2);
		
		assertEquals(2, canvas.getComponentCount());
	}

	
	@Test
	public void givenUpdate_whenGet_thenSameCharacterIsReturned() throws Exception {
		CanvasConfig drawCanvasConfig = new CanvasConfig();
		Dimension dimension = new Dimension(5, 5);
		Canvas canvas = new Canvas(drawCanvasConfig, dimension);
		char ch = 'x';
		
		canvas.update(1, 1, ch);
		
		assertEquals(ch, canvas.get(1, 1));
	}

	@Test
	public void givenRowOutOfBound_whenUpdate_thenThrowException() {
		CanvasConfig drawCanvasConfig = new CanvasConfig();
		Dimension dimension = new Dimension(5, 5);
		Canvas canvas = new Canvas(drawCanvasConfig, dimension);
		int row = 10;
		
		assertThrows(ArrayIndexOutOfBoundsException.class, () -> canvas.update(row, 1, 'x'));
	}

	@Test
	public void givenColumnOutOfBound_whenUpdate_thenThrowException() {
		CanvasConfig drawCanvasConfig = new CanvasConfig();
		Dimension dimension = new Dimension(5, 5);
		Canvas canvas = new Canvas(drawCanvasConfig, dimension);
		int column = 10;
		
		assertThrows(ArrayIndexOutOfBoundsException.class, () -> canvas.update(1, column, 'x'));
	}
	
	
	@Test
	public void givenCanvasDimensionIs5By5_whenGetBounds_thenReturn5By5StartFromPoint11() throws Exception {
		CanvasConfig drawCanvasConfig = new CanvasConfig();
		Dimension dimension = new Dimension(5, 5);
		Canvas canvas = new Canvas(drawCanvasConfig, dimension);
		
		Bounds bounds = canvas.getBounds();
		
		assertEquals(new Bounds(new Point(1, 1), new Dimension(5, 5)), bounds);
	}
	
	
	@Test
	public void givenCanvasDimensionIs1By1_whenDraw_thenCellsShouldBeUpdatedCorrectly() throws Exception {
		CanvasConfig drawCanvasConfig = new CanvasConfig();
		Dimension dimension = new Dimension(1, 1);
		Canvas canvas = new Canvas(drawCanvasConfig, dimension);
		
		List<?> expected = Arrays.asList(
			'-', '-', '-', 
			'|', ' ', '|', 
			'-', '-', '-'
		);

		drawAndVerify(canvas, 9, expected);
	}
	
	@Test
	public void givenCanvasAnd1Line_whenDraw_thenCellsShouldBeUpdatedCorrectly() throws Exception {
		CanvasConfig drawCanvasConfig = new CanvasConfig();
		Dimension dimension = new Dimension(3, 3);
		Canvas canvas = new Canvas(drawCanvasConfig, dimension);
		
		canvas.addDrawableComponent(new DrawableLine(new DrawableLineConfig(), new Point(1, 1), new Point(1, 3)));
		
		List<?> expected = Arrays.asList(
			'-', '-', '-', '-', '-', 
			'|', 'x', ' ', ' ', '|', 
			'|', 'x', ' ', ' ', '|', 
			'|', 'x', ' ', ' ', '|', 
			'-', '-', '-', '-', '-'
		);		

		drawAndVerify(canvas, 25, expected);
	}
	
	@Test
	public void givenCanvasAnd1LineAnd1Rectangle_whenDraw_thenCellsShouldBeUpdatedCorrectly() throws Exception {
		CanvasConfig drawCanvasConfig = new CanvasConfig();
		Dimension dimension = new Dimension(5, 5);
		Canvas canvas = new Canvas(drawCanvasConfig, dimension);
		
		canvas.addDrawableComponent(new DrawableLine(new DrawableLineConfig(), new Point(2, 1), new Point(2, 3)));
		canvas.addDrawableComponent(new DrawableRectangle(new DrawableRectangleConfig('*'), new Point(1, 2), new Point(4, 5)));
		
		List<?> expected = Arrays.asList(
			'-', '-', '-', '-', '-', '-', '-', 
			'|', ' ', 'x', ' ', ' ', ' ', '|', 
			'|', '*', '*', '*', '*', ' ', '|', 
			'|', '*', 'x', ' ', '*', ' ', '|', 
			'|', '*', ' ', ' ', '*', ' ', '|', 
			'|', '*', '*', '*', '*', ' ', '|', 
			'-', '-', '-', '-', '-', '-', '-'
		);

		drawAndVerify(canvas, 49, expected);
	}
	
	@Test
	public void givenCanvasAndComponentsWithCustomizedCharacter_whenDraw_thenCellsShouldBeUpdatedCorrectly() throws Exception {
		CanvasConfig drawCanvasConfig = new CanvasConfig();
		drawCanvasConfig.setHorizontalCharacter('=');
		drawCanvasConfig.setVerticalCharacter('$');
		Dimension dimension = new Dimension(5, 5);
		Canvas canvas = new Canvas(drawCanvasConfig, dimension);
		
		canvas.addDrawableComponent(new DrawableLine(new DrawableLineConfig('+'), new Point(2, 1), new Point(2, 3)));
		canvas.addDrawableComponent(new DrawableLine(new DrawableLineConfig('-'), new Point(5, 1), new Point(3, 1)));
		canvas.addDrawableComponent(new DrawableRectangle(new DrawableRectangleConfig('*'), new Point(1, 2), new Point(4, 5)));
		canvas.addDrawableComponent(new BucketFill(new Point(3 ,3), '?'));
		
		List<?> expected = Arrays.asList(
			'=', '=', '=', '=', '=', '=', '=', 
			'$', ' ', '+', '-', '-', '-', '$', 
			'$', '*', '*', '*', '*', ' ', '$', 
			'$', '*', '+', '?', '*', ' ', '$', 
			'$', '*', '?', '?', '*', ' ', '$', 
			'$', '*', '*', '*', '*', ' ', '$', 
			'=', '=', '=', '=', '=', '=', '='
		);
		
		drawAndVerify(canvas, 49, expected);
	}
	

	private GraphicsDevice createMock() {
		GraphicsDevice graphicsDevice = mock(GraphicsDevice.class);		
		
		doNothing().when(graphicsDevice).draw(isA(char.class));
		doNothing().when(graphicsDevice).goToNextLine();
		
		return graphicsDevice;
	}
	
	private void drawAndVerify(Canvas canvas, int expectedCallTimes, List<?> expectedCells) {
		GraphicsDevice graphicsDevice = createMock();
		canvas.setGraphicsDevice(graphicsDevice);
		
		canvas.draw();
		
		ArgumentCaptor<Character> captor = ArgumentCaptor.forClass(Character.class);

		verify(graphicsDevice, times(expectedCallTimes)).draw(captor.capture());
		
		assertIterableEquals(expectedCells, captor.getAllValues());
	}
}
