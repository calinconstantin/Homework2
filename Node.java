package coolStuff;
import java.util.ArrayList;

public class Node 
{	
	public int distanceX =0;
	public int distanceY =0;	
	public String Terrain = " ";	
	public ArrayList<Node> neighbors;
	public Node ()
	{
		neighbors = new ArrayList <Node>();
	}	
}
