package com.cs.codechallenge.geometry;

public class Point {
	private final int x;
	private final int y;
	
	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean isTheSame(Point other) {
		return this.x == other.x && this.y == other.y;
	}
	
	public boolean isOnTheSameHorizontalLine(Point another) {
		return this.y == another.y;
	}
	
	public boolean isOnTheSameVerticalLine(Point another) {
		return this.x == another.x;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
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
		Point other = (Point) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
}
