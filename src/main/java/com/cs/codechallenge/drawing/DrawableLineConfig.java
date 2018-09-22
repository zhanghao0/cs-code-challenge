package com.cs.codechallenge.drawing;

public class DrawableLineConfig {
	public static final char CHARACTER = 'x';

	private final char character;
	
	public DrawableLineConfig(char character) {
		super();
		this.character = character;
	}

	public DrawableLineConfig() {
		this(CHARACTER);
	}

	public char getCharacter() {
		return character;
	}
	
}
