package com.cs.codechallenge.drawing;

public interface DrawingContext {
	void addCanvas(Canvas canvas) throws Exception;
	void addDrawableComponent(DrawableComponent component) throws Exception;
	
	void draw();
}
