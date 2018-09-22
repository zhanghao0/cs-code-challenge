package com.cs.codechallenge.parser;

import com.cs.codechallenge.command.DrawRectangleCommand;
import com.cs.codechallenge.drawing.DrawableRectangle;
import com.cs.codechallenge.drawing.DrawableRectangleConfig;
import com.cs.codechallenge.drawing.DrawingContext;
import com.cs.codechallenge.geometry.Point;
import com.cs.codechallenge.io.FieldSet;

public class DrawRectangleCommandParser extends AbstractCommandParser<DrawRectangleCommand> {
	public static final String COMMAND_TYPE = "R";
	public static final int COMMAND_ARGUMENT_LENGTH = 5;

	private final DrawingContext drawingContext;
	private final DrawableRectangleConfig drawableRectangleConfig;

	public DrawRectangleCommandParser(String commandType, DrawingContext drawingContext, DrawableRectangleConfig drawableRectangleConfig) {
		super(commandType);	
		
		if (drawingContext == null) {
			throw new IllegalArgumentException("drawingContext should not be null");
		}
		
		if (drawableRectangleConfig == null) {
			throw new IllegalArgumentException("drawableRectangleConfig should not be null");
		}
		
		this.drawingContext = drawingContext;
		this.drawableRectangleConfig = drawableRectangleConfig;
	}

	public DrawRectangleCommandParser(DrawingContext drawingContext) {
		this(COMMAND_TYPE, drawingContext, new DrawableRectangleConfig());	
	}
 
	@Override
	protected DrawRectangleCommand parse(String commandText, FieldSet fieldSet) throws CommandParsingException {
		String type = fieldSet.readString(0);
		
		if (!isAcceptableCommandType(type))
			return null;
		
		if (fieldSet.getFieldCount() != COMMAND_ARGUMENT_LENGTH)
			throw new IncorrectNumberOfCommandArgumentException("Too many or few command arguments: " + commandText);
		
		int x1 = fieldSet.readInt(1);
		int y1 = fieldSet.readInt(2);
		int x2 = fieldSet.readInt(3);
		int y2 = fieldSet.readInt(4);
		
		Point start = new Point(x1, y1);
		Point end = new Point(x2, y2);
		
		DrawableRectangle rectangle = new DrawableRectangle(this.drawableRectangleConfig, start, end);
		
		return new DrawRectangleCommand(this.drawingContext, rectangle);
	}
}
