package com.cs.codechallenge.geometry;

public class Bounds {
	private final Point point;
	private final Dimension dimension;
	
	public Bounds(Point point, Dimension dimension) {
		super();

		if (point == null) {
			throw new IllegalArgumentException("point should not be null");
		}
		
		if (dimension == null) {
			throw new IllegalArgumentException("dimension should not be null");
		}
		
		this.point = point;
		this.dimension = dimension;
	}
	
	public boolean contains(Bounds other) {
		return this.point.getX() <= other.point.getX() 
			&& this.point.getY() <= other.point.getY() 
			&& this.point.getX() + this.dimension.getWidth() >= other.point.getX() + other.dimension.getWidth()
			&& this.point.getY() + this.dimension.getHeight() >= other.point.getY() + other.dimension.getHeight();
	}
	
	public boolean contains(int x, int y) {
		return this.point.getX() <= x
			&& this.point.getY() <= y
			&& this.point.getX() + this.dimension.getWidth() >= x
			&& this.point.getY() + this.dimension.getHeight() >= y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + dimension.hashCode();
		result = prime * result + point.hashCode();
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
		Bounds other = (Bounds) obj;
		if (!dimension.equals(other.dimension))
			return false;
		if (!point.equals(other.point))
			return false;
		return true;
	}
	
}
