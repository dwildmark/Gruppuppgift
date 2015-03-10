package main;

import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JPanel;

public class UI extends JPanel{
	private GUController controller;
	private MapView mapView;
	private JPanel karta, text, inputPnl;
	private JComboBox<Place> dropDownList;
	
	public UI(GUController controller, MapView mapView) {
		this.controller = controller;
		this.mapView = mapView;
		dropDownList = new JComboBox<Place>();
		add(dropDownList);
	}
	
	public void addPlacesToList(ArrayList<Place> inList) {
		for( Place place : inList) {
			dropDownList.addItem(place);
		}
	}
}
