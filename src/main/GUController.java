package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class GUController {
	private ArrayList<Place> places = new ArrayList<Place>();
	private ArrayList<Road> roads = new ArrayList<Road>();
	private Graph<Place> graph = new Graph<Place>();
	

	public GUController(String string, Position mapLeftUp,
			Position mapRightDown, String string2, String string3) {
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
				places.add(new Place(contents[0], 
						Double.parseDouble(contents[1]),
						Double.parseDouble(contents[2]),
						Double.parseDouble(contents[3]),
						Integer.parseInt(contents[4])));
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
			
			roads.add(new Road(info[0], info[1], 
					Integer.parseInt(info[2]), positions));
		}
		
		System.out.println(roads.get(3));
		System.out.println(places.get(7));
		
	}

}
