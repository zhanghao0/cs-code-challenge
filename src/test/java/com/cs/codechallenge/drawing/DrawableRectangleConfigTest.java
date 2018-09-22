package com.cs.codechallenge.drawing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.cs.codechallenge.drawing.DrawableRectangleConfig;

public class DrawableRectangleConfigTest {
	@Test
	public void givenCharacterIsW_whenGetCharacter_thenReturnTheCharacter() {
		DrawableRectangleConfig config = new DrawableRectangleConfig('W');
		
		assertEquals('W', config.getCharacter());
	}
}
