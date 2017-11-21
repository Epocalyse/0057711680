package F28DA_CW2;

import org.jgrapht.graph.DefaultWeightedEdge;

//add information to edge of graph
public class Edge extends DefaultWeightedEdge{

	private static final long serialVersionUID = 1L;
	
	public Edge(){
	}
	
	public String getArrive(){
		return (String) getSource();
	}
	
	public String getDepart(){
		return (String) getSource();
	}
	
	public String toString(){
		String str = getDepart().toString() + getArrive().toString();
		return str;
	}
	
}
