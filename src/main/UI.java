package main;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;

public class UI extends JPanel {
	private GUController controller;
	private MapView mapView;
	private JPanel Backgrnd = new JPanel (new BorderLayout());
	private JPanel route = new JPanel (new GridLayout ());
	private JPanel Sbuttons = new JPanel (new GridLayout (3,1));
	private JPanel allbuttons =  new JPanel (new GridLayout (3,2));
	private JPanel karta = new JPanel(), text = new JPanel(), inputPnl = new JPanel();
	private JComboBox<Place> dropDownList;
	private JComboBox <Place> dropDownList2;
	private JTabbedPane tabpane = new JTabbedPane();
	private JRadioButton dijkstra = new JRadioButton ("Dijkstra");
	private JRadioButton depth = new JRadioButton ("Sök på djupet");
	private JRadioButton width = new JRadioButton ("Sök på bredden");
	private JButton search = new JButton ("Sök");
	

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

		
		
		
		

		ButtonGroup btngrp1 = new ButtonGroup ();
		btngrp1.add(depth);
		btngrp1.add(width);
		btngrp1.add(dijkstra);

		
		Sbuttons.add(dijkstra);
		Sbuttons.add(depth);
		Sbuttons.add(width);


		
		route.add(dropDownList);
		route.add(dropDownList2);
		route.add(search);

		this.add(Sbuttons);
		this.add(route);
		this.add(mapView);

		Buttons btn = new Buttons();
		dijkstra.addActionListener(btn);
		depth.addActionListener(btn);
		width.addActionListener(btn);
		dropDownList.addActionListener( btn);
		dropDownList2.addActionListener(btn);
		search.addActionListener(btn);
		




	}
	private class Buttons implements ActionListener{

		public void actionPerformed(ActionEvent e){


			if (e.getSource()==depth){
				//GraphSearch GS = new GraphSearch();
				//GS.depthFirstSearch(graph, from, to)
			}
			if (e.getSource()==width){
				//GraphSearch GS = new GraphSearch();
				//GS.breadthFirstSearch(graph, from, to)
			}
			if(e.getSource()==dijkstra){
				//GraphSearch GS = new GraphSearch();
				//GS.dijkstraSearch(graph, from, to)
			}
			if(e.getSource()==dropDownList){
					
			}
			if(e.getSource()==dropDownList2){

			}
			if(e.getSource()==search){

			}

		}

	}


	public void addPlacesToList(ArrayList<Place> inList) {
		for( Place place : inList) {
			dropDownList.addItem(place);
		}
	}


}
