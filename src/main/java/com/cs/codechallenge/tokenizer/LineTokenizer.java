package com.cs.codechallenge.tokenizer;

import com.cs.codechallenge.io.FieldSet;

public interface LineTokenizer {
	FieldSet tokenize(String line);
}
