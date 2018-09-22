package com.cs.codechallenge.drawing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.cs.codechallenge.drawing.BucketFillConfig;

public class BucketFillConfigTest {
	@Test
	public void givenColorCharacterIgnoreCaseIsFalse_whenIsColorCharacterIgnoreCase_thenReturnFalse() {
		BucketFillConfig config = new BucketFillConfig();
		config.setColorCharacterIgnoreCase(false);
		
		assertEquals(false, config.isColorCharacterIgnoreCase());
	}
}
