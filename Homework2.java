package coolStuff;

import java.util.HashMap;
import java.util.ArrayList;
//The shortest path between a mouse and a cheese 
//through a box with obstacles
public class Homework2 
{
	static void createMap() 
	{

		for (int i = 0; i <=9; i++)
			for (int j = 0; j <=19; j++)
				Matrix.graph[i][j] = new Node();

		for (int i = 0; i <= 9; i++)
			for (int j = 0; j <=19; j++) 
			{
				Matrix.graph[i][j].distanceX = i;
				Matrix.graph[i][j].distanceY = j;
				Matrix.graph[i][j].Terrain = "o";
				if (i <= 8)
					Matrix.graph[i][j].neighbours.add(Matrix.graph[i + 1][j]);
				if (i > 0)
					Matrix.graph[i][j].neighbours.add(Matrix.graph[i - 1][j]);
				if (j <=18)
					Matrix.graph[i][j].neighbours.add(Matrix.graph[i][j + 1]);
				if (j > 0)
					Matrix.graph[i][j].neighbours.add(Matrix.graph[i][j - 1]);
			}

		int obstacles = (int) Math.floor(Math.random() * 100);

		while (obstacles != 0) 
		{
			Matrix.graph[(int) Math.floor(Math.random() * 9)][(int) Math.floor(Math.random() * 19)].Terrain = "|";
			obstacles--;
		}

	}

	static void shortestPath(int mouseX, int mouseY, int cheeseX, int cheeseY) 
	{
		// Exploring the neighbours nodes first before going to the next node 
		// Add a distance score to each of them
		HashMap<Node, Double> distanceScore = new HashMap<Node, Double>();
		HashMap<Node, Node> previousNode = new HashMap<Node, Node>();
		ArrayList<Node> unvisited = new ArrayList<Node>();
		Node mouse = Matrix.graph[mouseX][mouseY];
		Node cheese = Matrix.graph[cheeseX][cheeseY];
		distanceScore.put(mouse, 0.0);
		previousNode.put(mouse, null);

		for (int i = 0; i <=9; i++)
			for (int j = 0; j <=19; j++) 
			{
				if (Matrix.graph[i][j] != mouse) 
				{
					distanceScore.put(Matrix.graph[i][j], 999.0);
					previousNode.put(Matrix.graph[i][j], null);
				}
				unvisited.add(Matrix.graph[i][j]);
			}

		while (unvisited.isEmpty() != true) 
		{
			Node targetNode = null;
			for (Node thisNode : unvisited)
				if (targetNode == null || distanceScore.get(thisNode) < distanceScore.get(targetNode))
					targetNode = thisNode;

			if (targetNode == cheese)
				break;

			unvisited.remove(targetNode);
			for (Node thisNode : targetNode.neighbours) 
			{
				Double alt = (double) distanceScore.get(targetNode) + 1;
				if (alt < distanceScore.get(thisNode)) {
					distanceScore.put(thisNode, alt);
					previousNode.put(thisNode, targetNode);
				}
			}
		}

		Node currentPath = cheese;
		while (currentPath != null) 
		{
			Matrix.shortestPath.add(currentPath);
			currentPath = previousNode.get(currentPath);

		}

	}

	static void printVisual() 
	{

		for (int i = 0; i < 9; i++) 
		{
			for (int j = 0; j <= 19; j++) 
			{
				switch (Matrix.graph[i][j].Terrain) 
				{
				case "X":
					System.out.print("X ");
					break;
				case "C":
					System.out.print("C ");
					break;
				case "M":
					System.out.print("M ");
					break;
				case "o":
					System.out.print("o ");
					break;
				case "|":
					System.out.print("| ");
					break;
				}
			}
			System.out.println(" ");
		}
	}

	public static void main(String[] args) 
	{
		createMap();

		int mouseX=0, mouseY=0, cheeseX=0, cheeseY=0;
		mouseX = (int) Math.floor(Math.random() * 9);
		mouseY = (int) Math.floor(Math.random() * 19);				
		cheeseX = (int) Math.floor(Math.random() * 9);		
		cheeseY = (int) Math.floor(Math.random() * 19);
		
		
		shortestPath(mouseX, mouseY, cheeseX, cheeseY);

		for (int k = 0; k < Matrix.shortestPath.size(); k++) 
		{
			Matrix.graph[Matrix.shortestPath.get(k).distanceX][Matrix.shortestPath.get(k).distanceY].Terrain = "X";

		}
		Matrix.graph[mouseX][mouseY].Terrain = "M";
		Matrix.graph[cheeseX][cheeseY].Terrain = "C";

		printVisual();
	}

}
