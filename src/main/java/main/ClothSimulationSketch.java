package main;

import phsyics.ClothContainer;
import processing.core.PApplet;

public class ClothSimulationSketch extends PApplet {

	private ClothContainer clothContainer = new ClothContainer();
	boolean followMouse = false;

	public ClothSimulationSketch() {

	}

	@Override
	public void settings() {
		size(1920, 1080, P2D);
	}

	@Override
	public void setup() {
	}

	@Override
	public void draw() {
		background(255);
		clothContainer.update();
		if (followMouse)
			clothContainer.suckToPoint(mouseX, mouseY);
		clothContainer.display(this);
	}

	@Override
	public void mousePressed() {
		followMouse = true;
	}

	@Override
	public void mouseReleased() {
		followMouse = false;
	}

}
