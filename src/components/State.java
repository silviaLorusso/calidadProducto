package components;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import movements.Action;

public class State {

	private int[][] field;
	private int k;
	private int max;
	private int sizeCol;
	private int sizeRow;
	private Tractor tractor;
	
	

	public State(int[][] field, int k, int max, int sizeCol, int sizeRow, Tractor tractor) {
		this.field = field;
		this.k = k;
		this.max = max;
		this.sizeCol = sizeCol;
		this.sizeRow = sizeRow;
		this.tractor = tractor;
	}
	
	
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return new State(getMatrix(), k, max, sizeCol, sizeRow,getTractor());
	}

	public State(String path) {
		this.tractor = new Tractor();
		try {
			readField(path);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public State(State state) {
		this.field = state.field;
		this.k = state.k;
		this.max = state.max;
		this.sizeCol = state.sizeCol;
		this.sizeRow = state.sizeRow;
		this.tractor = state.tractor;
	}

	public int getSandMoving() {
		return getValue(this.tractor.getPosition()) - this.k;
	}

	public static State newState(State previousState, Action action) throws CloneNotSupportedException {
		State state = new State(previousState);
		upgradeState(state, action);
		return state;
	}

	public static void upgradeState(State state, Action action) throws CloneNotSupportedException {

		for (Position p : action.getSandMovement().keySet()) {
			int sum = state.getValue(p) + action.getSandMovement().get(p);
			System.out.println(p.toString() + "-" + state.getValue(p));
			state.setValue(p, sum);
			System.out.println(p.toString() + "-" + sum);
		}

		state.setValue(state.getTractor().getPosition(), state.getK());
		/*
		 * effettuato lo spostamento il trattore prende la sabbia dalla nuova posizione,
		 * la vecchia ha la sabbia che doveva essere spostata
		 */
		Tractor tractor = state.getTractor();
		tractor.setPosition(action.getNextPosition());
		state.setTractor(tractor);
	}

	public void readField(String path) throws IOException {
		FileReader file = new FileReader(path);
		Scanner inputStream = new Scanner(file);

		String items = inputStream.nextLine(); // first line contens x,y,nextsand,maxSand,row,columns
		String[] array = items.split(" ");
		this.tractor.setPosition(new Position(Integer.parseInt(array[0]), Integer.parseInt(array[1])));
		this.k = Integer.parseInt(array[2]);
		max = Integer.parseInt(array[3]);
		this.sizeCol = Integer.parseInt(array[4]);
		this.sizeRow = Integer.parseInt(array[5]);
		this.field = new int[sizeCol][sizeRow];

		// matrix filling
		int row = 0;
		int column;
		while (inputStream.hasNextLine()) {
			items = inputStream.nextLine();
			items = items.trim();
			array = items.split(" ");

			for (column = 0; column < sizeCol; column++) {
				Position parser = new Position(column, row);
				this.setValue(parser, Integer.parseInt(array[column]));
			}
			row++;
		}

		this.tractor.setLimit(sizeCol);

		inputStream.close();
		file.close();

	}

	public void printFild(String path) throws FileNotFoundException {
		File file = new File(path);
		PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream(file)));

		out.println(tractor.getPosition().getY() + "\b" + tractor.getPosition().getX() + "\b" + this.k + "\b" + max
				+ "\b" + sizeCol + "\b" + sizeRow);

		for (int row = 0; row < sizeRow; row++) {
			out.print("\b");
			for (int col = 0; col < sizeCol; col++) {
				Position p = new Position(col, row);
				out.print(this.getValue(p) + "\b");
			}
			out.println();
		}
		out.close();
	}

	public void setValue(Position p, int num) {
		field[p.getY()][p.getX()] = num;
	}

	public int getValue(Position p) {
		return field[p.getY()][p.getX()];
	}

	public void setMatrix(int[][] matrix) {
		this.field = matrix;
	}

	public int[][] getMatrix() {
		int[][] matrix=new int[sizeCol][sizeRow];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				matrix[j][i]=this.field[j][i];
			}
		}
		return matrix;
	}

	public void setTractor(Tractor tractor) {
		this.tractor = tractor;
	}

	public Tractor getTractor() throws CloneNotSupportedException {
		return (Tractor) tractor.clone();
	}

	public void setSizeCol(int sizeCol) {
		this.sizeCol = sizeCol;
	}

	public int getSizeCol() {
		return sizeCol;
	}

	public void setSizeRow(int sizeRow) {
		this.sizeRow = sizeRow;
	}

	public int getSizeRow() {
		return sizeRow;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getMax() {
		return max;
	}

	public void setK(int k) {
		this.k = k;
	}

	public int getK() {
		return k;
	}

	@Override
	public String toString() {
		return "(" + this.tractor + " " + this.k + " " + this.k + " " + this.sizeCol + " " + this.sizeRow + ")";
	}
}
