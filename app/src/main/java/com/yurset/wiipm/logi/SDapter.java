package com.yurset.wiipm.logi;

public class SDapter {

	final int tileUnused = 0;
	final int tileWall = 1;
	final int tileBorder = 2;

	final int tileFloor = 3;
	final int tileDoor = 4;

	final int tileSource = 6;
	final int tileTarget = 9;

	int[] risk = null;
	int[][] cisk = null;

	public SDapter(int sizeY, int sizeX, int sizeO) {
		cisk = new int[sizeY][sizeX];
		risk = new SDungeon(sizeX, sizeY, sizeO).createDungeon();
		for (int yy = 0; yy < sizeY; yy++) {
			for (int xx = 0; xx < sizeX; xx++) {
				switch (risk[yy * sizeX + xx]) {
					case tileUnused:
						cisk[yy][xx] = VData.TAG_UNUSE;
						break;
					case tileWall:
						cisk[yy][xx] = VData.TAG_WALLE;
						break;
					case tileBorder:
						cisk[yy][xx] = VData.TAG_STONE;
						break;
					case tileFloor:
						cisk[yy][xx] = VData.TAG_EMPTY;
						break;
					case tileDoor:
						cisk[yy][xx] = VData.TAG_FIGHT;
						break;
					case tileSource:
						cisk[yy][xx] = VData.TAG_MASTER;
						break;
					case tileTarget:
						cisk[yy][xx] = VData.TAG_TARGET;
						break;
				}
			}
		}
	}

	public int[][] getMatrix() {
		return cisk;
	}
}