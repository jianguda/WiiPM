package com.yurset.wiipm.logi;

import java.util.Vector;

import com.yurset.wiipm.base.APoint;

public class SFunc {
	int mate;
	boolean tag;
	int[][] maze = null;
	Vector<Integer> wayTo;
	APoint start = new APoint(0, 0);
	APoint end = new APoint(0, 0);
	APoint mart = new APoint(0, 0);

	public Vector<Integer> route(int[][] map, int cate, APoint cart) {
		maze = map;
		mate = cate;
		mart = cart;
		tag = false;
		wayTo = new Vector<Integer>();
		switch (cate) {
			case 0:// 左上-->右下
				start.y = 0;
				start.x = 0;
				end.y = cart.y;
				end.x = cart.x;
				break;
			case 1:// 左下-->右上
				start.y = cart.y;
				start.x = 0;
				end.y = 0;
				end.x = cart.x;
				break;
			case 2:// 右上-->左下
				start.y = 0;
				start.x = cart.x;
				end.y = cart.y;
				end.x = 0;
				break;
			case 3:// 右下-->左上
				start.y = cart.y;
				start.x = cart.x;
				end.y = 0;
				end.x = 0;
				break;
		}
		DFSearch(start.y, start.x);
		return wayTo;
	}

	private void DFSearch(int y, int x) {
		if (y == end.y && x == end.x) {
			tag = true;
			return;
		}
		int yy, xx;

		if (!tag && (mate == 1 || mate == 3)) {// 向上UPPER:0
			yy = y - 1;
			xx = x + 0;
			if ((yy >= 0 && yy <= mart.y) && (xx >= 0 && xx <= mart.x)
					&& maze[yy][xx] > 0) {
				wayTo.add(0);
				DFSearch(yy, xx);
				if (!tag)
					wayTo.remove(wayTo.size() - 1);
			}
		}
		if (!tag && (mate == 0 || mate == 1)) {// 向右RIGHT:1
			yy = y + 0;
			xx = x + 1;
			if ((yy >= 0 && yy <= mart.y) && (xx >= 0 && xx <= mart.x)
					&& maze[yy][xx] > 0) {
				wayTo.add(1);
				DFSearch(yy, xx);
				if (!tag)
					wayTo.remove(wayTo.size() - 1);
			}
		}
		if (!tag && (mate == 0 || mate == 2)) {// 向下DOWN:2
			yy = y + 1;
			xx = x + 0;
			if ((yy >= 0 && yy <= mart.y) && (xx >= 0 && xx <= mart.x)
					&& maze[yy][xx] > 0) {
				wayTo.add(2);
				DFSearch(yy, xx);
				if (!tag)
					wayTo.remove(wayTo.size() - 1);
			}
		}
		if (!tag && (mate == 2 || mate == 3)) {// 向左LEFT:3
			yy = y + 0;
			xx = x - 1;
			if ((yy >= 0 && yy <= mart.y) && (xx >= 0 && xx <= mart.x)
					&& maze[yy][xx] > 0) {
				wayTo.add(3);
				DFSearch(yy, xx);
				if (!tag)
					wayTo.remove(wayTo.size() - 1);
			}
		}
	}
}