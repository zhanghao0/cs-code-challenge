package com.cs.codechallenge.command;

import com.cs.codechallenge.ExitApplicationListener;

public class QuitCommand implements Command {
	private final ExitApplicationListener listener;
	
	public QuitCommand(ExitApplicationListener listener) {
		super();
		
		if (listener == null) {
			throw new IllegalArgumentException("listener should not be null");
		}
		
		this.listener = listener;
	}

	@Override
	public void execute() {
		System.out.println("Quit the program");
		
		this.listener.onExit();
	}
	
}
