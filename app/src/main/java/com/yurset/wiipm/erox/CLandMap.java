package com.yurset.wiipm.erox;

public class CLandMap {

	// 基本单元大小是40*40
	// 2 * ( 8 * 9 )
	public int baseY = 16;
	public int baseX = 18;
	// baseZ = baseY * baseX
	public int baseZ = 288;

	// 城市、主干
	private int funcCity = 10;
	private boolean ranBo = true;

	public int baseCity[] = new int[funcCity];
	public int baseMap[][] = new int[baseY][baseX];

	public CLandMap() {
		// -1表示"道路"
		// 0-9表示CITY
		// 90-99表示"森林"
		for (int y = 0; y < baseY; y++) {
			for (int x = 0; x < baseX; x++) {
				baseMap[y][x] = 99;
			}
		}
		locCity();
		uprCity();
		locTown();
		locSite();
		mcModify();
	}

	// 初始城市0、道馆城市1-8、联盟城市9
	public void locCity() {
		// 19-34终
		// 37-52
		// 55-70八
		// 73-88七
		// 91-106
		// 109-124六
		// 127-142
		// 145-160二三
		// 163-178
		// 181-196一
		// 199-214
		// 217-232始
		// 235-250
		// 253-268四五
		baseCity[0] = 8 + 217;
		baseCity[1] = 4 + 181;
		baseCity[2] = 6 + 145;
		baseCity[3] = 0 + 145;
		baseCity[4] = 2 + 253;
		baseCity[5] = 10 + 253;
		baseCity[6] = 10 + 109;
		baseCity[7] = 15 + 73;
		baseCity[8] = 12 + 55;
		baseCity[9] = 8 + 19;
	}

	// 确定主干道路
	public void uprCity() {
		int y = 0, x = 0;
		int py = 0, px = 0;
		for (int i = 0; i < funcCity; i++) {
			y = baseCity[i] / baseX;
			x = baseCity[i] - y * baseX;
			baseMap[y][x] = i;
			if (i > 0) {
				// 左右方向铺路
				if (px < x) {
					if ((baseMap[py - 1][px] == 99 || baseMap[py - 1][px + 1] == 99)
							&& (baseMap[py + 1][px] == 99 || baseMap[py + 1][px + 1] == 99)) {
						for (int j = px + 1; j < x; j++) {
							if (baseMap[py][j] == 99)
								baseMap[py][j] = -1;
							if (baseMap[py][j + 1] != 99
									|| baseMap[py - 1][j] != 99
									|| baseMap[py + 1][j] != 99) {
								break;
							}
						}
					}
					if ((baseMap[py - 1][x] == 99 || baseMap[py - 1][x - 1] == 99)
							&& (baseMap[py + 1][x] == 99 || baseMap[py + 1][x - 1] == 99)) {
						for (int k = x - 1; k > px; k--) {
							if (baseMap[py][k] == 99)
								baseMap[py][k] = -1;
							if (baseMap[py][k - 1] != 99
									|| baseMap[py - 1][k] != 99
									|| baseMap[py + 1][k] != 99) {
								break;
							}
						}
					}
				} else if (px > x) {
					if ((baseMap[py - 1][px] == 99 || baseMap[py - 1][px - 1] == 99)
							&& (baseMap[py + 1][px] == 99 || baseMap[py + 1][px - 1] == 99)) {
						for (int j = px - 1; j > x; j--) {
							if (baseMap[py][j] == 99)
								baseMap[py][j] = -1;
							if (baseMap[py][j - 1] != 99
									|| baseMap[py - 1][j] != 99
									|| baseMap[py + 1][j] != 99) {
								break;
							}
						}
					}
					if ((baseMap[py - 1][x] == 99 || baseMap[py - 1][x + 1] == 99)
							&& (baseMap[py + 1][x] == 99 || baseMap[py + 1][x + 1] == 99)) {
						for (int k = x + 1; k < px; k++) {
							if (baseMap[py][k] == 99)
								baseMap[py][k] = -1;
							if (baseMap[py][k + 1] != 99
									|| baseMap[py - 1][k] != 99
									|| baseMap[py + 1][k] != 99) {
								break;
							}
						}
					}
				}
				// 上下方向铺路
				if (py < y) {
					if ((baseMap[py][x - 1] == 99 || baseMap[py + 1][x - 1] == 99)
							&& (baseMap[py][x + 1] == 99 || baseMap[py + 1][x + 1] == 99)) {
						for (int j = py + 1; j < y; j++) {
							if (baseMap[j][x] == 99)
								baseMap[j][x] = -1;
							if (baseMap[j + 1][x] != 99
									|| baseMap[j][x - 1] != 99
									|| baseMap[j][x + 1] != 99) {
								break;
							}
						}
					}
					if ((baseMap[y][x - 1] == 99 || baseMap[y - 1][x - 1] == 99)
							&& (baseMap[y][x + 1] == 99 || baseMap[y - 1][x + 1] == 99)) {
						for (int k = y - 1; k > py; k--) {
							if (baseMap[k][x] == 99)
								baseMap[k][x] = -1;
							if (baseMap[k - 1][x] != 99
									|| baseMap[k][x - 1] != 99
									|| baseMap[k][x + 1] != 99) {
								break;
							}
						}
					}
				} else if (py > y) {
					if ((baseMap[py][x - 1] == 99 || baseMap[py - 1][x - 1] == 99)
							&& (baseMap[py][x + 1] == 99 || baseMap[py - 1][x + 1] == 99)) {
						for (int j = py - 1; j > y; j--) {
							if (baseMap[j][x] == 99)
								baseMap[j][x] = -1;
							if (baseMap[j - 1][x] != 99
									|| baseMap[j][x - 1] != 99
									|| baseMap[j][x + 1] != 99) {
								break;
							}
						}
					}
					if ((baseMap[y][x - 1] == 99 || baseMap[y + 1][x - 1] == 99)
							&& (baseMap[y][x + 1] == 99 || baseMap[y + 1][x + 1] == 99)) {
						for (int k = y + 1; k < py; k++) {
							if (baseMap[k][x] == 99)
								baseMap[k][x] = -1;
							if (baseMap[k + 1][x] != 99
									|| baseMap[k][x - 1] != 99
									|| baseMap[k][x + 1] != 99) {
								break;
							}
						}
					}
				}
				if (py != y && px != x) {
					if (baseMap[py][x] == 99) {
						baseMap[py][x] = -1;
					}
				}
			}
			py = y;
			px = x;
		}
	}

