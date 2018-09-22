package com.cs.codechallenge.drawing;

public class DrawableRectangleConfig {
	public static final char CHARACTER = 'x';

	private final char character;
	
	public DrawableRectangleConfig(char character) {
		super();
		this.character = character;
	}

	public DrawableRectangleConfig() {
		this(CHARACTER);
	}

	public char getCharacter() {
		return character;
	}
	
}
