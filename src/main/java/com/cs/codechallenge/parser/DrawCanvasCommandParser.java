package com.cs.codechallenge.parser;

import com.cs.codechallenge.command.DrawCanvasCommand;
import com.cs.codechallenge.drawing.Canvas;
import com.cs.codechallenge.drawing.CanvasConfig;
import com.cs.codechallenge.drawing.DrawingContext;
import com.cs.codechallenge.geometry.Dimension;
import com.cs.codechallenge.io.FieldSet;

public class DrawCanvasCommandParser extends AbstractCommandParser<DrawCanvasCommand> {
	public static final String COMMAND_TYPE = "C";
	public static final int COMMAND_ARGUMENT_LENGTH = 3;

	private final DrawingContext drawingContext;
	private final CanvasConfig canvasConfig;
	
	public DrawCanvasCommandParser(String commandType, DrawingContext drawingContext, CanvasConfig canvasConfig) {
		super(commandType);	
		
		if (drawingContext == null) {
			throw new IllegalArgumentException("drawingContext should not be null");
		}
		
		if (canvasConfig == null) {
			throw new IllegalArgumentException("canvasConfig should not be null");
		}
		
		this.drawingContext = drawingContext;
		this.canvasConfig = canvasConfig;
	}

	public DrawCanvasCommandParser(DrawingContext drawingContext) {
		this(COMMAND_TYPE, drawingContext, new CanvasConfig());	
	}

	@Override
	protected DrawCanvasCommand parse(String commandText, FieldSet fieldSet) throws CommandParsingException {
		String type = fieldSet.readString(0);
		
		if (!isAcceptableCommandType(type))
			return null;
		
		if (fieldSet.getFieldCount() != COMMAND_ARGUMENT_LENGTH)
			throw new IncorrectNumberOfCommandArgumentException("Too many or few command arguments: " + commandText);
		
		int width = fieldSet.readInt(1);		
		int height = fieldSet.readInt(2);		
		
		Canvas canvas = new Canvas(this.canvasConfig, new Dimension(width, height));
		
		return new DrawCanvasCommand(this.drawingContext, canvas);
	}
}
