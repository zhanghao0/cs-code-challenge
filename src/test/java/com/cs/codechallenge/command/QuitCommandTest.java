package com.cs.codechallenge.command;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import com.cs.codechallenge.ExitApplicationListener;
import com.cs.codechallenge.command.QuitCommand;

public class QuitCommandTest {
	
	@Test
	public void givenListenerIsNull_whenConstruct_ThrowException() {
		ExitApplicationListener listener = null;
		
		assertThrows(IllegalArgumentException.class, () -> new QuitCommand(listener));
	}
	
	@Test
	public void givenCommandIsConstructed_whenExecute_thenExitApplication() throws IOException {
		ExitApplicationListener listener = mock(ExitApplicationListener.class);
		try (
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
			) {	        
		        System.setOut(new PrintStream(bos));
		        
		        QuitCommand command = new QuitCommand(listener);
		        
		        command.execute();
		        
		        bos.flush();
		        
		        String allWrittenLines = new String(bos.toByteArray()); 
		        assertTrue(allWrittenLines.contains("Quit the program"));
		        
		        verify(listener, times(1)).onExit();
		}
	}
}
