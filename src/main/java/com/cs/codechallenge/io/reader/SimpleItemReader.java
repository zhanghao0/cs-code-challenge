package com.cs.codechallenge.io.reader;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SimpleItemReader implements ItemReader<String> {

	private final InputStream inputStream;
	private BufferedReader reader;

	public SimpleItemReader(InputStream inputStream) {
		super();
		
		if (inputStream == null) {
			throw new IllegalArgumentException("inputStream should not be null");
		}
		
		this.inputStream = inputStream;
	}

	public SimpleItemReader() {
		this(System.in);
	}

	@Override
	public void open() throws Exception {
		this.reader = new BufferedReader(new InputStreamReader(this.inputStream));
	}

	@Override
	public void close() throws Exception {
		if (this.reader != null) {
			this.reader.close();
		}
	}

	@Override
	public String read() throws Exception {
		if (this.reader == null) {
			throw new ReaderNotOpenException("Reader must be open before it can be read.");
		}

		String line =  this.reader.readLine();
		
		if (line == null)
			return null;
		
		return line;
	}
	
}
