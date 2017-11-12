package movements;

import java.util.HashMap;

import components.Position;

public class Action {

	private Position nextPosition;
	private HashMap<Position, Integer> sandMovement;

	public Action(Position next) {
		this.nextPosition = next;
		this.sandMovement = new HashMap<>();
	}
	
	public Position getNextPosition() {
		return nextPosition;
	}
	
	public void setNextPosition(Position nextPosition) {
		this.nextPosition = nextPosition;
	}
	
	public HashMap<Position, Integer> getSandMovement() {
		return sandMovement;
	}
	
	public void setSandMovement(HashMap<Position, Integer> sandMovement) {
		this.sandMovement = sandMovement;
	}
	
	public void addElement(Position p, Integer i) {
		this.sandMovement.put(p, i);
	}
	
	public StringBuilder toString() {
		StringBuilder sb = new StringBuilder();
		ref = sb.append("(").append(this.nextPosition).append(") [");
		
		for(Position p:sandMovement.keySet()) {
			ref= sb.append(sandMovement.get(p).toString()).append(p.toString()).append(",");
		}
		ref= sb.append("1]");
		return ref;
	}
	
	
		
		
		
}
