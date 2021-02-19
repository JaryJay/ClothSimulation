package phsyics;

public class Constraint {

	public static final float TARGET_LENGTH = 15L;

	private Node n1;
	private Node n2;

	public Constraint(Node n1, Node n2) {
		this.n1 = n1;
		this.n2 = n2;
	}

	public void update() {
		float lengthSquared = getLengthSquared();
		float extraDist = lengthSquared - TARGET_LENGTH * TARGET_LENGTH;
		if (extraDist > 0) {
			extraDist = extraDist / (TARGET_LENGTH * 10);
//			extraDist = (float) Math.sqrt(extraDist) / 50.0F;
			Vector2f n1ToN2 = n2.getPos().copy().subtract(n1.getPos());
			n1.applyForce(n1ToN2.copy().normalize().scale(extraDist));
			n2.applyForce(n1ToN2.copy().normalize().scale(-extraDist));
		}
	}

	public float getLengthSquared() {
		return n1.getPos().copy().subtract(n2.getPos()).lengthSquared();
	}

	public Node getN1() {
		return n1;
	}

	public void setN1(Node n1) {
		this.n1 = n1;
	}

	public Node getN2() {
		return n2;
	}

	public void setN2(Node n2) {
		this.n2 = n2;
	}

}
