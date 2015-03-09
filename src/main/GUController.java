package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GUController {
	private ArrayList<Place> places = new ArrayList<Place>();
	private ArrayList<Road> roads = new ArrayList<Road>();
	private Graph<Place> graph = new Graph<Place>();
	

	public GUController(String string, Position mapLeftUp,
			Position mapRightDown, String string2, String string3) {
		
	}
	
	private void openFiles() throws IOException {
		FileReader fr = new FileReader("places.txt");
		BufferedReader reader = new BufferedReader(fr);
		String readLine;
		while((readLine = reader.readLine()) != null) {
			if(readLine.charAt(0) != '/') {
				String[] contents = readLine.split(" ");
				places.add(new Place(contents[0], 
						Double.parseDouble(contents[1]),
						Double.parseDouble(contents[2]),
						Double.parseDouble(contents[3]),
						Integer.parseInt(contents[4])));
			}
		}
		
		fr = new FileReader("roads.txt");
		reader = new BufferedReader(fr);
		while((readLine = reader.))
		
		
	}

}
