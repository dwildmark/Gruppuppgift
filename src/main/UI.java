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
	private static final long serialVersionUID = 1L;
	private GUController controller;
	private JPanel route = new JPanel (new GridLayout (3,1));
	private JPanel sButtons = new JPanel (new GridLayout (4,1));
	private JPanel allbuttons =  new JPanel (new BorderLayout());
	private JLabel sOptions = new JLabel ("Sökalternativ");
	private JComboBox<Place> dropDownList = new JComboBox<Place>();
	private JComboBox <Place> dropDownList2 = new JComboBox<Place>();
	private JTabbedPane alternativ = new JTabbedPane();
	private JPanel mapInfo = new JPanel( new GridLayout(15,1));
	private JRadioButton dijkstra = new JRadioButton ("Dijkstra");
	private JRadioButton depth = new JRadioButton ("Sök på djupet");
	private JRadioButton breadth = new JRadioButton ("Sök på bredden");
	private JButton search = new JButton ("Sök");
	

	public UI(GUController controller, MapView mapView) {
		this.controller = controller;
		setLayout(new BorderLayout());
		
		alternativ.addTab("Karta", mapView);
		alternativ.addTab("Text", mapInfo );	
		
		ButtonGroup btngrp1 = new ButtonGroup ();
		btngrp1.add(depth);
		btngrp1.add(breadth);
		btngrp1.add(dijkstra);
		dijkstra.setSelected(true);
		
		route.setPreferredSize( new Dimension (550,100));
		route.add(dropDownList);
		route.add(dropDownList2);
		route.add(search);
		
		sButtons.add(sOptions);
		sButtons.add(dijkstra);
		sButtons.add(depth);
		sButtons.add(breadth);
		
		allbuttons.add(route, BorderLayout.WEST);
		allbuttons.add(sButtons, BorderLayout.EAST);
		
		add(alternativ, BorderLayout.NORTH);
		add(allbuttons, BorderLayout.SOUTH);
		
		Buttons btn = new Buttons();
		dijkstra.addActionListener(btn);
		depth.addActionListener(btn);
		breadth.addActionListener(btn);
		dropDownList.addActionListener( btn);
		dropDownList2.addActionListener(btn);
		search.addActionListener(btn);
	}
	
	public void addLabels(ArrayList<JLabel> labels) {
		mapInfo.removeAll();
		for(JLabel label : labels) {
			mapInfo.add(label);
		}
	}
	
	public void addPlacesToList(ArrayList<Place> inList) {
		for( Place place : inList) {
			dropDownList.addItem(place);
			dropDownList2.addItem(place);
		}
	}
	
	private class Buttons implements ActionListener{

		public void actionPerformed(ActionEvent e){
			if(e.getSource()==search){
				if( dijkstra.isSelected()) {
					controller.searchDijkstra((Place)dropDownList.getSelectedItem(), (Place)dropDownList2.getSelectedItem());
				} else if( depth.isSelected()) {
					controller.searchDepth((Place)dropDownList.getSelectedItem(), (Place)dropDownList2.getSelectedItem());
				} else if( breadth.isSelected()){
					controller.searchBreadth((Place)dropDownList.getSelectedItem(), (Place)dropDownList2.getSelectedItem());
				}
			}
		}
	}
}
