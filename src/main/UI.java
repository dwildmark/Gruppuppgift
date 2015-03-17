package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;

public class UI extends JPanel {
	private GUController controller;
	private MapView mapView;
	private JPanel Backgrnd = new JPanel (new BorderLayout());
	private JPanel route = new JPanel (new GridLayout (3,1));
	private JPanel Sbuttons = new JPanel (new GridLayout (4,1));
	private JPanel allbuttons =  new JPanel (new BorderLayout());
//	private JPanel alternativ = new JPanel(), text = new JPanel(), inputPnl = new JPanel();
	private JLabel sOptions = new JLabel ("Sökalternativ");
	private JPanel tabs = new JPanel(new GridLayout(0,2));
	private JComboBox<Place> dropDownList;
	private JComboBox <Place> dropDownList2;
	private JTabbedPane alternativ = new JTabbedPane();

	private JRadioButton dijkstra = new JRadioButton ("Dijkstra");
	private JRadioButton depth = new JRadioButton ("Sök på djupet");
	private JRadioButton width = new JRadioButton ("Sök på bredden");
	private JButton search = new JButton ("Sök");
	

	public UI(GUController controller, MapView mapView) {
		this.controller = controller;
		this.mapView = mapView;



		
		
//		alternativ.addTab("Karta", mapView);
//		alternativ.addTab("Text", mapView);
		
		
//		alternativ.setPreferredSize( new Dimension(40,20));
		
		
		
			
		
		ButtonGroup btngrp1 = new ButtonGroup ();
		btngrp1.add(depth);
		btngrp1.add(width);
		btngrp1.add(dijkstra);
		
		route.setPreferredSize( new Dimension (500,100));
		route.add(dropDownList);
		route.add(dropDownList2);
		route.add(search);
		
		Sbuttons.add(sOptions);
		Sbuttons.add(dijkstra);
		Sbuttons.add(depth);
		Sbuttons.add(width);

//		Backgrnd.add(alternativ, BorderLayout.NORTH);
		Backgrnd.add(mapView, BorderLayout.CENTER);
		Backgrnd.add(allbuttons, BorderLayout.SOUTH);
		
		allbuttons.add(route, BorderLayout.WEST);
		allbuttons.add(Sbuttons, BorderLayout.EAST);
		
		this.add(Backgrnd);
//		this.add(mapView);
//		this.add(route);
//		this.add(Sbuttons);
//		this.add(tabpane);

		

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
