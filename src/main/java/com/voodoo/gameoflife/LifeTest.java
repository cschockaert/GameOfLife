package com.voodoo.gameoflife;


import javax.swing.JFrame;


/**
 * main Class that creates and resizes the JFrame
 * @author anuccio
 *
 */
public class LifeTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Life box = new Life();
		
		box.setSize(500,400);
		box.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		box.pack();
		box.setVisible(true);

	}

}
