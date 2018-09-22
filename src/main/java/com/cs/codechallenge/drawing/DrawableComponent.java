package com.cs.codechallenge.drawing;

import com.cs.codechallenge.geometry.Bounds;

public interface DrawableComponent {
	void draw(Canvas canvas);
	
	Bounds getBounds();
}
