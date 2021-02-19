package phsyics;

public class Node {

	private boolean fixed;

	private Vector2f pos;
	private Vector2f velocity;

	public Node(Vector2f pos, boolean fixed) {
		this.pos = pos;
		this.fixed = fixed;
		velocity = new Vector2f(0, 0);
	}

	public Node(float x, float y) {
		this(new Vector2f(x, y), false);
	}

	public Node(float x, float y, boolean fixed) {
		this(new Vector2f(x, y), fixed);
	}

	public void clearVelocity() {
		velocity.set(0, 0);
	}

	public void applyForce(Vector2f force) {
		if (!fixed)
			velocity.add(force);
	}

	public void applyVelocity() {
		pos.add(velocity);
		if (pos.y > 1000) {
			pos.y = 1000;
			velocity.y = 0;
			applyForce(ClothContainer.GRAVITY.copy().scale(-1));
		}
	}

	public Vector2f getPos() {
		return pos;
	}

	public void setPos(Vector2f pos) {
		this.pos = pos;
	}

	public boolean isFixed() {
		return fixed;
	}

	public void setFixed(boolean fixed) {
		this.fixed = fixed;
	}

	public void shrinkVelocity() {
		this.velocity.scale(0.98F);
	}

}
