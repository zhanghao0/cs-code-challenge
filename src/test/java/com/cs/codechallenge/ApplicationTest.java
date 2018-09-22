package com.cs.codechallenge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import com.cs.codechallenge.Application;
import com.cs.codechallenge.command.Command;
import com.cs.codechallenge.io.reader.ItemReader;
import com.cs.codechallenge.parser.CommandCannotBeParsedException;
import com.cs.codechallenge.parser.CommandParser;

@ExtendWith(MockitoExtension.class)
public class ApplicationTest {
	@Test
	public void givenCommandParserIsNull_whenRun_ThrowException() {
		Application application = new Application();
		
		assertThrows(IllegalArgumentException.class, () -> application.run());
	}

	@Test
	public void givenReaderIsNull_whenRun_ThrowException() {
		CommandParser<?> commandParser = mock(CommandParser.class);
		Application application = new Application();
		application.setCommandParser(commandParser);
		
		assertThrows(IllegalArgumentException.class, () -> application.run());
	}

	@Test
	public void givenCommandParserReturnsNull_whenRun_ThenNoCommandExecuted() throws Exception {
		@SuppressWarnings("unchecked")
		CommandParser<Command> commandParser = mock(CommandParser.class);
		when(commandParser.parse(isA(String.class))).thenReturn(null);
		
		@SuppressWarnings("unchecked")
		ItemReader<String> reader = mock(ItemReader.class);
		doNothing().when(reader).open();
		when(reader.read()).thenReturn("command text");
		
		Application application = new Application();
		application.setCommandParser(commandParser);
		application.setReader(reader);
		
		try (
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
			) {	        
		        System.setOut(new PrintStream(bos));
				
				application.run();
		        
		        bos.flush();
				
				int value = application.getExecutedCommands();
				
				assertEquals(0, value);
		}
	}

	@Test
	public void givenCommandParserReturnsACommand_whenRun_ThenTheCommandIsExecuted() throws Exception {
		Command command = mock(Command.class);
		doNothing().when(command).execute();
		
		@SuppressWarnings("unchecked")
		CommandParser<Command> commandParser = mock(CommandParser.class);
		when(commandParser.parse(isA(String.class))).thenReturn(command, (Command)null);
		
		@SuppressWarnings("unchecked")
		ItemReader<String> reader = mock(ItemReader.class);
		doNothing().when(reader).open();
		when(reader.read()).thenReturn("command text");
		
		Application application = new Application();
		application.setCommandParser(commandParser);
		application.setReader(reader);
		
		try (
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
			) {	        
		        System.setOut(new PrintStream(bos));
				
				application.run();
		        
		        bos.flush();
				
				int value = application.getExecutedCommands();
				
				assertEquals(1, value);
				verify(command, times(1)).execute();
		}
	}
	

	@Test
	public void givenReaderThrowsException_whenRun_ThenApplicationWillExit() throws Exception {
		@SuppressWarnings("unchecked")
		CommandParser<Command> commandParser = mock(CommandParser.class);
		
		@SuppressWarnings("unchecked")
		ItemReader<String> reader = mock(ItemReader.class);
		doNothing().when(reader).open();
		when(reader.read()).thenThrow(new Exception("reder exception"));
		
		Application application = new Application();
		application.setCommandParser(commandParser);
		application.setReader(reader);
		
		try (
				ByteArrayOutputStream out = new ByteArrayOutputStream();
			) {	        
		        System.setOut(new PrintStream(out));
				
				assertThrows(Exception.class, () -> application.run());
		        
		        out.flush();

				verify(commandParser, never()).parse(isA(String.class));
				verify(reader, times(1)).close();
				
				int value = application.getExecutedCommands();
				
				assertEquals(0, value);
		}
	}

	@Test
	public void givenCommandParserThrowsException_whenRun_ThenExceptionWillBeCaught() throws Exception {
		Command command = mock(Command.class);
		doNothing().when(command).execute();
		
		@SuppressWarnings("unchecked")
		CommandParser<Command> commandParser = mock(CommandParser.class);
		when(commandParser.parse(isA(String.class))).thenAnswer(new Answer<Command> () {

			@Override
			public Command answer(InvocationOnMock invocation) throws Throwable {
				Object[] args = invocation.getArguments();
				
				if (args[0].equals("command 1")) {
					throw new CommandCannotBeParsedException("test exception");
				} else if (args[0].equals("command 2")) { 
					return command;
				} else {
					return null;
				}
			}			
		});
		
		@SuppressWarnings("unchecked")
		ItemReader<String> reader = mock(ItemReader.class);
		doNothing().when(reader).open();
		when(reader.read()).thenReturn("command 1", "command 2", "command 3");
		
		Application application = new Application();
		application.setCommandParser(commandParser);
		application.setReader(reader);
		
		try (
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				ByteArrayOutputStream err = new ByteArrayOutputStream();
			) {	        
		        System.setOut(new PrintStream(out));
		        System.setErr(new PrintStream(err));
				
				application.run();
		        
		        out.flush();
		        err.flush();
				
				int value = application.getExecutedCommands();
				
				assertEquals(1, value);

				verify(reader, times(1)).close();
				
				String allWrittenLines = new String(err.toByteArray()); 
		        assertTrue(allWrittenLines.contains("test exception"));
		}
	}
}
