package com.voodoo.gameoflife;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 * branch1
 */
public class Cell extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9150145006502273739L;

	private boolean alive = false;
	
	private boolean nextAliveState = false;
	
	private List<Cell> neightbor = new ArrayList<Cell>();

	private int row;
	private int col;
		
	public Cell(int row, int col) {
		setBorder(new LineBorder(Color.BLACK, 1));
		this.row = row;
		this.col = col;
		setToolTipText("");
	}
	
	
	public int getRow() {
		return row;
	}


	public int getCol() {
		return col;
	}


	public boolean isNeighbor(Cell cell) {
		return Math.abs(row - cell.getRow()) <= 1 && Math.abs(col - cell.getCol()) <= 1;
	}
		
	public void addNeightBorIfNeeded(Cell cell) {
		if (cell != this && isNeighbor(cell)) {
			this.neightbor.add(cell);
		}
	}

	public void die() {
		alive = false;
		repaint();
	}

	public void live() {
		alive = true;
		repaint();
	}

	public boolean isAlive() {
		return alive;
	}



	public int getNeighborAliveCount() {
		int ncount = 0;
		for (Cell cell : neightbor ) {
			if (cell.isAlive()) {
				ncount++;
			}
		}
		return ncount;
	}
	
	


	public boolean isNextAliveState() {
		return nextAliveState;
	}


	public void setNextAliveState(boolean nextAliveState) {
		this.nextAliveState = nextAliveState;
	}

	public void applyNextState() {
		this.alive = nextAliveState;
		this.repaint();
	}

	@Override
	public Color getBackground() {
		if (isAlive()) {
			return Color.BLACK;
		} else {
			return Color.WHITE;
		}
	}
	
	@Override
	public String getToolTipText(MouseEvent event) {
		return String.valueOf(getNeighborAliveCount());
	}


}
