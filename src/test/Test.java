package test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;

import components.State;
import components.Tractor;
import tree.Frontier;
import tree.Node;
import tree.Successor;

public class Test {

	public static void main(String[] args) {
		State stato = new State("Test.0.txt");
		try {
			stato.printFild("Generato.0.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Successor successor = new Successor(stato);
		int i = 1;
		try {
			for (Node n : successor.newSuccessors()) {
				try {
					n.getState().printFild("Generato." + i + ".txt");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				i++;
			}
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * public static void main(String[] args) { State stato = new
	 * State("Test.0.txt"); Successor successor = new Successor(stato); Frontier
	 * frontier = new Frontier(); frontier.createFrontier(); ArrayList<Node>
	 * sucNodes = null; try { sucNodes = successor.newSuccessors(); } catch
	 * (CloneNotSupportedException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } // TIME 1 for (Node node : sucNodes) {
	 * frontier.insert(node); } //TIME 2 }
	 */
}
