package com.cs.codechallenge.drawing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.cs.codechallenge.drawing.CanvasConfig;

public class CanvasConfigTest {
	
	@Test
	public void givenCellCharacterIsO_whenGetCellCharacter_thenReturnO() {
		CanvasConfig config = new CanvasConfig();
		config.setCellCharacter('O');
		
		assertEquals('O', config.getCellCharacter());
	}
	
	@Test
	public void givenMaxWidthIsNotPositive_whenSetMaxWidth_thenThrowException() {
		CanvasConfig config = new CanvasConfig();
		int maxWidth = 0;
		
		assertThrows(IllegalArgumentException.class, () -> config.setMaxWidth(maxWidth));
	}
	
	@Test
	public void givenMaxWidthIs10_whenGetMaxWidth_thenReturn10() {
		CanvasConfig config = new CanvasConfig();
		config.setMaxWidth(10);
		
		assertEquals(10, config.getMaxWidth());
	}
	
	@Test
	public void givenMaxHeightIsNotPositive_whenSetMaxHeight_thenThrowException() {
		CanvasConfig config = new CanvasConfig();
		int maxHeight = 0;
		
		assertThrows(IllegalArgumentException.class, () -> config.setMaxHeight(maxHeight));
	}
	
	@Test
	public void givenMaxHeightIs10_whenGetMaxHeight_thenReturn10() {
		CanvasConfig config = new CanvasConfig();
		config.setMaxHeight(10);
		
		assertEquals(10, config.getMaxHeight());
	}
}
