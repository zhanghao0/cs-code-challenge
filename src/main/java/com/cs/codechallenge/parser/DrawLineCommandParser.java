package com.cs.codechallenge.parser;

import com.cs.codechallenge.command.DrawLineCommand;
import com.cs.codechallenge.drawing.DrawableLine;
import com.cs.codechallenge.drawing.DrawableLineConfig;
import com.cs.codechallenge.drawing.DrawingContext;
import com.cs.codechallenge.geometry.Point;
import com.cs.codechallenge.io.FieldSet;

public class DrawLineCommandParser extends AbstractCommandParser<DrawLineCommand> {
	public static final String COMMAND_TYPE = "L";
	public static final int COMMAND_ARGUMENT_LENGTH = 5;

	private final DrawingContext drawingContext;
	private final DrawableLineConfig drawableLineConfig;

	public DrawLineCommandParser(String commandType, DrawingContext drawingContext, DrawableLineConfig drawableLineConfig) {
		super(commandType);	
		
		if (drawingContext == null) {
			throw new IllegalArgumentException("drawingContext should not be null");
		}
		
		if (drawableLineConfig == null) {
			throw new IllegalArgumentException("drawableLineConfig should not be null");
		}
		
		this.drawingContext = drawingContext;
		this.drawableLineConfig = drawableLineConfig;
	}

	public DrawLineCommandParser(DrawingContext drawingContext) {
		this(COMMAND_TYPE, drawingContext, new DrawableLineConfig());	
	}
 
	@Override
	protected DrawLineCommand parse(String commandText, FieldSet fieldSet) throws CommandParsingException {
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
		
		DrawableLine line = new DrawableLine(this.drawableLineConfig, start, end);
		
		return new DrawLineCommand(this.drawingContext, line);
	}
}
