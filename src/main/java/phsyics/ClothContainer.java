package phsyics;

import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;

public class ClothContainer {

	List<Node> nodes = new ArrayList<>();
	List<Constraint> constraints = new ArrayList<>();
	Node[][] grid;

	public ClothContainer() {
		int numOfRows = 20;
		int numOfColumns = 50;
		grid = new Node[numOfRows][numOfColumns];
		addGrid(numOfRows, numOfColumns, Constraint.TARGET_LENGTH);
		for (int i = 0; i < numOfColumns; i++) {
			grid[0][i].setFixed(true);
		}
	}

	public void addGrid(int numOfRows, int numOfColumns, float spacing) {
		for (int i = 0; i < numOfRows; i++) {
			for (int j = 0; j < numOfColumns; j++) {
				Node node = new Node(j * spacing + 500, i * spacing + 200);
				grid[i][j] = node;
				nodes.add(node);
			}
		}
		for (int i = 0; i < numOfRows; i++) {
			for (int j = 0; j < numOfColumns; j++) {
				if (i + 1 < numOfRows) {
					constraints.add(new Constraint(grid[i][j], grid[i + 1][j]));
				}
				if (j + 1 < numOfColumns) {
					constraints.add(new Constraint(grid[i][j], grid[i][j + 1]));
				}
			}
		}
	}

	public void update() {
		for (int i = 0; i < constraints.size(); i++) {
			Constraint c = constraints.get(i);
			c.update();
		}
		Vector2f gravity = new Vector2f(0, 0.2f);
		for (Node n : nodes) {
			n.applyForce(gravity);
			n.applyVelocity();
			n.shrinkVelocity();
		}
	}

	public void display(PApplet p) {
//		displayNodes(p);
		displayConstraints(p);
	}

	private void displayConstraints(PApplet p) {
		p.fill(0);
		for (int i = 0; i < constraints.size(); i++) {
			Constraint c = constraints.get(i);
			Vector2f n1Pos = c.getN1().getPos();
			Vector2f n2Pos = c.getN2().getPos();
			p.line(n1Pos.x, n1Pos.y, n2Pos.x, n2Pos.y);
		}
	}

	public void suckToPoint(int mouseX, int mouseY) {
		Vector2f center = new Vector2f(mouseX, mouseY);
		for (Node n : nodes) {
			n.applyForce(center.copy().subtract(n.getPos()).normalize().scale(1));
		}
	}

}
