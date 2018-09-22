package com.cs.codechallenge.command;

import com.cs.codechallenge.drawing.DrawableRectangle;
import com.cs.codechallenge.drawing.DrawingContext;

public class DrawRectangleCommand implements DrawingCommand {
	private final DrawingContext drawingContext;
	private final DrawableRectangle rectangle;
	
	public DrawRectangleCommand(DrawingContext drawingContext, DrawableRectangle rectangle) {
		super();	
		
		if (drawingContext == null) {
			throw new IllegalArgumentException("drawingContext should not be null");
		}
		
		if (rectangle == null) {
			throw new IllegalArgumentException("rectangle should not be null");
		}
		
		this.drawingContext = drawingContext;
		this.rectangle = rectangle;
	}

	@Override
	public void execute() throws Exception {
		this.drawingContext.addDrawableComponent(this.rectangle);
		
		this.drawingContext.draw();
	}
	
}
