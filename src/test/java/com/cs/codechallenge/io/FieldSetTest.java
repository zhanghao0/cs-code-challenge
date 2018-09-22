package com.cs.codechallenge.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.Test;

import com.cs.codechallenge.io.FieldSet;
import com.cs.codechallenge.io.InvalidValueException;

public class FieldSetTest {
	@Test
	public void givenFieldsAreNull_whenConsturctor_thenThrowException() {
		List<String> fields = null;
		
		assertThrows(IllegalArgumentException.class, () -> new FieldSet(fields));
	}
	
	@Test
	public void givenLocaleIsNull_whenConsturctor_thenThrowException() {
		List<String> fields = Collections.emptyList();
		Locale locale = null;
		
		assertThrows(IllegalArgumentException.class, () -> new FieldSet(fields, locale));
	}
	
	
	@Test
	public void givenFieldsAreEmpty_whenGetFieldCount_thenReturn0() {
		List<String> fields = Collections.emptyList();
		FieldSet fieldSet = new FieldSet(fields);
		
		int count = fieldSet.getFieldCount();
		
		assertEquals(0, count);
	}
	
	@Test
	public void givenFieldsHave1String_whenGetFieldCount_thenReturn1() {
		List<String> fields = Arrays.asList("A");
		FieldSet fieldSet = new FieldSet(fields);
		
		int count = fieldSet.getFieldCount();
		
		assertEquals(1, count);
	}
	
	
	@Test
	public void givenFieldsAreEmpty_whenReadString_thenThrowException() {
		List<String> fields = Collections.emptyList();
		FieldSet fieldSet = new FieldSet(fields);
		
		assertThrows(IndexOutOfBoundsException.class, () -> fieldSet.readString(0));
	}
	
	@Test
	public void givenFieldsHave1Null_whenReadString_thenReturnNull() {
		List<String> fields = Arrays.asList((String)null);
		FieldSet fieldSet = new FieldSet(fields);
		
		String value = fieldSet.readString(0);
		
		assertNull(value);
	}
	
	@Test
	public void givenFieldsHave1String_whenReadString_thenReturnTheString() {
		List<String> fields = Arrays.asList("AB");
		FieldSet fieldSet = new FieldSet(fields);
		
		String value = fieldSet.readString(0);
		
		assertEquals("AB", value);
	}
	
	@Test
	public void givenFieldsHave1StringWithSpaces_whenReadString_thenReturnTrimmedString() {
		List<String> fields = Arrays.asList("  AB ");
		FieldSet fieldSet = new FieldSet(fields);
		
		String value = fieldSet.readString(0);
		
		assertEquals("AB", value);
	}
	
	
	@Test
	public void givenFieldsAreEmpty_whenReadChar_thenThrowException() {
		List<String> fields = Collections.emptyList();
		FieldSet fieldSet = new FieldSet(fields);
		
		assertThrows(IndexOutOfBoundsException.class, () -> fieldSet.readChar(0));
	}
	
	@Test
	public void givenFieldsHave2Chars_whenReadChar_thenThrowException() {
		List<String> fields = Arrays.asList("AB");
		FieldSet fieldSet = new FieldSet(fields);

		assertThrows(InvalidValueException.class, () -> fieldSet.readChar(0));
	}
	
	@Test
	public void givenFieldsHave1EscpasAnd1Char_whenReadChar_thenThrowException() {
		List<String> fields = Arrays.asList("\\s");
		FieldSet fieldSet = new FieldSet(fields);

		char value = fieldSet.readChar(0);
		
		assertEquals(' ', value);
	}
	
	@Test
	public void givenFieldsHave1Char_whenReadChar_thenReturnTheChar() {
		List<String> fields = Arrays.asList("A");
		FieldSet fieldSet = new FieldSet(fields);
		
		char value = fieldSet.readChar(0);
		
		assertEquals('A', value);
	}
	
	@Test
	public void givenFieldsHave1CharWithSpaces_whenReadChar_thenReturnTrimmedChar() {
		List<String> fields = Arrays.asList("  A ");
		FieldSet fieldSet = new FieldSet(fields);
		
		char value = fieldSet.readChar(0);
		
		assertEquals('A', value);
	}

	
	@Test
	public void givenFieldsAreEmpty_whenReadInt_thenThrowException() {
		List<String> fields = Collections.emptyList();
		FieldSet fieldSet = new FieldSet(fields);
		
		assertThrows(IndexOutOfBoundsException.class, () -> fieldSet.readInt(0));
	}
	
	@Test
	public void givenFieldsHave1Letter_whenReadInt_thenThrowException() {
		List<String> fields = Arrays.asList("A");
		FieldSet fieldSet = new FieldSet(fields);

		assertThrows(InvalidValueException.class, () -> fieldSet.readInt(0));
	}
	
	@Test
	public void givenFieldsHave1Decimal_whenReadInt_thenThrowException() {
		List<String> fields = Arrays.asList("1.0");
		FieldSet fieldSet = new FieldSet(fields);

		assertThrows(InvalidValueException.class, () -> fieldSet.readInt(0));
	}
	
	@Test
	public void givenFieldsHave1Int_whenReadInt_thenReturnTheInt() {
		List<String> fields = Arrays.asList("1");
		FieldSet fieldSet = new FieldSet(fields);
		
		int value = fieldSet.readInt(0);
		
		assertEquals(1, value);
	}
	
	@Test
	public void givenFieldsHave1IntWithSpaces_whenReadInt_thenReturnTrimmedInt() {
		List<String> fields = Arrays.asList("  1 ");
		FieldSet fieldSet = new FieldSet(fields);
		
		int value = fieldSet.readInt(0);
		
		assertEquals(1, value);
	}
}