	// 确定城市间的小镇
	public void locTown() {

	}

	// 决定孤立坐标
	public void locSite() {

	}

	// 小修小改
	public void mcModify() {
		// 修改循环空间不处理超大地图的边界
		for (int y = 1; y < baseY - 1; y++) {
			for (int x = 1; x < baseX - 1; x++) {
				if (baseMap[y][x] == 99) {
					// 使湖畔不连通空地
					if (baseMap[y - 1][x] < 0) {
						if (baseMap[y][x + 1] > 9 && baseMap[y + 1][x] > 9
								&& baseMap[y][x - 1] > 9) {
							if (ranBo) {
								baseMap[y][x] = 80;
							} else {
								baseMap[y][x] = 84;
							}
							ranBo = !ranBo;
						}
					} else if (baseMap[y][x + 1] < 0) {
						if (baseMap[y - 1][x] > 9 && baseMap[y + 1][x] > 9
								&& baseMap[y][x - 1] > 9) {
							if (ranBo) {
								baseMap[y][x] = 81;
							} else {
								baseMap[y][x] = 85;
							}
							ranBo = !ranBo;
						}
					} else if (baseMap[y + 1][x] < 0) {
						if (baseMap[y - 1][x] > 9 && baseMap[y][x + 1] > 9
								&& baseMap[y][x - 1] > 9) {
							if (ranBo) {
								baseMap[y][x] = 82;
							} else {
								baseMap[y][x] = 86;
							}
							ranBo = !ranBo;
						}
					} else if (baseMap[y][x - 1] < 0) {
						if (baseMap[y - 1][x] > 9 && baseMap[y][x + 1] > 9
								&& baseMap[y + 1][x] > 9) {
							if (ranBo) {
								baseMap[y][x] = 83;
							} else {
								baseMap[y][x] = 87;
							}
							ranBo = !ranBo;
						}
					}
				}
			}
		}
		// 连通靠近城市的郊野
		for (int y = 1; y < baseY - 1; y++) {
			for (int x = 1; x < baseX - 1; x++) {
				if (baseMap[y][x] == 99) {
					if ((baseMap[y - 1][x] >= 80 && baseMap[y - 1][x] <= 87)
							|| (baseMap[y + 1][x] >= 80 && baseMap[y + 1][x] <= 87)) {
						if ((baseMap[y][x - 1] >= 80 && baseMap[y][x - 1] <= 87)
								|| (baseMap[y][x + 1] >= 80 && baseMap[y][x + 1] <= 87)) {
							if ((baseMap[y - 1][x] == -1 || baseMap[y + 1][x] == -1)
									&& (baseMap[y][x - 1] == -1 || baseMap[y][x + 1] == -1)) {
								baseMap[y][x] = 88;
							}
						}
					}
				}
			}
		}
		// 城市附近8格空开
		for (int y = 1; y < baseY - 1; y++) {
			for (int x = 1; x < baseX - 1; x++) {
				if (baseMap[y][x] >= 0 && baseMap[y][x] <= 9) {
					if (baseMap[y - 1][x - 1] != -1
							&& baseMap[y - 1][x - 1] != 99) {
						baseMap[y - 1][x - 1] = 99;
					}
					if (baseMap[y - 1][x] != -1 && baseMap[y - 1][x] != 99) {
						baseMap[y - 1][x] = 99;
					}
					if (baseMap[y - 1][x + 1] != -1
							&& baseMap[y - 1][x + 1] != 99) {
						baseMap[y - 1][x + 1] = 99;
					}
					if (baseMap[y][x - 1] != -1 && baseMap[y][x - 1] != 99) {
						baseMap[y][x - 1] = 99;
					}
					if (baseMap[y][x + 1] != -1 && baseMap[y][x + 1] != 99) {
						baseMap[y][x + 1] = 99;
					}
					if (baseMap[y + 1][x - 1] != -1
							&& baseMap[y + 1][x - 1] != 99) {
						baseMap[y + 1][x - 1] = 99;
					}
					if (baseMap[y + 1][x] != -1 && baseMap[y + 1][x] != 99) {
						baseMap[y + 1][x] = 99;
					}
					if (baseMap[y + 1][x + 1] != -1
							&& baseMap[y + 1][x + 1] != 99) {
						baseMap[y + 1][x + 1] = 99;
					}
				}
			}
		}
		// 消除孤立的郊野点
		for (int y = 1; y < baseY - 1; y++) {
			for (int x = 1; x < baseX - 1; x++) {
				if (baseMap[y][x] >= 80 && baseMap[y][x] <= 89) {
					if (!(baseMap[y - 1][x] >= 80 && baseMap[y - 1][x] <= 89)) {
						if (!(baseMap[y][x + 1] >= 80 && baseMap[y][x + 1] <= 89)) {
							if (!(baseMap[y + 1][x] >= 80 && baseMap[y + 1][x] <= 89)) {
								if (!(baseMap[y][x - 1] >= 80 && baseMap[y][x - 1] <= 89)) {
									baseMap[y][x] = 99;
								}
							}
						}
					}
				}
			}
		}
		// 发现山区（只与森林接壤）
		for (int y = 1; y < baseY - 1; y++) {
			for (int x = 1; x < baseX - 1; x++) {
				if (baseMap[y][x] >= 99) {
					if (baseMap[y - 1][x] >= 99) {
						if (baseMap[y + 1][x] >= 99) {
							if (baseMap[y][x - 1] >= 99) {
								if (baseMap[y][x + 1] >= 99) {
									baseMap[y][x] = 100;
								}
							}
						}
					}
				}
			}
		}
		// 城市附近8格空开
		for (int y = 1; y < baseY - 1; y++) {
			for (int x = 1; x < baseX - 1; x++) {
				if (baseMap[y][x] >= 0 && baseMap[y][x] <= 9) {
					if (baseMap[y - 1][x - 1] != -1
							&& baseMap[y - 1][x - 1] != 99) {
						baseMap[y - 1][x - 1] = 99;
					}
					if (baseMap[y - 1][x] != -1 && baseMap[y - 1][x] != 99) {
						baseMap[y - 1][x] = 99;
					}
					if (baseMap[y - 1][x + 1] != -1
							&& baseMap[y - 1][x + 1] != 99) {
						baseMap[y - 1][x + 1] = 99;
					}
					if (baseMap[y][x - 1] != -1 && baseMap[y][x - 1] != 99) {
						baseMap[y][x - 1] = 99;
					}
					if (baseMap[y][x + 1] != -1 && baseMap[y][x + 1] != 99) {
						baseMap[y][x + 1] = 99;
					}
					if (baseMap[y + 1][x - 1] != -1
							&& baseMap[y + 1][x - 1] != 99) {
						baseMap[y + 1][x - 1] = 99;
					}
					if (baseMap[y + 1][x] != -1 && baseMap[y + 1][x] != 99) {
						baseMap[y + 1][x] = 99;
					}
					if (baseMap[y + 1][x + 1] != -1
							&& baseMap[y + 1][x + 1] != 99) {
						baseMap[y + 1][x + 1] = 99;
					}
				}
			}
		}
		// 消除孤立的山区点
		for (int y = 1; y < baseY - 1; y++) {
			for (int x = 1; x < baseX - 1; x++) {
				if (baseMap[y][x] == 100) {
					if (baseMap[y - 1][x] != 100) {
						if (baseMap[y][x + 1] != 100) {
							if (baseMap[y + 1][x] != 100) {
								if (baseMap[y][x - 1] != 100) {
									baseMap[y][x] = 99;
								}
							}
						}
					}
				}
			}
		}
	}
}