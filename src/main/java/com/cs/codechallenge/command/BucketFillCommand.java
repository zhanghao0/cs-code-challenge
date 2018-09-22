package com.cs.codechallenge.command;

import com.cs.codechallenge.drawing.BucketFill;
import com.cs.codechallenge.drawing.DrawingContext;

public class BucketFillCommand implements DrawingCommand {
	private final DrawingContext drawingContext;
	private final BucketFill bucketFill;
	
	public BucketFillCommand(DrawingContext drawingContext, BucketFill bucketFill) {
		super();	
		
		if (drawingContext == null) {
			throw new IllegalArgumentException("drawingContext should not be null");
		}
		
		if (bucketFill == null) {
			throw new IllegalArgumentException("bucketFill should not be null");
		}
		
		this.drawingContext = drawingContext;
		this.bucketFill = bucketFill;
	}

	@Override
	public void execute() throws Exception {
		this.drawingContext.addDrawableComponent(this.bucketFill);
		
		this.drawingContext.draw();
	}
}
