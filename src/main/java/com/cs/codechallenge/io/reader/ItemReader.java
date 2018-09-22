package com.cs.codechallenge.io.reader;


public interface ItemReader<T> extends AutoCloseable {
	void open() throws Exception;
	
	T read() throws Exception;
}
