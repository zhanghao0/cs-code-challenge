package com.cs.codechallenge.parser;

import com.cs.codechallenge.command.BucketFillCommand;
import com.cs.codechallenge.drawing.BucketFill;
import com.cs.codechallenge.drawing.DrawingContext;
import com.cs.codechallenge.geometry.Point;
import com.cs.codechallenge.io.FieldSet;

public class BucketFillCommandParser extends AbstractCommandParser<BucketFillCommand> {
	public static final String COMMAND_TYPE = "B";
	public static final int COMMAND_ARGUMENT_LENGTH = 4;

	private final DrawingContext drawingContext;

	public BucketFillCommandParser(String commandType, DrawingContext drawingContext) {
		super(commandType);	
		
		if (drawingContext == null) {
			throw new IllegalArgumentException("drawingContext should not be null");
		}
		
		this.drawingContext = drawingContext;
	}

	public BucketFillCommandParser(DrawingContext drawingContext) {
		this(COMMAND_TYPE, drawingContext);	
	}
 
	@Override
	protected BucketFillCommand parse(String commandText, FieldSet fieldSet) throws CommandParsingException {
		String type = fieldSet.readString(0);
		
		if (!isAcceptableCommandType(type))
			return null;
		
		if (fieldSet.getFieldCount() != COMMAND_ARGUMENT_LENGTH)
			throw new IncorrectNumberOfCommandArgumentException("Too many or few command arguments: " + commandText);
		
		int x = fieldSet.readInt(1);
		int y = fieldSet.readInt(2);
		char ch = fieldSet.readChar(3);
		
		Point point = new Point(x, y);

		BucketFill bucketFill = new BucketFill(point, ch);
		
		return new BucketFillCommand(this.drawingContext, bucketFill);
	}
}
