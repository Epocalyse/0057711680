package F28DA_CW2;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

public class FlightItinerary implements IFlightItinerary{
	
	private SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> g;
	private SimpleDirectedWeightedGraph<Airport, Edge> g2;
	private HashMap<String, Airport> map = new HashMap<String, Airport>();
	
	public FlightItinerary(boolean tf){
		if (tf == true)
			g = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		else
			g2 = new SimpleDirectedWeightedGraph<>(Edge.class);
	}

	@Override
	public boolean populate(HashSet<String[]> airlines, HashSet<String[]> airports, HashSet<String[]> routes) {
		// Surrounded by try-catch due to reading files.
		try{
			for (String[] str: airports){
				Airport a = new Airport(str[0], str[1], str[2]);
				map.put(str[0], a);
				g2.addVertex(a);
			}
			
			for (String[] str: routes){
				Edge e = new Edge();
				g2.addEdge(map.get(str[1]), map.get(str[3]), e);
				g2.setEdgeWeight(e, Double.parseDouble(str[5]));
			}
			
			if(g2.vertexSet().size() != airports.size()){
				System.out.println("Not same " + (airports.size() - g2.vertexSet().size()));
				return false;
			}
			
			return true;
			
		}catch (Exception e) {
			System.out.println("Incorrect file reading.");
			return false;
		}	
	}

	@Override
	public IItinerary leastCost(String to, String from) throws FlightItineraryException {
		//Returns the cheapest flight itinerary from one airport (airport code) to another
		DijkstraShortestPath sp = new DijkstraShortestPath(g2);
		GraphPath gp = sp.getPath(map.get(from), map.get(to));
		
		System.out.println("Cheapest Itinerary for " + map.get(from).getAirport().toUpperCase() + " to " + map.get(to).getAirport().toUpperCase());
		System.out.println("Leg Leave At On Arrive At");
		//System.out.println(String.format(format, args));
		System.out.println(gp.toString());
		System.out.println("Total Journey Cost = £");
		System.out.println("Total Time in the Air = " + (int) gp.getWeight());
		return null;
		
		/*
		 * Itinerary for Edinburgh  to  Toronto
		 *Leg  Leave          At    On     Arrive         At
		 *1    Edinburgh      0530  FZ345  Dubai          1100
		 *2    Dubai          1230  EK657  Kuala  Lumpur  1730
		 *3    Kuala  Lumpur  1800  QF652  Sydney         2130
		 *Total  Journey  Cost     = £510
		 *Total  Time in the Air = 840
		 */
	}

	@Override
	public IItinerary leastHop(String to, String from) throws FlightItineraryException {
		// TODO Auto-generated method stub
		//Returns least connections flight itinerary from one airport (airport code) to another
		return null;
	}

	@Override
	public IItinerary leastCost(String to, String from, List<String> excluding) throws FlightItineraryException {
		// TODO
		// offer the possibility to exclude one or more airports from the search
		return null;
	}

	@Override
	public IItinerary leastHop(String to, String from, List<String> excluding) throws FlightItineraryException {
		// TODO Auto-generated method stub
		// offer the possibility to exclude one or more airports from the search
		return null;
	}

	@Override
	public String leastCostMeetUp(String at1, String at2) throws FlightItineraryException {
		// TODO Auto-generated method stub
		//Returns the airport code of the best airport for the meet up of two people located in two different airports
		//(airport codes) accordingly to the itineraries costs
		return null;
	}

	@Override
	public String leastHopMeetUp(String at1, String at2) throws FlightItineraryException {
		// TODO Auto-generated method stub
		//Returns the airport code of the best airport for the meet up of two people located in two different airports
		//(airport codes) accordingly to the number of connections
		return null;
	}

	@Override
	public String leastTimeMeetUp(String at1, String at2, String startTime) throws FlightItineraryException {
		// TODO Auto-generated method stub
		//Returns the airport code of the best airport for the earliest meet up of two people located in two different
		//airports (airport codes) when departing at a given time
		return null;
	}
	
	private void addpathInfo(String f1, String f2, int weight){
		DefaultWeightedEdge e = g.addEdge(f1, f2);
		g.setEdgeWeight(e, weight);
		e = g.addEdge(f2, f1);
		g.setEdgeWeight(e, weight);
	}
	
	//Get path from one vertex to another
	private GraphPath<String, DefaultWeightedEdge> getPathing(String start, String end){
		DijkstraShortestPath sp = new DijkstraShortestPath(g);
		return sp.getPath(start, end);
	}
	
	//Gets input from the user of which airport they are coming from/to
	private String getInputAirport(String stades){
		System.out.println("Enter the "+ stades +" airport:");
		Scanner sc1 = new Scanner(System.in);
		return sc1.nextLine().toLowerCase();
	}
	
	// PartA for FlightGraphT
	public void partA(){
		String edinburgh = "edinburgh";
		String heathrow = "heathrow";
		String dubai = "dubai";
		String sydney = "sydney";
		String kuala_lumpur = "kuala lumpur";

		g.addVertex(edinburgh);
		g.addVertex(heathrow);
		g.addVertex(dubai);
		g.addVertex(sydney);
		g.addVertex(kuala_lumpur);
		
		addpathInfo(edinburgh, heathrow, 80);
		addpathInfo(heathrow, dubai, 130);
		addpathInfo(heathrow, sydney, 570);
		addpathInfo(dubai, kuala_lumpur, 170);
		addpathInfo(dubai, edinburgh, 190);
		addpathInfo(kuala_lumpur, sydney, 150);
		
		
		System.out.println("Airports currently avalible: ");
		System.out.println("    " + g.vertexSet());
		
		String sta = getInputAirport("start");
		String des = getInputAirport("destination");
		
		if (g.containsVertex(sta) && g.containsVertex(des)){
			System.out.println("Cheapest flight path from " + sta.toUpperCase() + " to " + des.toUpperCase());
			System.out.println(this.getPathing(sta, des));
			System.out.println("Cost of cheapest path = £" + "");
		} else {
			System.out.println("Airport/s do not exist. Please try again.");
		}
	}
	
	// PartB
	public void partB() throws FileNotFoundException, FlightItineraryException{
		FlightsReader reader = new FlightsReader(FlightsReader.AIRLINECODS);
		
		populate(reader.getAirlines(), reader.getAirports(), reader.getRoutes());
		
		String sta = getInputAirport("start");
		String des = getInputAirport("destination");
		
		System.out.println("Cheapest/   flight route");
		Scanner sc1 = new Scanner(System.in);
		String least = sc1.nextLine().toLowerCase();
		sc1.close();
		
		if ((least.contains("least") && least.contains("cost"))||least.contains("cheap")){
			leastCost(sta, des);
		} else if ((least.contains("least") && least.contains("hop"))||least.contains("short")){
			leastHop(sta, des);
		}
		
		//if (g2.containsVertex(sta) && g2.containsVertex(des)){
			
		//} else {
			System.out.println("Airport/s do not exist. Please try again.");
		//}
	}
	
	public static void main(String[] args) throws FileNotFoundException, FlightItineraryException{
		System.out.println("Which part to do? Part A (Simple) or Part B (Realistic).");
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String partab = sc.nextLine().toUpperCase();
		if (partab.contains("A")){
			FlightItinerary fl = new FlightItinerary(true);
			fl.partA();
		}else if (partab.contains("B")){
			FlightItinerary fl = new FlightItinerary(false);
			fl.partB();
		}
	}

}
