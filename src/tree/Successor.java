package tree;

import java.util.ArrayList;

import components.*;
import movements.Action;
import movements.Partition;

public class Successor {

	private Node parentNode;

	public Successor(State state) {
		parentNode = new Node(state);
	}

	public ArrayList<Node> newSuccessors() throws CloneNotSupportedException {

		ArrayList<Action> actions = generateActions(parentNode.getState());
		ArrayList<Node> successors = new ArrayList<Node>();

		Node auxNode = ((Node) parentNode.clone());
		for (Action action : actions) {
			Node newNode = new Node(auxNode, action);
			successors.add(newNode);
			auxNode = ((Node) parentNode.clone());
		}
		return successors;
	}

	private int[] trasformStringArray(int size, String trasformed) {
		int[] array = new int[size];

		String[] stringhe = trasformed.split("");

		for (int i = 0; i < stringhe.length; i++) {
			array[i] = Integer.parseInt(stringhe[i]);
		}
		return array;
	}

	private ArrayList<Action> generateActions(State state) throws CloneNotSupportedException {
		ArrayList<Position> nextPosition;
		ArrayList<Action> actions = new ArrayList<Action>();
		nextPosition = state.getTractor().getMovements();
		ArrayList<String> possibleSand =Partition.generationSand(state.getSandMoving(),nextPosition.size());
		int[] sandInPoint = new int[nextPosition.size()];

		for (Position indexP : nextPosition) { // per all possible positions we can have possible action (sand
												// moving + next position)
			int count = 0;
			while (count != possibleSand.size()) {
				for (String sandDistribution : possibleSand) {

					Action action = new Action(indexP); // create a new action

					sandInPoint = trasformStringArray(sandDistribution.length(), sandDistribution);

					boolean isIn = true; // if action pass control can be added to all possible action
					int i = 0;
					for (Position indexI : nextPosition) {

						action.addElement(indexI, sandInPoint[i]); // add action to the possible actions

						if (sandInPoint[i] > (state.getMax() - state.getValue(indexI)))
							isIn = false;
						i++;
					}
					if (isIn == true) { // control
						actions.add(action);
						System.out.println(action.toString()); // test to can see all action
					}
					count++;
				}
			}
			System.out.println();
		}
		return actions;
	}

}
