package com.cs.codechallenge.io;

import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.List;
import java.util.Locale;

public class FieldSet {
	private final List<String> fields;
	private final NumberFormat integerFormat;

	public FieldSet(List<String> fields) {
		this(fields, Locale.US);
	}

	public FieldSet(List<String> fields, Locale locale) {
		super();
		
		if (fields == null) {
			throw new IllegalArgumentException("fields should not be null");
		}
		
		if (locale == null) {
			throw new IllegalArgumentException("locale should not be null");
		}
		
		this.fields = fields;
		this.integerFormat = NumberFormat.getIntegerInstance(locale);
	}
	
	public int getFieldCount() {
		return this.fields.size();
	}
	
	public String readString(int index) {
		return this.readAndTrim(index);	
	}
	
	public char readChar(int index) {
		String value = readAndTrim(index);
		
		if ("\\s".equals(value)) {
			value = " ";
		}
		
		if (value == null || value.length() > 1) {
			throw new InvalidValueException("Not a character: " + value);
		}
		
		return value.charAt(0);
	}
	
	public int readInt(int index) {
		String value = readAndTrim(index);
		
		ParsePosition position = new ParsePosition(0);
		Number number = integerFormat.parse(value, position);
		
		if (number == null || position.getIndex() != value.length()) {
			throw new InvalidValueException("Not an integer: " + value);
		}
		
		return number.intValue();
	}
	
	protected String readAndTrim(int index) {
		String value = fields.get(index);

		if (value != null) {
			return value.trim();
		}
		else {
			return null;
		}
	}
}
