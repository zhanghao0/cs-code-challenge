package com.cs.codechallenge;

import java.util.Arrays;

import com.cs.codechallenge.command.Command;
import com.cs.codechallenge.drawing.DrawingContext;
import com.cs.codechallenge.drawing.SimpleDrawingContext;
import com.cs.codechallenge.io.reader.ItemReader;
import com.cs.codechallenge.io.reader.SimpleItemReader;
import com.cs.codechallenge.parser.BucketFillCommandParser;
import com.cs.codechallenge.parser.CommandParser;
import com.cs.codechallenge.parser.CompositeCommandParser;
import com.cs.codechallenge.parser.DrawCanvasCommandParser;
import com.cs.codechallenge.parser.DrawLineCommandParser;
import com.cs.codechallenge.parser.DrawRectangleCommandParser;
import com.cs.codechallenge.parser.QuitCommandParser;

public class Application implements ExitApplicationListener {
	private CommandParser<? extends Command> commandParser;
	private ItemReader<String> reader;
	
	private boolean running = true;
	
	private int executedCommands = 0;

	public void setCommandParser(CommandParser<? extends Command> commandParser) {
		this.commandParser = commandParser;
	}

	public void setReader(ItemReader<String> reader) {
		this.reader = reader;
	}

	public void run() throws Exception {	
		if (commandParser == null) {
			throw new IllegalArgumentException("commandParser should not be null");
		}
		
		if (reader == null) {
			throw new IllegalArgumentException("reader should not be null");
		}
		
		try(
			ItemReader<String> reader = this.reader;
		) {
			reader.open();
			
			while (isRunning()) {
				Command command = null;

				System.out.print("enter command: ");
				
				String commmandText = reader.read();
				
				try {
					command = commandParser.parse(commmandText);
					
					if (command == null) 
						break;
					
					command.execute();
					
					this.executedCommands++;
				} catch (Exception exception) {
					System.err.println(exception.getMessage());
					//sleep to ensure console buffer is flushed
					Thread.sleep(200);
					continue;
				}
			}
		}
	}

	@Override
	public void onExit() {
		this.running = false;
	}

	private boolean isRunning() {
		return running;
	}

	public int getExecutedCommands() {
		return executedCommands;
	}

	public static void main(String[] args) throws Exception {
		DrawingContext drawingContext = new SimpleDrawingContext();
		
		Application application = new Application();
		
		CommandParser<? extends Command> commandParser = new CompositeCommandParser(
			Arrays.asList(
				new QuitCommandParser(application),
				new DrawCanvasCommandParser(drawingContext),
				new DrawLineCommandParser(drawingContext),
				new DrawRectangleCommandParser(drawingContext),
				new BucketFillCommandParser(drawingContext)
			)
		);
		
		ItemReader<String> reader = new SimpleItemReader();
		
		application.setCommandParser(commandParser);
		application.setReader(reader);
		
		application.run();
	}
}
