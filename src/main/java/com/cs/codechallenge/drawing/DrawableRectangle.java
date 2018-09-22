package com.cs.codechallenge.drawing;

import java.util.UUID;

import com.cs.codechallenge.geometry.Bounds;
import com.cs.codechallenge.geometry.Dimension;
import com.cs.codechallenge.geometry.Point;

public class DrawableRectangle implements DrawableComponent {
	private final DrawableRectangleConfig drawableRectangleConfig;
	
	private final UUID id;
	private final Point start;
	private final Point end;
	
	public DrawableRectangle(DrawableRectangleConfig drawableRectangleConfig, Point start, Point end) {
		super();
		
		if (drawableRectangleConfig == null) {
			throw new IllegalArgumentException("drawableRectangleConfig should not be null");
		}
		
		if (start == null) {
			throw new IllegalArgumentException("start should not be null");
		}
		
		if (end == null) {
			throw new IllegalArgumentException("end should not be null");
		}
		
		if (start.isTheSame(end))
			throw new IllegalArgumentException("start point and end point should not be the same");
		
		if (start.isOnTheSameHorizontalLine(end) || start.isOnTheSameVerticalLine(end))
			throw new IllegalArgumentException(" start point and end point should not be on the same horizontal or vertical line");
		
		this.drawableRectangleConfig = drawableRectangleConfig;
		this.id = UUID.randomUUID();
		this.start = start;
		this.end = end;
	}

	@Override
	public void draw(Canvas canvas) {
		int sx = Math.min(this.start.getX(), this.end.getX());
		int ex = Math.max(this.start.getX(), this.end.getX());		
		int sy = Math.min(this.start.getY(), this.end.getY());
		int ey = Math.max(this.start.getY(), this.end.getY());

		for (int i = sx; i <= ex; i++) {
			canvas.update(sy, i, this.drawableRectangleConfig.getCharacter());
			canvas.update(ey, i, this.drawableRectangleConfig.getCharacter());
		}

		for (int i = sy + 1; i < ey; i++) {
			canvas.update(i, sx, this.drawableRectangleConfig.getCharacter());
			canvas.update(i, ex, this.drawableRectangleConfig.getCharacter());
		}
	}

	@Override
	public Bounds getBounds() {
		int x = this.start.getX() < this.end.getX() ? this.start.getX() : this.end.getX();
		int y = this.start.getY() < this.end.getY() ? this.start.getY() : this.end.getY();
		int width = Math.abs(this.start.getX() - this.end.getX()) + 1;
		int height = Math.abs(this.start.getY() - this.end.getY()) + 1;
		
		return new Bounds(new Point(x, y), new Dimension(width, height));
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
		DrawableRectangle other = (DrawableRectangle) obj;
		if (!id.equals(other.id))
			return false;
		return true;
	}
}
