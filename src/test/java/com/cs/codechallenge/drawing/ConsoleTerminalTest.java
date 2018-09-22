package com.cs.codechallenge.drawing;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import com.cs.codechallenge.drawing.ConsoleTerminal;

public class ConsoleTerminalTest {
	@Test
	public void givenCharacterIsA_whenDraw_thenOutputA() throws IOException {
		char ch = 'A';
		
		try (
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
		) {	        
	        System.setOut(new PrintStream(bos));
	        ConsoleTerminal consoleTerminal = new ConsoleTerminal();
	        
	        consoleTerminal.draw(ch);
	        
	        bos.flush();
	        
	        String allWrittenLines = new String(bos.toByteArray()); 
	        assertTrue(allWrittenLines.contains("A"));
		}
	}
	
	@Test
	public void givenNothing_whenGoToNextLine_thenANewLineIsWritten() throws IOException {
		try (
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
		) {	        
	        System.setOut(new PrintStream(bos));
	        ConsoleTerminal consoleTerminal = new ConsoleTerminal();
	        
	        consoleTerminal.goToNextLine();
	        
	        bos.flush();
	        
	        String allWrittenLines = new String(bos.toByteArray()); 
	        assertTrue(allWrittenLines.equals(System.lineSeparator()));
		}
	}
}
