package com.voodoo.gameoflife;

/**
 * représente dieu
 * 
 */
public class God { 
	private World world;

	public God(World world) {
		this.world = world;
	}
	
	
	public void killAllCells() {
		for (Cell cell : world.getAllCells()) {
			cell.die();
		}
	}


	public void generateNextGeneration() {
		 int row;
		 int col;
		
		for(row=1; row<world.cells.length-1; row++){				//loops through the board and applies rules of the game
			for(col=1; col< world.cells[row].length-1; col++){
				if(world.cells[row][col].isAlive()){
					if (world.cells[row][col].getNeighborAliveCount() < 2){				//if its alive and has less then 2 alive neighbors
						world.cells[row][col].die();
					}

					if (world.cells[row][col].getNeighborAliveCount() > 3){				//if its alive and has more than 3 neighbors
						world.cells[row][col].die();
					}
				}
				else {		
					if (world.cells[row][col].getNeighborAliveCount() == 3){				//if its dead and has exactly 3 neighbors
						world.cells[row][col].live();
					}
				}
			}
		}//end for
	}

	
}
