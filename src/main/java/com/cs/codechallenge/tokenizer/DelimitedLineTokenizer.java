package com.cs.codechallenge.tokenizer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.cs.codechallenge.io.FieldSet;

public class DelimitedLineTokenizer implements LineTokenizer {
	public static final String DELIMITER_WHITESPACE = " ";
	
	private final String delimiter;
	
	public DelimitedLineTokenizer() {
		this(DELIMITER_WHITESPACE);
	}
	
	public DelimitedLineTokenizer(String delimiter) {
		super();
		this.delimiter = delimiter;
	}

	@Override
	public FieldSet tokenize(String line) {
		if (line == null)
			return new FieldSet(Collections.emptyList());

		String[] fields = line.split(this.delimiter);
		
		List<String> list = new ArrayList<String>(fields.length);
		
		for (String field : fields) {
			String trimmed = field.trim();
			if (!trimmed.isEmpty()) {
				list.add(trimmed);
			}
		}
		
		return new FieldSet(list);
	}

}
