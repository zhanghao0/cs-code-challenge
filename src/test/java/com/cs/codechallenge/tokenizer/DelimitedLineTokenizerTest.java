package com.cs.codechallenge.tokenizer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.cs.codechallenge.io.FieldSet;
import com.cs.codechallenge.tokenizer.DelimitedLineTokenizer;

public class DelimitedLineTokenizerTest {
	@Test
	public void givenLineIsNull_whenTokenize_thenReturnEmptyFieldSet() {
		String line = null;
		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
		
		FieldSet fieldSet = tokenizer.tokenize(line);
		
		assertEquals(0, fieldSet.getFieldCount());
	}
	
	@Test
	public void givenLineIsEmpty_whenTokenize_thenReturnEmptyFieldSet() {
		String line = "";
		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
		
		FieldSet fieldSet = tokenizer.tokenize(line);
		
		assertEquals(0, fieldSet.getFieldCount());
	}
	
	@Test
	public void givenLineIsBlank_whenTokenize_thenReturnEmptyFieldSet() {
		String line = "\t  ";
		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
		
		FieldSet fieldSet = tokenizer.tokenize(line);
		
		assertEquals(0, fieldSet.getFieldCount());
	}
	
	@Test
	public void givenLineIsA_whenTokenize_thenReturnFieldSetWithValueA() {
		String line = "A";
		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
		
		FieldSet fieldSet = tokenizer.tokenize(line);

		assertEquals(1, fieldSet.getFieldCount());
		assertEquals('A', fieldSet.readChar(0));
	}
	
	@Test
	public void givenLineIsAB_whenTokenize_thenReturnFieldSetWithValueAB() {
		String line = "AB";
		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
		
		FieldSet fieldSet = tokenizer.tokenize(line);

		assertEquals(1, fieldSet.getFieldCount());
		assertEquals("AB", fieldSet.readString(0));
	}
	
	@Test
	public void givenLineContains0And1_whenTokenize_thenReturnFieldSetWithValue0And1() {
		String line = "0 1";
		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
		
		FieldSet fieldSet = tokenizer.tokenize(line);
		
		assertEquals(2, fieldSet.getFieldCount());
		assertEquals(0, fieldSet.readInt(0));
		assertEquals(1, fieldSet.readInt(1));
	}
	
	@Test
	public void givenLineContainsLeadingAndTrailingSpaces_whenTokenize_thenReturnFieldSetWithProperValues() {
		String line = "  0 1 ";
		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
		
		FieldSet fieldSet = tokenizer.tokenize(line);
		
		assertEquals(2, fieldSet.getFieldCount());
		assertEquals(0, fieldSet.readInt(0));
		assertEquals(1, fieldSet.readInt(1));
	}
	
	@Test
	public void givenTokensAreSeparatedByComma_whenTokenize_thenReturnFieldSetWithProperValues() {
		String line = "0,1";
		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer(",");
		
		FieldSet fieldSet = tokenizer.tokenize(line);
		
		assertEquals(2, fieldSet.getFieldCount());
		assertEquals(0, fieldSet.readInt(0));
		assertEquals(1, fieldSet.readInt(1));
	}
	
	@Test
	public void givenTokensAreSeparatedByComma_whenTokenize_thenResultShouldTrimSpacesBetweenTokens() {
		String line = "AB , CDE, F";
		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer(",");
		
		FieldSet fieldSet = tokenizer.tokenize(line);
		
		assertEquals(3, fieldSet.getFieldCount());
		assertEquals("AB", fieldSet.readString(0));
		assertEquals("CDE", fieldSet.readString(1));
		assertEquals("F", fieldSet.readString(2));
	}
}
