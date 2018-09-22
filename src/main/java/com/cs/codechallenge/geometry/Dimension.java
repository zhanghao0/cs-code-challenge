package com.cs.codechallenge.geometry;

public class Dimension {

	private final int width;
	private final int height;
	
	public Dimension(int width, int height) {
		super();
		
		if (width < 0) {
			throw new IllegalArgumentException("width should be zero or a positive integer");
		}
		
		if (height < 0) {
			throw new IllegalArgumentException("height should be zero or a positive integer");
		}
		
		this.width = width;
		this.height = height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + height;
		result = prime * result + width;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dimension other = (Dimension) obj;
		if (height != other.height)
			return false;
		if (width != other.width)
			return false;
		return true;
	}
	
}
