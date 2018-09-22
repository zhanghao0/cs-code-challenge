package com.cs.codechallenge.drawing;

public class CanvasConfig {
	public static final char HORIZONTAL_CHARACTER = '-';
	public static final char VERTICAL_CHARACTER = '|';
	public static final char CELL_CHARACTER = ' ';
	public static final int MAX_WIDTH = 50;
	public static final int MAX_HEIGHT = 50;

	private char horizontalCharacter = HORIZONTAL_CHARACTER;
	private char verticalCharacter = VERTICAL_CHARACTER;
	private char cellCharacter = CELL_CHARACTER;
	private int maxWidth = MAX_WIDTH;
	private int maxHeight = MAX_HEIGHT;

	public char getHorizontalCharacter() {
		return horizontalCharacter;
	}

	public void setHorizontalCharacter(char horizontalCharacter) {
		this.horizontalCharacter = horizontalCharacter;
	}

	public char getVerticalCharacter() {
		return verticalCharacter;
	}

	public void setVerticalCharacter(char verticalCharacter) {
		this.verticalCharacter = verticalCharacter;
	}

	public char getCellCharacter() {
		return cellCharacter;
	}

	public void setCellCharacter(char cellCharacter) {
		this.cellCharacter = cellCharacter;
	}

	public int getMaxWidth() {
		return maxWidth;
	}

	public void setMaxWidth(int maxWidth) {
		if (maxWidth < 1) {
			throw new IllegalArgumentException("maxWidth should be a positive integer");
		}
		
		this.maxWidth = maxWidth;
	}

	public int getMaxHeight() {
		return maxHeight;
	}

	public void setMaxHeight(int maxHeight) {		
		if (maxHeight < 1) {
			throw new IllegalArgumentException("maxHeight should be a positive integer");
		}
		
		this.maxHeight = maxHeight;
	}

}
