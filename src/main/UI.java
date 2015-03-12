package main;

import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;

public class UI extends JPanel{
	private GUController controller;
	private MapView mapView;
	private JPanel karta, text, inputPnl;
	private JComboBox<Place> dropDownList;
	private JComboBox <Place> dropDownList2;
	private JTabbedPane tabpane;
	private JButton search;
	private JRadioButton dijkstra, depth, width;
	
	
	public UI(GUController controller, MapView mapView) {
		this.controller = controller;
		this.mapView = mapView;
		dropDownList = new JComboBox<Place>();
		dropDownList2 = new JComboBox<Place>();
		
		add(dropDownList2);
		add(dropDownList);
		add(tabpane);
		add(karta);
		add(text);
		add(inputPnl);
		
		dijkstra = new JRadioButton ("Dijkstra");
		depth = new JRadioButton ("Sök på djupet");
		width = new JRadioButton ("Sök på bredden");
		search = new JButton ("Sök");
		
		ButtonGroup btngrp1 = new ButtonGroup ();
		btngrp1.add(depth);
		btngrp1.add(width);
		btngrp1.add(dijkstra);
		
		JPanel Sbuttons = new JPanel ();
		Sbuttons.add(dijkstra);
		Sbuttons.add(depth);
		Sbuttons.add(width);
		this.add(Sbuttons);
		
		JPanel route = new JPanel();
		route.add(dropDownList);
		route.add(dropDownList2);
		route.add(search);
		this.add(route);
		
		this.add(mapView);
		
	}
	
	
	public void addPlacesToList(ArrayList<Place> inList) {
		for( Place place : inList) {
			dropDownList.addItem(place);
		}
	}
	
	
}
