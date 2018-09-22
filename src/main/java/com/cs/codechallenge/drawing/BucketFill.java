package com.cs.codechallenge.drawing;

import java.util.UUID;

import com.cs.codechallenge.geometry.Bounds;
import com.cs.codechallenge.geometry.Dimension;
import com.cs.codechallenge.geometry.Point;

public class BucketFill implements DrawableComponent {	
	
	private final UUID id;
	private final Point point;
	private final char colorCharacter;

	public BucketFill(Point point, char colorCharacter) {
		super();
		
		if (point == null) {
			throw new IllegalArgumentException("point should not be null");
		}
		
		this.id = UUID.randomUUID();
		this.point = point;
		this.colorCharacter = colorCharacter;
	}

	@Override
	public void draw(Canvas canvas) {
		char target = canvas.get(this.point.getY(), this.point.getX());
		
		this.fill(canvas, target, this.colorCharacter, this.point.getY(), this.point.getX());
	}

	@Override
	public Bounds getBounds() {
		return new Bounds(new Point(point.getX(), point.getY()), new Dimension(0, 0));
	}

	//https://en.wikipedia.org/wiki/Flood_fill#The_algorithm
	private void fill(Canvas canvas, char target, char replacement, int row, int column) {
		if (this.compareCharacter(target, replacement)) {
			return;
		}
		
		char ch = canvas.get(row, column);
		
		if (!this.compareCharacter(ch, target))
			return;
		
		canvas.update(row, column, replacement);
		
		if (canvas.getBounds().contains(column, row + 1)) {
			fill(canvas, ch, replacement, row + 1, column);
		}
		
		if (canvas.getBounds().contains(column, row - 1)) {
			fill(canvas, ch, replacement, row - 1, column);
		}
		
		if (canvas.getBounds().contains(column - 1, row)) {
			fill(canvas, ch, replacement, row, column - 1);
		}
		
		if (canvas.getBounds().contains(column + 1, row)) {
			fill(canvas, ch, replacement, row, column + 1);
		}
	}
	
	private boolean compareCharacter(char ch1, char ch2) {
		return ch1 == ch2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id.hashCode();
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
		BucketFill other = (BucketFill) obj;
		if(!id.equals(other.id))
			return false;
		return true;
	}
}
