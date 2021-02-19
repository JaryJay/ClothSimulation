package phsyics;

import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;

public class ClothContainer {

	public static final Vector2f GRAVITY = new Vector2f(0, 0.2f);

	List<Node> nodes = new ArrayList<>();
	List<Constraint> constraints = new ArrayList<>();

	public ClothContainer() {
		addGrid(20, 45, 150, 200);
		addGrid(19, 41, 350, 300);
		addGrid(18, 37, 550, 400);
		addGrid(17, 33, 750, 500);
	}

	public void addGrid(int numOfRows, int numOfColumns, int x, int y) {
		Node[][] grid = new Node[numOfRows][numOfColumns];
		for (int i = 0; i < numOfRows; i++) {
			for (int j = 0; j < numOfColumns; j++) {
				Node node = new Node(j * Constraint.TARGET_LENGTH + x, i * Constraint.TARGET_LENGTH + y);
				grid[i][j] = node;
				nodes.add(node);
				if (i == 0) {
					node.setFixed(true);
				}
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
		for (Node n : nodes) {
			Vector2f wind = new Vector2f((float) ((Math.random() - 0.3F) * 0.05F), 0);
			n.applyForce(GRAVITY);
			n.applyForce(wind);
			n.applyVelocity();
			n.shrinkVelocity();
		}
	}

	public void display(PApplet p) {
//		displayNodes(p);
		displayConstraints(p);
	}

	private void displayConstraints(PApplet p) {
		p.stroke(255);
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
			Vector2f nToCenter = center.copy().subtract(n.getPos());
//			float lengthSquared = nToCenter.lengthSquared() + 0.001F;
			n.applyForce(nToCenter.normalize().scale(0.7F));
		}
	}

}
