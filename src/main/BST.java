package main;

import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.JOptionPane;

public class BST {
	private Node tree;
	private Comparator comparator;

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
			Place temp = places.get(i);
				put(temp, temp.getName());
			}


		}
	

	public void put(Place place, String key){
		tree = put(tree, key, place);       
	}

	private Node put(Node node, String key, Place place) {
		if( node == null ) {
			node = new Node( key, place, null, null );
		} else {
			if(comparator.compare (key, node.key) < 0) {
				node.left = put(node.left, key, place);
			} else if(comparator.compare (key, node.key) > 0) {
				node.right = put(node.right, key, place);
			}else{
				JOptionPane.showMessageDialog(null, "Objektet finns redan i listan!", "Put Error", JOptionPane.ERROR_MESSAGE);
				System.out.println("Objektet finns redan i listan!");
				return null;
				
			}
		}
		return node;
	}

	public Place remove(String key) {
		Place place = get( key );
		if(place!=null) {
			tree = remove(tree,key);
		}
		return place;
	}

	private Node remove(Node node, String key) {
		int compare = comparator.compare(key, node.key);
		if(compare==0) {
			if(node.left==null && node.right==null)
				node = null;
			else if(node.left!=null && node.right==null)
				node = node.left;
			else if(node.left==null && node.right!=null)
				node = node.right;
			else {
				Node min = getMin(node.right);
				min.right = remove(node.right,min.key);
				min.left = node.left;
				node = min;
			}
		} else if(compare<0) {
			node.left = remove(node.left,key);
		} else {
			node.right = remove(node.right,key);
		}
		return node;
	}

	private Node getMin(Node node) {
		while(node.left!=null)
			node = node.left;
		return node;
	}

	private class Comp  {
		public int compare( String key1, String key2 ) {
			Comparable k1 = ( Comparable )key1;
			return k1.compareTo( key2 );
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
		 public String key;
		 public Place value;
		 public Node left;
		 public Node right;

		 public Node (String key, Place value, Node left, Node right){
			 this.key = key;
			 this.value = value;
			 this.left = left;
			 this.right = right;
		 }
	 }
	 public static void main(String[] args) {
		 
		
	}
}
