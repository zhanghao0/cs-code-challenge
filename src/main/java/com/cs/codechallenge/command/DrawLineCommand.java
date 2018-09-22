package com.cs.codechallenge.command;

import com.cs.codechallenge.drawing.DrawableLine;
import com.cs.codechallenge.drawing.DrawingContext;

public class DrawLineCommand implements DrawingCommand {
	private final DrawingContext drawingContext;
	private final DrawableLine line;
	
	public DrawLineCommand(DrawingContext drawingContext, DrawableLine line) {
		super();	
		
		if (drawingContext == null) {
			throw new IllegalArgumentException("drawingContext should not be null");
		}
		
		if (line == null) {
			throw new IllegalArgumentException("line should not be null");
		}
		
		this.drawingContext = drawingContext;
		this.line = line;
	}

	@Override
	public void execute() throws Exception {
		this.drawingContext.addDrawableComponent(this.line);
		
		this.drawingContext.draw();
	}
	
}
