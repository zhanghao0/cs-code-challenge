package com.cs.codechallenge.drawing;

public class ConsoleTerminal implements GraphicsDevice {

	@Override
	public void draw(char ch) {
		System.out.print(ch);
	}

	@Override
	public void goToNextLine() {
		System.out.println();
	}

}
