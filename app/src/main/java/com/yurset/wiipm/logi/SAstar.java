package com.yurset.wiipm.logi;

import java.util.*;
import com.yurset.wiipm.base.APoint;

public class SAstar {
	private int[][] map;
	private int row, col;
	private Vector<Integer> wayTo;
	private List<Node> open, close;

	public SAstar(int[][] maze) {
		map = maze;
		row = maze.length;
		col = maze[0].length;
		open = new ArrayList<Node>();
		close = new ArrayList<Node>();
		wayTo = new Vector<Integer>();
	}

	public Vector<Integer> search(APoint be, APoint ed) {
		if (be.x < 0 || be.x >= row || ed.x < 0 || ed.x >= row || be.y < 0
				|| be.y >= col || ed.y < 0 || ed.y >= col) {
			// Log.i("wiiPM", "outOfBounds");
			return wayTo;
		}
		// _base.logicMap[y][x] > 2 && _base.logicMap[y][x] < 10 ||
		// _base.logicMap[y][x] > 99
		if (map[be.x][be.y] <= 2 || map[ed.x][ed.y] <= 2
				|| map[be.x][be.y] >= 10 && map[be.x][be.y] <= 99
				|| map[ed.x][ed.y] >= 10 && map[ed.x][ed.y] <= 99) {
			// Log.i("wiiPM", "inValid");
			return wayTo;
		}
		Node so = new Node(be.x, be.y, null);
		Node ta = new Node(ed.x, ed.y, null);
		open.add(so);
		search(so, ta);
		// for (Node node : result)
		// map[node.x][node.y] = 2;
		// System.out.println(wayTo.toString());
		return wayTo;
	}

	// 查找核心算法
	private void search(Node so, Node ta) {
		List<Node> result = new ArrayList<Node>();
		boolean isFind = false;
		Node node = null;
		while (open.size() > 0) {
			// System.out.println(openList);
			// 取出开启列表中最低F值，即第一个存储的值的F为最低的
			node = open.get(0);
			// 判断是否找到目标点
			if (node.x == ta.x && node.y == ta.y) {
				isFind = true;
				break;
			}
			// 上
			if ((node.y - 1) >= 0) {
				checkPath(node.x, node.y - 1, node, ta);
			}
			// 下
			if ((node.y + 1) < col) {
				checkPath(node.x, node.y + 1, node, ta);
			}
			// 左
			if ((node.x - 1) >= 0) {
				checkPath(node.x - 1, node.y, node, ta);
			}
			// 右
			if ((node.x + 1) < row) {
				checkPath(node.x + 1, node.y, node, ta);
			}
			// 从开启列表中删除
			// 添加到关闭列表中
			close.add(open.remove(0));
			// 开启列表中排序，把F值最低的放到最底端
			Collections.sort(open, new NodeFComparator());
			// System.out.println(openList);
		}
		if (isFind) {
			getPath(result, node);
		}
	}

	// 查询此路是否能走通
	private boolean checkPath(int x, int y, Node parent, Node ta) {
		Node node = new Node(x, y, parent);
		// 查找地图中是否能通过
		// 此处修改成可以被4[对战]8[事件]600-699[NPC]拦截
		if (map[x][y] <= 2 || map[x][y] == 4 || map[x][y] == 8
				|| map[x][y] >= 10 && map[x][y] <= 99 || map[x][y] >= 600
				&& map[x][y] <= 699) {
			close.add(node);
			return false;
		}
		// 查找关闭列表中是否存在
		if (isListContains(close, x, y) != -1) {
			return false;
		}
		// 查找开启列表中是否存在
		int index = -1;
		if ((index = isListContains(open, x, y)) != -1) {
			// G值是否更小，即是否更新G，F值
			if ((parent.g + 1) < open.get(index).g) {
				node.parent = parent;
				countG(node, ta);
				countF(node);

				open.set(index, node);
			}
		} else {
			// 添加到开启列表中
			node.parent = parent;
			count(node, ta);
			open.add(node);
		}
		return true;
	}

	// 集合中是否包含某个元素(-1：没有找到，否则返回所在的索引)
	private int isListContains(List<Node> list, int x, int y) {
		for (int i = 0; i < list.size(); i++) {
			Node node = list.get(i);
			if (node.x == x && node.y == y) {
				return i;
			}
		}
		return -1;
	}

	// 从终点往返回到起点
	private void getPath(List<Node> result, Node node) {
		if (node.parent != null) {
			getPath(result, node.parent);
			wayTo.add(direct(node.parent.x, node.parent.y, node.x, node.y));
		}
		result.add(node);
	}

	// 计算G,H,F值
	private void count(Node node, Node eNode) {
		countG(node, eNode);
		countH(node, eNode);
		countF(node);
	}

	// 计算G值
	private void countG(Node node, Node eNode) {
		if (node.parent == null) {
			node.g = 1;
		} else {
			node.g = node.parent.g + 1;
		}
	}

	// 计算H值
	private void countH(Node node, Node eNode) {
		node.f = (Math.abs(node.x - eNode.x) + Math.abs(node.y - eNode.y)) * 10;
	}

	// 计算F值
	private void countF(Node node) {
		node.f = node.g + node.h;
	}

	private int direct(int soX, int soY, int taX, int taY) {
		if (soX > taX)// 向上 0
			return 0;
		if (soY < taY)// 向右 1
			return 1;
		if (soX < taX)// 向下 2
			return 2;
		if (soY > taY)// 向左 3
			return 3;
		return 0;
	}
}

// 节点类
class Node {
	public int x;// X坐标
	public int y;// Y坐标
	public int g;// 当前点到起点的移动耗费
	public int h;// 当前点到终点的移动耗费，即曼哈顿距离|x1-x2|+|y1-y2|(忽略障碍物)
	public int f;// f=g+h
	public Node parent;// 父类节点

	public Node(int x, int y, Node parent) {
		this.x = x;
		this.y = y;
		this.parent = parent;
	}
}

// 节点比较类
class NodeFComparator implements Comparator<Node> {
	public int compare(Node node1, Node node2) {
		return node1.f - node2.f;
	}
}