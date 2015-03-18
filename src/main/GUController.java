package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class GUController {
	private ArrayList<Place> places = new ArrayList<Place>();
	private ArrayList<Road> roads = new ArrayList<Road>();
	private Graph<Place> graph = new Graph<Place>();
	private HashMap<String, Place> placeMap = new HashMap<String, Place>();
	private HashMap<String, Road> roadMap = new HashMap<String, Road>();
	private MapView mapView;
	private UI ui;
	private BST bst;
	

	public GUController(String string, Position mapLeftUp,
			Position mapRightDown, String string2, String string3) {
		String path = new File("src/main/skane.JPG").getAbsolutePath();
		mapView = new MapView(path, 
				mapLeftUp.getLongitude(),
				mapLeftUp.getLatitude(),
				mapRightDown.getLongitude(),
				mapRightDown.getLatitude());
		//Nedanstående endast för testning under programmets uppbyggnad.
		ui = new UI(this, mapView);
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(ui);
		frame.pack();
		frame.setVisible(true);
		//Här slutar testet.
		try{
			openFiles();
		} catch(IOException e) {
			JOptionPane.showMessageDialog(null, "Oops, fel när filerna öppnades.");
		}
		
	}
	
	/**
	 * Metoden öppnar filerna och läser in dem till motsvarande ArrayList. samt HashMap.
	 * @throws IOException
	 */
	private void openFiles() throws IOException {
		String path1 = new File("src/main/places.txt").getAbsolutePath();
		String path2 = new File("src/main/roads.txt").getAbsolutePath();
		FileReader fr = new FileReader(path1);
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
		
		fr = new FileReader(path2);
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
		
		ui.addPlacesToList(places);

		bst = new BST(places);
		
		//Nedanstående block är enbart för test av funktioner.
		
		//Här slutar testet.
		
	}
	
	public void searchDijkstra(Place from, Place to) {
		ArrayList<Edge<Place>> temp = GraphSearch.dijkstraSearch(graph, from, to);
		mapView.showRoads(edgesToRoads(temp));
		updateText(edgesToRoads(temp), from, to);
	}
	
	public void searchDepth(Place from, Place to) {
		ArrayList<Edge<Place>> temp = GraphSearch.depthFirstSearch(graph, from, to);
		mapView.showRoads(edgesToRoads(temp));
		updateText(edgesToRoads(temp), from, to);
	}
	
	public void searchBreadth(Place from, Place to) {
		ArrayList<Edge<Place>> temp = GraphSearch.breadthFirstSearch(graph, from, to);
		mapView.showRoads(edgesToRoads(temp));
		updateText(edgesToRoads(temp), from, to);
	}
	
	private void updateText(ArrayList<Road> roads, Place from, Place to) {
		ArrayList<JLabel> list = new ArrayList<JLabel>();
		list.add(new JLabel("Från:"));
		list.add(new JLabel("Stad: " + from.getName()
				+ "\tArea: " + from.getArea()
				+ "\tInvånare: " + from.getPopulation()));
		list.add(new JLabel("Till:"));
		list.add(new JLabel("Stad: " + from.getName() 
				+ "\tArea: " + from.getArea()
				+ "\tInvånare: " + from.getPopulation()));
		list.add(new JLabel(""));
		list.add(new JLabel(""));
		list.add(new JLabel(""));
		
		int totalLength = 0;
		for(Road road : roads) {
			list.add(new JLabel(road.toString()));
			totalLength += road.getCost();
		}
		list.add(new JLabel("Total sträcka: " + totalLength));
		ui.addLabels(list);
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
	 * Tar en ArrayList innehållande Edge-object och returnerar en ArrayList
	 * innehållande motsvarande Road-objekt. Metoden använder sig av en hashmap för att
	 * koppla Edge-objekt till existerande Road-objekt.
	 * @param inList ArrayList med Edges
	 * @return tempRoads ArrayList med Road-objekt
	 */
	private ArrayList<Road> edgesToRoads(ArrayList<Edge<Place>> inList) {
		ArrayList<Road> tempRoads = new ArrayList<Road>();
		for(Edge<Place> road : inList) {
			tempRoads.add(roadMap.get(road.getFrom().getName() + road.getTo().getName()));
		}
		return tempRoads;
	}

}
