package com.cs.codechallenge.drawing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.cs.codechallenge.drawing.DrawableLineConfig;

public class DrawableLineConfigTest {
	@Test
	public void givenCharacterIsW_whenGetCharacter_thenReturnTheCharacter() {
		DrawableLineConfig config = new DrawableLineConfig('W');
		
		assertEquals('W', config.getCharacter());
	}
}
