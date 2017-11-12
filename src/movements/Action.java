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
		String ref="";
		ref+="("+this.nextPosition+") [";
		
		for(Position p:sandMovement.keySet()) {
			ref+=sandMovement.get(p).toString()+p.toString()+",";
		}
		ref+="1]";
		return ref;
	}
}
