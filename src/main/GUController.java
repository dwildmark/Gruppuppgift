package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.ImageIcon;
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

	public GUController(Position mapLeftUp,
			Position mapRightDown) {
		String path = new File("src/main/skane.JPG").getAbsolutePath();
		String pathIcon = new File("src/main/skanevapen.gif").getAbsolutePath();
		ImageIcon icon = new ImageIcon(pathIcon);

		mapView = new MapView(path, mapLeftUp.getLongitude(),
				mapLeftUp.getLatitude(), mapRightDown.getLongitude(),
				mapRightDown.getLatitude());

		ui = new UI(this, mapView);
		JFrame frame = new JFrame();
		frame.setIconImage(icon.getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(ui);
		frame.pack();
		frame.setVisible(true);
		try {
			openFiles();
		} catch (IOException e) {
			JOptionPane
					.showMessageDialog(ui, "Oops, fel när filerna öppnades.");
		}

	}

	/**
	 * Metoden öppnar filerna och läser in dem till motsvarande ArrayList och
	 * HashMap. Slutligen byggs ett binärt sökträd.
	 * 
	 * @throws IOException
	 */
	private void openFiles() throws IOException {
		String path1 = new File("src/main/places.txt").getAbsolutePath();
		String path2 = new File("src/main/roads.txt").getAbsolutePath();
		FileReader fr = new FileReader(path1);
		BufferedReader reader = new BufferedReader(fr);
		String readLine;
		// Här läses places.txt in och ett Place-objekt skapas för varje stad.
		while ((readLine = reader.readLine()) != null) {
			if (readLine.charAt(1) != '/') {
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

		// Här läses roads.txt in och ett Road-objekt skapas för varje väg.
		while ((readLine = reader.readLine()) != null) {

			ArrayList<Position> positions = new ArrayList<Position>();
			String[] info = readLine.split(",");

			for (int i = 3; i < info.length; i += 2) {
				positions.add(new Position(Double.parseDouble(info[i]), Double
						.parseDouble(info[i + 1])));
			}
			Road temp = new Road(info[0], info[1], Integer.parseInt(info[2]),
					positions);
			roads.add(temp);
			roadMap.put(temp.getFrom() + temp.getTo(), temp);
		}

		buildGraph();

		ui.addPlacesToList(places);

		bst = new BST(places);

	}

	/**
	 * Ndeanstående metoder utför sökning och visning av resultat. UI-klassen
	 * skickar med Place-objekt. Med hjälp av dessa beräknas väg på olika sätt
	 * beroende på vilken sökmetod som används. Väginformation skrivs ut till
	 * kartan via mapView.showRoads() och till textfönstret med hjälp av
	 * updateText.
	 * 
	 * @param from
	 *            Place-objekt med utgångsposition.
	 * @param to
	 *            Place-objekt med destination.
	 */
	public void searchDijkstra(Place from, Place to) {
		ArrayList<Edge<Place>> temp = GraphSearch.dijkstraSearch(graph, from,
				to);
		mapView.showRoads(edgesToRoads(temp));
		updateText(edgesToRoads(temp), from, to);
	}

	public void searchDepth(Place from, Place to) {
		ArrayList<Edge<Place>> temp = GraphSearch.depthFirstSearch(graph, from,
				to);
		mapView.showRoads(edgesToRoads(temp));
		updateText(edgesToRoads(temp), from, to);
	}

	public void searchBreadth(Place from, Place to) {
		ArrayList<Edge<Place>> temp = GraphSearch.breadthFirstSearch(graph,
				from, to);
		mapView.showRoads(edgesToRoads(temp));
		updateText(edgesToRoads(temp), from, to);
	}

	/**
	 * Testet nedan gör att användaren får ut information om en stad, genom att
	 * skicka in en "key" stadens namn som input. Sedan får användaren skicka in
	 * en key med stadens namn, då kommer användaren ta bort denna staden,
	 * utskriften kommer bli en tom messageDialog-ruta.
	 */
	public void testBST() {
		try {
			String cityTest1 = JOptionPane
					.showInputDialog(ui, "Hämta en stad med dess key: ");
			Place temp = bst.get(cityTest1);
			JOptionPane.showMessageDialog(
					ui,
					"Detta är staden: " + temp.getName() + " Invånare: "
							+ temp.getPopulation() + " Area: " + temp.getArea()
							+ " Position: " + temp.getPosition());
			String cityTest2 = JOptionPane
					.showInputDialog(ui, "Obs!, Staden försvinner! \nTa bort en stad med dess key:  ");
			Place temp2 = bst.remove(cityTest2);
			JOptionPane.showMessageDialog(ui, "Nu försöker vi hämta stadens namn: " + bst.get(temp2.getName()));
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(ui,
					"Antingen finns inte staden du angav"
							+ ", eller så tryckte du cancel. Mest så du vet.");
		}
	}

	/**
	 * Metoden uppdaterar informationen i textrutan. Den visar information om de
	 * båda städerna, samt vilka vägar man ska ta och den totala sträckan.
	 * 
	 * @param roads
	 *            lista med vägarna
	 * @param from
	 *            Place-objekt med utgångsposition.
	 * @param to
	 *            Place-objekt med destination.
	 */
	private void updateText(ArrayList<Road> roads, Place from, Place to) {
		ArrayList<JLabel> list = new ArrayList<JLabel>();
		list.add(new JLabel("Från:"));
		list.add(new JLabel("Stad: " + from.getName() + "   Area: "
				+ from.getArea() + "   Invånare: " + from.getPopulation()));
		list.add(new JLabel("Till:"));
		list.add(new JLabel("Stad: " + to.getName() + "   Area: "
				+ to.getArea() + "   Invånare: " + to.getPopulation()));
		list.add(new JLabel(""));
		list.add(new JLabel(""));
		list.add(new JLabel(""));

		int totalLength = 0;
		for (Road road : roads) {
			list.add(new JLabel(road.toString()));
			totalLength += road.getCost();
		}
		list.add(new JLabel("Total sträcka: " + totalLength));
		ui.addLabels(list);
	}

	/**
	 * Bygger en graf med Place som vertex och Road som edges. Metoden utnyttjar
	 * en hashmap för att koppla Edges till Vertex.
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
	 * innehållande motsvarande Road-objekt. Metoden använder sig av en hashmap
	 * för att koppla Edge-objekt till existerande Road-objekt.
	 * 
	 * @param inList
	 *            ArrayList med Edges
	 * @return tempRoads ArrayList med Road-objekt
	 */
	private ArrayList<Road> edgesToRoads(ArrayList<Edge<Place>> inList) {
		ArrayList<Road> tempRoads = new ArrayList<Road>();
		for (Edge<Place> road : inList) {
			tempRoads.add(roadMap.get(road.getFrom().getName()
					+ road.getTo().getName()));
		}
		return tempRoads;
	}

}
