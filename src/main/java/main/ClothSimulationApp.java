package main;

import processing.core.PApplet;

public class ClothSimulationApp {

	public static void main(String[] args) {
		String[] processingArgs = { "Cloth Simulation" };
		ClothSimulationSketch mySketch = new ClothSimulationSketch();
		PApplet.runSketch(processingArgs, mySketch);
	}

}
