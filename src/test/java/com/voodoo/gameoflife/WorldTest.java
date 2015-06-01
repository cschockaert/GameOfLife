package com.voodoo.gameoflife;

import static org.junit.Assert.*;

import org.junit.Test;

public class WorldTest {

	@Test
	public void testGetAllCells() {
		World world = new World();
		assertEquals(World.WORLD_X * World.WORLD_Y, world.getAllCells().size());
	}

}
