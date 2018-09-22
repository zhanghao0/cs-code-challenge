package com.cs.codechallenge.drawing;

public class SimpleDrawingContext implements DrawingContext {
	private Canvas canvas;
	
	@Override
	public void addCanvas(Canvas canvas) throws Exception {
		if (canvas == null) {
			throw new IllegalArgumentException("canvas should not be null");
		}
		
		if (this.canvas != null) {
			throw new InvalidDrawableComponentException("Canvas has been created");
		}
		
		this.canvas = canvas;
	}

	@Override
	public void addDrawableComponent(DrawableComponent component) throws Exception {
		if (component == null) {
			throw new IllegalArgumentException("component should not be null");
		}
		
		if (this.canvas == null) {
			throw new InvalidDrawableComponentException("Canvas has not been created");
		}
		
		this.canvas.addDrawableComponent(component);
	}

	@Override
	public void draw() {
		if (this.canvas != null) {
			this.canvas.draw();
		}
	}
	
}
