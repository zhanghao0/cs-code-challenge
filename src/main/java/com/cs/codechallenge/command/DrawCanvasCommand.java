package com.cs.codechallenge.command;

import com.cs.codechallenge.drawing.Canvas;
import com.cs.codechallenge.drawing.DrawingContext;

public class DrawCanvasCommand implements DrawingCommand {
	private final DrawingContext drawingContext;
	private final Canvas canvas;
	
	public DrawCanvasCommand(DrawingContext drawingContext, Canvas canvas) {
		super();
		
		if (drawingContext == null) {
			throw new IllegalArgumentException("drawingContext should not be null");
		}
		
		if (canvas == null) {
			throw new IllegalArgumentException("canvas should not be null");
		}
		
		this.drawingContext = drawingContext;
		this.canvas = canvas;
	}

	@Override
	public void execute() throws Exception {
		this.drawingContext.addCanvas(canvas);

		this.drawingContext.draw();
	}
}
