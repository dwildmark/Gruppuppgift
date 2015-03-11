package main;

import java.util.ArrayList;

public class BST {
	private Node tree;
	
	/**
	 * Metoden ska lägga till nya platser tills det inte finns fler platser att 
	 * lägga till. 
	 * 
	 * OBS!! metod för att "sortera" in places i det binära sökträdet
	 * måste skapas, typ metod: expandTree eller liknande
	 * @param places, olika städer.
	 */

	public BST(ArrayList<Place> places){
		for(int i = 0; i < places.size(); i++){
			

		}
	}

	public Place get(String key) {
		Node node = find( key );
		if(node!=null)
			return node.value;
		return null;
	}
	/**
	 * Om tree är större än key så ska jag söka åt vänster. Annars ska jag söka åt höger.
	 * När while-satsen blir 0 så har vi hittat noden,
	 * när res > 0 går vi åt vänster och när res < 0, åt höger.
	 * @param key staden vi söker efter
	 * @return funnen stad.
	 */
	private Node find(String key) {
		int res;
		Node node = tree;
		while( ( node != null ) && ( ( res = tree.key.compareTo( key ) ) != 0 ) ) {
			if( res > 0 )
				node = node.left;
			else
				node = node.right;
		}
		return node;
	}

	private class Node {
		private String key;
		private Place value;
		private Node left;
		private Node right;

		public Node (String key, Place value, Node left, Node right){
			this.key = key;
			this.value = value;
			this.left = left;
			this.right = right;
		}

		public int compareTo(Node nodeNew){
			return key.compareTo(nodeNew.key);
		}

	}

}
