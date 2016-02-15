package com.yurset.wiipm.logi;

import java.util.Date;
import java.util.Random;

// http://roguebasin.roguelikedevelopment.org/index.php?title=Java_Example_of_Dungeon-Building_Algorithm
// Designed by Mike Andersen
// Java version by "Solarnus"

public class SDungeon {

	// size of the map
	private int xsize = 0;
	private int ysize = 0;

	// number of "objects" to generate on the map
	private int objects = 0;

	// define the %chance to generate either a room or a corridor on the map
	// BTW, rooms are 1st priority so actually it's enough to just define the
	// chance of generating a room
	private int chanceRoom = 94;

	// our map
	private int[] dungeon_map = null;

	// the old seed from the RNG is saved in this one
	private long oldseed = 0;

	// a list over tile types we're using
	final private int tileUnused = 0;// 不可行
	final private int tileDirtWall = 1;// 不可行
	final private int tileStoneWall = 2;// 不可行
	final private int tileDirtFloor = 3;// 可行
	final private int tileDoor = 4;// 可行
	final private int tileSource = 6;
	final private int tileTarget = 9;

	public SDungeon(int inx, int iny, int inobj) {
		/*******************************************************************************/
		// Here's the one generating the whole map
		objects = inobj;
		xsize = inx;
		ysize = iny;

		dungeon_map = new int[xsize * ysize];
	}

	public int[] createDungeon() {
		/*******************************************************************************/
		// start with making the "standard stuff" on the map
		for (int y = 0; y < ysize; y++) {
			for (int x = 0; x < xsize; x++) {
				// ie, making the borders of unwalkable walls
				if (y == 0)
					setCell(x, y, tileStoneWall);
				else if (y == ysize - 1)
					setCell(x, y, tileStoneWall);
				else if (x == 0)
					setCell(x, y, tileStoneWall);
				else if (x == xsize - 1)
					setCell(x, y, tileStoneWall);

					// and fill the rest with dirt
				else
					setCell(x, y, tileUnused);
			}
		}

		/*******************************************************************************
		 * And now the code of the random-map-generation-algorithm begins!
		 *******************************************************************************/

		// start with making a room in the middle, which we can start building
		// upon
		makeRoom(xsize / 2, ysize / 2, 16, 13, getRand(0, 3));

		// keep count of the number of "objects" we've made
		int features = 1; // +1 for the first room we just made

		// then we start the main loop
		for (int countingTries = 0; countingTries < 1000; countingTries++) {

			// check if we've reached our quota
			if (features == objects) {
				break;
			}

			// start with a random wall
			int newx = 0;
			int xmod = 0;
			int newy = 0;
			int ymod = 0;
			int validTile = -1;

			// 1000 chances to find a suitable object (room or corridor)..
			// (yea, i know it's kinda ugly with a for-loop... -_-')

			for (int testing = 0; testing < 1000; testing++) {
				newx = getRand(1, xsize - 1);
				newy = getRand(1, ysize - 1);
				validTile = -1;

				// System.out.println("tempx: " + newx + "\ttempy: " + newy);

				if (getCell(newx, newy) == tileDirtWall) {
					// check if we can reach the place
					if (getCell(newx, newy + 1) == tileDirtFloor) {
						validTile = 0; //
						xmod = 0;
						ymod = -1;
					} else if (getCell(newx - 1, newy) == tileDirtFloor) {
						validTile = 1; //
						xmod = +1;
						ymod = 0;
					}

					else if (getCell(newx, newy - 1) == tileDirtFloor) {
						validTile = 2; //
						xmod = 0;
						ymod = +1;
					}

					else if (getCell(newx + 1, newy) == tileDirtFloor) {
						validTile = 3; //
						xmod = -1;
						ymod = 0;
					}

					// check that we haven't got another door nearby, so we
					// won't get alot of openings besides each other

					if (validTile > -1) {
						if (getCell(newx, newy + 1) == tileDoor) // north
							validTile = -1;
						else if (getCell(newx - 1, newy) == tileDoor)// east
							validTile = -1;
						else if (getCell(newx, newy - 1) == tileDoor)// south
							validTile = -1;
						else if (getCell(newx + 1, newy) == tileDoor)// west
							validTile = -1;
					}

					// if we can, jump out of the loop and continue with the
					// rest
					if (validTile > -1)
						break;
				}
			}

			if (validTile > -1) {

				// choose what to build now at our newly found place, and at
				// what direction
				int feature = getRand(0, 100);
				if (feature <= chanceRoom) { // a new room
					if (makeRoom((newx + xmod), (newy + ymod), 16, 13,
							validTile)) {
						features++; // add to our quota

						// then we mark the wall opening with a door
						setCell(newx, newy, tileDoor);

						// clean up infront of the door so we can reach it
						setCell((newx + xmod), (newy + ymod), tileDirtFloor);
					}
				}
			}
		}

		/*******************************************************************************
		 * All done with the building, let's finish this one off
		 *******************************************************************************/

		// sprinkle out the bonusstuff (stairs, chests etc.) over the map
		int newx = 0;
		int newy = 0;
		int ways = 0; // from how many directions we can reach the random spot
		// from
		int state = 0; // the state the loop is in, start with the stairs

		while (state != 10) {
			for (int testing = 0; testing < 1000; testing++) {
				newx = getRand(1, xsize - 1);
				newy = getRand(1, ysize - 2); // cheap bugfix, pulls down newy
				// to 0<y<24, from 0<y<25
				// System.out.println("x: " + newx + "\ty: " + newy);
				ways = 4; // the lower the better

				// check if we can reach the spot
				if (getCell(newx, newy + 1) == tileDirtFloor) {
					// north
					if (getCell(newx, newy + 1) != tileDoor)
						ways--;
				}

				if (getCell(newx - 1, newy) == tileDirtFloor) {
					// east
					if (getCell(newx - 1, newy) != tileDoor)
						ways--;
				}

				if (getCell(newx, newy - 1) == tileDirtFloor) {
					// south
					if (getCell(newx, newy - 1) != tileDoor)
						ways--;
				}

				if (getCell(newx + 1, newy) == tileDirtFloor) {
					// west
					if (getCell(newx + 1, newy) != tileDoor)
						ways--;
				}

				if (state == 0) {
					if (ways == 0) {
						// we're in state 0, let's place a "upstairs" thing
						setCell(newx, newy, tileSource);
						state = 1;
						break;
					}
				}

				else if (state == 1) {
					if (ways == 0) {
						// state 1, place a "downstairs"
						setCell(newx, newy, tileTarget);
						state = 10;
						break;
					}
				}
			}
		}

		// all done with the map generation, tell the user about it and finish
		System.out.println(xsize + "\t" + ysize + "\t" + objects + "\t"
				+ features);
		return dungeon_map;
	}

