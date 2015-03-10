package main;

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
	}
}
