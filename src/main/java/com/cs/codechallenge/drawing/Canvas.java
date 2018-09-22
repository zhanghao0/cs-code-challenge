package com.cs.codechallenge.drawing;

import java.util.ArrayList;
import java.util.List;

import com.cs.codechallenge.geometry.Bounds;
import com.cs.codechallenge.geometry.Dimension;
import com.cs.codechallenge.geometry.Point;

public class Canvas {
	private static final Point ORIGIN = new Point(1, 1);
	private final CanvasConfig canvasConfig;
	
	private final Dimension dimension;
	private final Dimension outerDimension;
	
	private GraphicsDevice graphicsDevice = new ConsoleTerminal();

	private final List<DrawableComponent> components = new ArrayList<>();
	private final Bounds bounds;
	
	private final char[][] cells;
	
	public Canvas(CanvasConfig canvasConfig, Dimension dimension) {
		super();
		if (canvasConfig == null) {
			throw new IllegalArgumentException("canvasConfig should not be null");
		}
		
		if (dimension == null) {
			throw new IllegalArgumentException("dimension should not be null");
		}
		
		if (dimension.getWidth() <= 0 || dimension.getWidth() > canvasConfig.getMaxWidth())
			throw new IllegalArgumentException("Invalid canvas width: " + dimension.getWidth());
		
		if (dimension.getHeight() <= 0 || dimension.getHeight() > canvasConfig.getMaxHeight())
			throw new IllegalArgumentException("Invalid canvas height: " + dimension.getHeight());
		
		this.canvasConfig = canvasConfig;
		this.dimension = dimension;
		
		this.outerDimension = new Dimension(this.dimension.getWidth() + 2, this.dimension.getHeight() + 2);
		this.bounds = new Bounds(ORIGIN, this.dimension);
		this.cells = new char[outerDimension.getHeight()][outerDimension.getWidth()];
	}

	public void setGraphicsDevice(GraphicsDevice graphicsDevice) {
		if (graphicsDevice != null) {
			this.graphicsDevice = graphicsDevice;
		}
	}

	public void addDrawableComponent(DrawableComponent component) throws Exception {
		if (component == null) {
			throw new IllegalArgumentException("component should not be null");
		}
		
		if (!this.components.contains(component)) {
			if (!this.bounds.contains(component.getBounds())) {
				throw new InvalidDrawableComponentException("Component should be within the canvas");
			}
			this.components.add(component);
		}
	}

	
	public void update(int row, int column, char value) {
		cells[row][column] = value;
	}
	
	public char get(int row, int column) {
		return cells[row][column];
	}
	
	public Bounds getBounds() {
		return this.bounds;
	}
	
	public int getComponentCount() {
		return this.components.size();
	}

	public void draw() {
		this.drawSelf();
		
		this.components.forEach(component -> component.draw(this));
		
		this.drawOnDevice();
	}

	private void drawSelf() {
		for (int j = 0; j < this.outerDimension.getHeight(); j++) {
			for (int i = 0; i < this.outerDimension.getWidth(); i++) {
				if (j == 0 || j == this.outerDimension.getHeight() - 1) {
					this.update(j, i, canvasConfig.getHorizontalCharacter());
				} else if (i == 0 || i == this.outerDimension.getWidth() - 1) {
					this.update(j, i, canvasConfig.getVerticalCharacter());
				} else {
					this.update(j, i, canvasConfig.getCellCharacter());
				}
			}
		}
	}

	private void drawOnDevice() {		
		for (int j = 0; j < this.outerDimension.getHeight(); j++) {
			for (int i = 0; i < this.outerDimension.getWidth(); i++) {
				this.graphicsDevice.draw(cells[j][i]);
			}
			this.graphicsDevice.goToNextLine();
		}
	}
}
