

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 *Life Class that simulates the game of life on a JFrame 
 * @author anuccio
 *
 */
public class Life extends JFrame {
/** container that gets the content pane */
	private Container j;
	/** an array of jpanels */
	private JPanel[][] Jpanelarray;
	/**stores the neighbor count for each element*/
	private int[][] ncount;
	/** stores the alive status */
	private boolean[][] background;
	private int row,col;
	/**sees if the user is clicking or not*/
	private boolean candrag = false;
	/**boolean value */
	private boolean toggle = false;


/**
 * Constructor that initializes the JFrame
 */
	public Life(){			//constructor initiates GUI

		super("Game of Life");

		Jpanelarray = new JPanel[30][40];
		background = new boolean[32][42];
		ncount = new int[30][40];


		j = getContentPane();				
		setBackground(Color.WHITE);												//sets background of jframe
		setLayout(new GridLayout(30,40));										//makes a 30x40 board

		MouseHandler handler = new MouseHandler();	//initializes handlers
		KeyHandler keyhandler = new KeyHandler();

		this.addKeyListener(keyhandler);	//adds a keylistener to the jframe

		for(row=0; row<Jpanelarray.length; row++){
			for(col=0; col< Jpanelarray[row].length; col++){
				Jpanelarray[row][col] = new JPanel();									//adds jpanels for each individual location
				Jpanelarray[row][col].setBorder(new LineBorder(Color.BLACK,1));
				Jpanelarray[row][col].setBackground(Color.WHITE);
				j.add(Jpanelarray[row][col]);	
				Jpanelarray[row][col].addMouseListener(handler);
				Jpanelarray[row][col].addMouseMotionListener(handler);

			}//end inner for
		}//end outer for

		
		for(int i=0; i<background.length;i++){						//sets all the values to dead
			for(int j=0; j<background[i].length; j++){
				background[i][j] = false;
			}
		}

	}//end constructor

/**
 * Private mouse handler class that uses the mouse adapter
 * @author anuccio
 *
 */
	private class MouseHandler extends MouseAdapter	{

/**
 * if mouse is pressed
 */
		public void mousePressed(MouseEvent event){
			candrag = true;						//is true when the user is holding down a click
			
			if(event.getComponent().getBackground().equals(Color.WHITE)){		//if user clicks on a white square
				event.getComponent().setBackground(Color.BLACK);				//sets the white square to black
			}
			else {
				event.getComponent().setBackground(Color.WHITE);				//sets black square to white
			}

		}
/**
 * if mouse is released
 */
		public void mouseReleased(MouseEvent event){
			candrag = false;									//makes it false when click is released
		}

/**
 * if mouse is clicked
 */
		public void mouseClicked(MouseEvent event){
			if(event.getComponent().getBackground().equals(Color.WHITE)){	
				event.getComponent().setBackground(Color.WHITE);
			}
			else {
				event.getComponent().setBackground(Color.BLACK);
			}


		}
/**
 * if mouse enters the panel
 */
		public void mouseEntered(MouseEvent event){
			if(candrag){

				if(event.getComponent().getBackground().equals(Color.WHITE)){	//if the user is holding down the click and enters a new square, it changes the background
					event.getComponent().setBackground(Color.BLACK);
				}
				else {
					event.getComponent().setBackground(Color.WHITE);
				}


			}

		}

	}//end private class
/**
 * private key handler class that starts and stops the game of life
 * @author anuccio
 *
 */
	private class KeyHandler extends KeyAdapter {		//begins the animation when user enters the a key

		public void keyPressed(KeyEvent event){

			if(event.getKeyChar() == 'a'){	
				toggle = !toggle;		
				if (toggle){
					BeginGame();
				}

			}//end if
		}

	}


/**
 * This starts the game
 */
	public void BeginGame(){

		getAlive();		//gets the alive status of each elements
		
		for(row=1; row<background.length-1; row++){				//loops through the board and applies rules of the game
			for(col=1; col< background[row].length-1; col++){
				if(background[row][col] == true){
					if (ncount[row-1][col-1] < 2){				//if its alive and has less then 2 alive neighbors
						background[row][col] = false;
					}

					if (ncount[row-1][col-1] > 3){				//if its alive and has more than 3 neighbors
						background[row][col] = false;
					}
				}
				else {		
					if (ncount[row-1][col-1] == 3){				//if its dead and has exactly 3 neighbors
						background[row][col] = true;
					}
				}
			}
		}//end for
	
		repaint();		//repaints the board



	}//end BeginGame()

/**
 * end the game, clears the screen
 */
	public void EndGame(){

		for(row=1; row<background.length-1; row++){
			for(col=1; col< background[row].length-1; col++){
				background[row][col] = false;
				Jpanelarray[row-1][col-1].setBackground(Color.WHITE);
			}
		}
	}
/**
 * Sets the amount of alive neighbors for each element
 */
	public void setNeighborCount(){

		for(int i=0; i<ncount.length;i++){
			for(int j=0; j<ncount[i].length; j++){
				ncount[i][j] = 0;
			}
		}

		for(row=1; row<background.length-1; row++){			//sets the neighbor count
			for(col=1; col< background[row].length-1; col++){

				if(background[row-1][col]==true){
					ncount[row-1][col-1]++;
				}

				if(background[row-1][col-1]==true){
					ncount[row-1][col-1]++;
				}
				if(background[row-1][col+1]==true){
					ncount[row-1][col-1]++;
				}
				if(background[row][col-1]==true){
					ncount[row-1][col-1]++;
				}
				if(background[row][col+1]==true){
					ncount[row-1][col-1]++;
				}
				if(background[row+1][col]==true){
					ncount[row-1][col-1]++;
				}
				if(background[row+1][col+1]==true){
					ncount[row-1][col-1]++;
				}
				if(background[row+1][col-1]==true){
					ncount[row-1][col-1]++;
				}


			}
		}//end ncount for
	}
/**
 * gets the alive status for the board
 */
	public void getAlive(){	

		for(int i=1; i<background.length-1;i++){
			for(int j=1; j<background[i].length-1; j++){

				if (Jpanelarray[i-1][j-1].getBackground().equals(Color.BLACK)){
					background[i][j] = true;
				}
				else {
					background[i][j] = false;
				}

			}
		}//end for
		setNeighborCount();	//sets neighborcount

	}
/**
 * This is called when the program calls repaint()
 */
	public void paint(Graphics g){

		
		super.paint(g);
		
		try {									//waits 500 ms before repainting
			Thread.sleep(500);
		} catch (InterruptedException e) {}
		
		for(row=1; row<background.length-1; row++){
			for(col=1; col< background[row].length-1; col++){
				
				Jpanelarray[row-1][col-1].setBorder(new LineBorder(Color.BLACK,1));
				
				if (background[row][col]){
					Jpanelarray[row-1][col-1].setBackground(Color.BLACK);
				}
				else {
					Jpanelarray[row-1][col-1].setBackground(Color.WHITE);
				}
			}

		}//end for
		
		if(toggle){
			BeginGame();
		}
		else{ 
			EndGame();
		}
	}//end redraw 

}




