package com.cs.codechallenge.io.reader;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cs.codechallenge.io.reader.ReaderNotOpenException;
import com.cs.codechallenge.io.reader.SimpleItemReader;

@ExtendWith(MockitoExtension.class)
public class SimpleItemReaderTest {
	@Test
	public void givenInputStreamIsNull_whenConstructor_thenThrowException() throws Exception {
		InputStream inputStream = null;
		
		assertThrows(IllegalArgumentException.class, () -> new SimpleItemReader(inputStream));
	}
	
	@Test
	public void givenReaderIsNotOpen_whenRead_thenThrowException() throws Exception {
		try (SimpleItemReader reader = new SimpleItemReader()) {
			assertThrows(ReaderNotOpenException.class, () -> reader.read());
		}
	}
	
	@Test
	public void givenReaderIsOpenAndNoUserInput_whenRead_thenReturnNull() throws Exception {
		InputStream inputStream = this.createMock(false);
		
		try (SimpleItemReader reader = new SimpleItemReader(inputStream)) {
			reader.open();

			String line = reader.read();
			
			assertNull(line);
		}
	}
	
	@Test
	public void givenReaderIsOpenAndHasUserInput_whenRead_thenReturnUserInput() throws Exception {
		InputStream inputStream = this.createMock(true);
		
		try (SimpleItemReader reader = new SimpleItemReader(inputStream)) {
			reader.open();

			String line = reader.read();
			
			assertNotNull(line);
		}
	}
	
	private InputStream createMock(boolean hasUserInput) throws IOException {
		InputStream inputStream = mock(InputStream.class);
		
		int value = hasUserInput ? 1 : -1;
		when(inputStream.read(isA(byte[].class), isA(int.class), isA(int.class))).thenReturn(value, -1);
		
		return inputStream;
	}
}
