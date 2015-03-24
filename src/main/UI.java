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
	private JPanel routePnl = new JPanel (new GridLayout (3,1));
	private JPanel searchBtnPnl = new JPanel (new GridLayout (4,1));
	private JPanel interactPnl =  new JPanel (new BorderLayout());
	private JPanel testBSTPnl = new JPanel();
	private JLabel sOptions = new JLabel ("Sökalternativ");
	private JComboBox<Place> dropDownList = new JComboBox<Place>();
	private JComboBox <Place> dropDownList2 = new JComboBox<Place>();
	private JTabbedPane tabbPane = new JTabbedPane();
	private JPanel mapInfo = new JPanel( new GridLayout(15,1));
	private JRadioButton dijkstra = new JRadioButton ("Dijkstra");
	private JRadioButton depth = new JRadioButton ("Sök på djupet");
	private JRadioButton breadth = new JRadioButton ("Sök på bredden");
	private JButton searchBtn = new JButton ("Sök");
	private JButton startTestBtn = new JButton("Starta test");
	

	public UI(GUController controller, MapView mapView) {
		this.controller = controller;
		setLayout(new BorderLayout());
		
		testBSTPnl.add(startTestBtn);
		
		tabbPane.addTab("Karta", mapView);
		tabbPane.addTab("Text", mapInfo );
		tabbPane.addTab("BST-test", testBSTPnl);
		
		ButtonGroup btngrp1 = new ButtonGroup ();
		btngrp1.add(depth);
		btngrp1.add(breadth);
		btngrp1.add(dijkstra);
		dijkstra.setSelected(true);
		
		routePnl.setPreferredSize( new Dimension (550,100));
		routePnl.add(dropDownList);
		routePnl.add(dropDownList2);
		routePnl.add(searchBtn);
		
		searchBtnPnl.add(sOptions);
		searchBtnPnl.add(dijkstra);
		searchBtnPnl.add(depth);
		searchBtnPnl.add(breadth);
		
		interactPnl.add(routePnl, BorderLayout.WEST);
		interactPnl.add(searchBtnPnl, BorderLayout.EAST);
		
		add(tabbPane, BorderLayout.NORTH);
		add(interactPnl, BorderLayout.SOUTH);
		
		Buttons btn = new Buttons();
		dijkstra.addActionListener(btn);
		depth.addActionListener(btn);
		breadth.addActionListener(btn);
		dropDownList.addActionListener( btn);
		dropDownList2.addActionListener(btn);
		searchBtn.addActionListener(btn);
		startTestBtn.addActionListener(btn);
	}
	
	public void addLabels(ArrayList<JLabel> labels) {
		mapInfo.removeAll();
		for(JLabel label : labels) {
			mapInfo.add(label);
		}
		tabbPane.update(getGraphics());
	}
	
	public void addPlacesToList(ArrayList<Place> inList) {
		for( Place place : inList) {
			dropDownList.addItem(place);
			dropDownList2.addItem(place);
		}
	}
	
	private class Buttons implements ActionListener{

		public void actionPerformed(ActionEvent e){
			if(e.getSource()==searchBtn){
				if( dijkstra.isSelected()) {
					controller.searchDijkstra((Place)dropDownList.getSelectedItem(), (Place)dropDownList2.getSelectedItem());
				} else if( depth.isSelected()) {
					controller.searchDepth((Place)dropDownList.getSelectedItem(), (Place)dropDownList2.getSelectedItem());
				} else if( breadth.isSelected()){
					controller.searchBreadth((Place)dropDownList.getSelectedItem(), (Place)dropDownList2.getSelectedItem());
				}
			} else if(e.getSource() == startTestBtn) {
				controller.testBST();
			}
		}
	}
}
