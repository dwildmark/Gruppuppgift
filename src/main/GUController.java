package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GUController {
	private ArrayList<Place> places = new ArrayList<Place>();
	private ArrayList<Road> roads = new ArrayList<Road>();
	private Graph<Place> graph = new Graph<Place>();
	private HashMap<String, Place> placeMap = new HashMap<String, Place>();
	private HashMap<String, Road> roadMap = new HashMap<String, Road>();
	private MapView mapView;
	

	public GUController(String string, Position mapLeftUp,
			Position mapRightDown, String string2, String string3) {
		mapView = new MapView("C:/Users/Dennis/Documents/GitHub/Gruppuppgift/src/main/skane.JPG", 
				mapLeftUp.getLongitude(),
				mapLeftUp.getLatitude(),
				mapRightDown.getLongitude(),
				mapRightDown.getLatitude());
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(mapView);
		frame.pack();
		frame.setVisible(true);
		try{
			openFiles();
		} catch(IOException e) {
			JOptionPane.showMessageDialog(null, "Oops, fel när filerna öppnades.");
		}
		
	}
	
	private void openFiles() throws IOException {
		FileReader fr = new FileReader("C:/Users/Dennis/Documents/GitHub/Gruppuppgift/src/main/places.txt");
		BufferedReader reader = new BufferedReader(fr);
		String readLine;
		while((readLine = reader.readLine()) != null) {
			if(readLine.charAt(1) != '/') {
				String[] contents = readLine.split(" ");
				Place temp = new Place(contents[0], 
						Double.parseDouble(contents[1]),
						Double.parseDouble(contents[2]),
						Double.parseDouble(contents[3]),
						Integer.parseInt(contents[4]));
				places.add(temp);
				placeMap.put(contents[0], temp);
			}
		}
		
		fr = new FileReader("C:/Users/Dennis/Documents/GitHub/Gruppuppgift/src/main/roads.txt");
		reader = new BufferedReader(fr);
		
		while((readLine = reader.readLine()) != null) {
			
			ArrayList<Position> positions = new ArrayList<Position>();
			String[] info = readLine.split(",");
			
			for(int i = 3; i < info.length; i += 2) {
				positions.add(new Position(Double.parseDouble(info[i]),
						Double.parseDouble(info[i + 1])));
			}
			Road temp = new Road(info[0], info[1], 
					Integer.parseInt(info[2]), positions);
			roads.add(temp);
			roadMap.put(temp.getFrom() + temp.getTo(),temp);
		}
		buildGraph();
		//Nedanstående block är enbart för test av funktioner.
		System.out.println(roads.get(3));
		System.out.println(places.get(7));
		System.out.println(placeMap.get("Ängelholm"));
		ArrayList<Edge<Place>> tempArr = GraphSearch.dijkstraSearch(graph, placeMap.get("Höganäs"), placeMap.get("Ystad"));
		int sumWeight = 0;
		for(Edge<Place> road : tempArr) {
			System.out.println(road);
			sumWeight += road.getWeight();
		}
		System.out.println("Total sträcka: " + sumWeight);
		mapView.showRoads(edgesToRoads(tempArr));
		//Här slutar testet.
		
	}
	
	/**
	 * Bygger en graf med Place som vertex och Road som edges.
	 */
	private void buildGraph() {
		for (Place place : places) {
			graph.addVertex(place);
		}
		for (Iterator<Road> it = roads.iterator(); it.hasNext();) {
			Road temp = it.next();
			graph.addEdge(placeMap.get(temp.getFrom()), 
					placeMap.get(temp.getTo()), temp.getCost());
		}
	}
	
	/**
	 * Konverterar en ArrayList innehållande Edge-object till en ArrayList
	 * innehållande Road-objekt. Metoden använder sig av en hashmap för att
	 * koppla Edge-objekt till existerande Road-objekt.
	 * @param inList ArrayList med Edges
	 * @return tempRoads ArrayList med Road-objekt
	 */
	private ArrayList<Road> edgesToRoads(ArrayList<Edge<Place>> inList) {
		ArrayList<Road> tempRoads = new ArrayList<Road>();
		for(Edge<Place> road : inList) {
			System.out.println(road);
			tempRoads.add(roadMap.get(road.getFrom().getName() + road.getTo().getName()));
		}
		return tempRoads;
	}

}
