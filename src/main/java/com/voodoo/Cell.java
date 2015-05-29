package com.voodoo;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Cell extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9150145006502273739L;

	private boolean alive = false;
	
	private List<Cell> neightbor = new ArrayList<Cell>();

	private int row;
	private int col;
		
	public Cell(int row, int col) {
		setBorder(new LineBorder(Color.BLACK, 1));
		this.row = row;
		this.col = col;
		setToolTipText("test");
	}
	
	
	public int getRow() {
		return row;
	}


	public int getCol() {
		return col;
	}


	public void addNeightBorIfNeeded(Cell cell) {
		if (cell != this && Math.abs(cell.getRow() - row) <= 1 && Math.abs(cell.getCol() - col) <=1 ) {
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



	public int getNcount() {
		int ncount = 0;
		for (Cell cell : neightbor) {
			if (cell.isAlive()) {
				ncount++;
			}
		}
		return ncount;
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
		return String.valueOf(getNcount());
	}


}
