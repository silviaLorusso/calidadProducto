package tree;

import java.util.Random;

import components.State;
import movements.Action;

public class Node implements Comparable<Node> {

	private Node parent;
	private State state;
	private int cost;
	private Action action;
	private int depth;
	private int value;
	private Random random = new Random();

	public Node(State rootState) {
		this.parent = null;
		this.state = rootState;
		this.action = null;
		this.depth = 0;
		this.cost = 0;
		this.value = random.nextInt(1) + 100;

	}

	public Node(Node parentNode, Action nextAction) throws CloneNotSupportedException {
		this.state = State.newState(parentNode.getState(), nextAction);
		this.action = nextAction;
		this.parent = parentNode;
		this.cost = parentNode.getCost() + 1;
		this.depth = parentNode.getDepth() + 1;
		this.value = random.nextInt(1) + 100;
	}

	public Node(Node parent, State state, int cost, Action action, int depth, int value, Random random) {
		super();
		this.parent = parent;
		this.state = state;
		this.cost = cost;
		this.action = action;
		this.depth = depth;
		this.value = value;
		this.random = random;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return new Node(this.parent, (State) this.state.clone(), this.cost, this.action, this.depth, this.value,
				this.random);
	}

	@Override
	public int compareTo(Node n) {
		return ((Integer) this.value).compareTo((Integer) n.value);
	}

}