	// setting a tile's type
	private void setCell(int x, int y, int celltype) {
		dungeon_map[x + xsize * y] = celltype;
	}

	// returns the type of a tile
	private int getCell(int x, int y) {
		return dungeon_map[x + xsize * y];
	}

	// The RNG. the seed is based on seconds from the "java epoch" ( I think..)
	// perhaps it's the same date as the unix epoch
	private int getRand(int min, int max) {

		// the seed is based on current date and the old, already used seed
		Date now = new Date();
		long seed = now.getTime() + oldseed;
		oldseed = seed;

		Random rand = new Random(seed);
		int n = max - min + 1;
		int i = rand.nextInt(n);
		if (i < 0)
			i = -i;

		return min + i;
	}

	private boolean makeRoom(int x, int y, int xlength, int ylength,
							 int direction) {
		/*******************************************************************************/

		// define the dimensions of the room, it should be at least 4x4 tiles
		// (2x2 for walking on, the rest is walls)
		int xlen = getRand(4, xlength);
		int ylen = getRand(4, ylength);

		// the tile type it's going to be filled with
		int floor = tileDirtFloor; // jordgolv..
		int wall = tileDirtWall; // jordv????gg

		// choose the way it's pointing at
		int dir = 0;
		if (direction > 0 && direction < 4)
			dir = direction;

		switch (dir) {

			case 0: // north

				// Check if there's enough space left for it
				for (int ytemp = y; ytemp > (y - ylen); ytemp--) {
					if (ytemp < 0 || ytemp > ysize)
						return false;
					for (int xtemp = (x - xlen / 2); xtemp < (x + (xlen + 1) / 2); xtemp++) {
						if (xtemp < 0 || xtemp > xsize)
							return false;
						if (getCell(xtemp, ytemp) != tileUnused)
							return false; // no space left...
					}
				}

				// we're still here, build
				for (int ytemp = y; ytemp > (y - ylen); ytemp--) {
					for (int xtemp = (x - xlen / 2); xtemp < (x + (xlen + 1) / 2); xtemp++) {
						// start with the walls
						if (xtemp == (x - xlen / 2))
							setCell(xtemp, ytemp, wall);
						else if (xtemp == (x + (xlen - 1) / 2))
							setCell(xtemp, ytemp, wall);
						else if (ytemp == y)
							setCell(xtemp, ytemp, wall);
						else if (ytemp == (y - ylen + 1))
							setCell(xtemp, ytemp, wall);
							// and then fill with the floor
						else
							setCell(xtemp, ytemp, floor);
					}
				}

				break;

			case 1: // east

				for (int ytemp = (y - ylen / 2); ytemp < (y + (ylen + 1) / 2); ytemp++) {
					if (ytemp < 0 || ytemp > ysize)
						return false;
					for (int xtemp = x; xtemp < (x + xlen); xtemp++) {
						if (xtemp < 0 || xtemp > xsize)
							return false;
						if (getCell(xtemp, ytemp) != tileUnused)
							return false;
					}
				}

				for (int ytemp = (y - ylen / 2); ytemp < (y + (ylen + 1) / 2); ytemp++) {
					for (int xtemp = x; xtemp < (x + xlen); xtemp++) {
						if (xtemp == x)
							setCell(xtemp, ytemp, wall);
						else if (xtemp == (x + xlen - 1))
							setCell(xtemp, ytemp, wall);
						else if (ytemp == (y - ylen / 2))
							setCell(xtemp, ytemp, wall);
						else if (ytemp == (y + (ylen - 1) / 2))
							setCell(xtemp, ytemp, wall);
						else
							setCell(xtemp, ytemp, floor);
					}
				}

				break;

			case 2: // south

				for (int ytemp = y; ytemp < (y + ylen); ytemp++) {
					if (ytemp < 0 || ytemp > ysize)
						return false;
					for (int xtemp = (x - xlen / 2); xtemp < (x + (xlen + 1) / 2); xtemp++) {
						if (xtemp < 0 || xtemp > xsize)
							return false;
						if (getCell(xtemp, ytemp) != tileUnused)
							return false;
					}
				}

				for (int ytemp = y; ytemp < (y + ylen); ytemp++) {
					for (int xtemp = (x - xlen / 2); xtemp < (x + (xlen + 1) / 2); xtemp++) {
						if (xtemp == (x - xlen / 2))
							setCell(xtemp, ytemp, wall);
						else if (xtemp == (x + (xlen - 1) / 2))
							setCell(xtemp, ytemp, wall);
						else if (ytemp == y)
							setCell(xtemp, ytemp, wall);
						else if (ytemp == (y + ylen - 1))
							setCell(xtemp, ytemp, wall);
						else
							setCell(xtemp, ytemp, floor);
					}
				}

				break;

			case 3: // west

				for (int ytemp = (y - ylen / 2); ytemp < (y + (ylen + 1) / 2); ytemp++) {
					if (ytemp < 0 || ytemp > ysize)
						return false;
					for (int xtemp = x; xtemp > (x - xlen); xtemp--) {
						if (xtemp < 0 || xtemp > xsize)
							return false;
						if (getCell(xtemp, ytemp) != tileUnused)
							return false;
					}
				}

				for (int ytemp = (y - ylen / 2); ytemp < (y + (ylen + 1) / 2); ytemp++) {
					for (int xtemp = x; xtemp > (x - xlen); xtemp--) {
						if (xtemp == x)
							setCell(xtemp, ytemp, wall);
						else if (xtemp == (x - xlen + 1))
							setCell(xtemp, ytemp, wall);
						else if (ytemp == (y - ylen / 2))
							setCell(xtemp, ytemp, wall);
						else if (ytemp == (y + (ylen - 1) / 2))
							setCell(xtemp, ytemp, wall);
						else
							setCell(xtemp, ytemp, floor);
					}
				}

				break;
		}

		// yay, all done
		return true;
	}

}