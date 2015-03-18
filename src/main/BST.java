package main;

import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.JOptionPane;

/**
 * Denna klassen är till för att bygga vårt binära sökträd, ta bort
 * objekt ur träden, lägga till nya objekt samt hantera detta.
 * @author Andreas
 */
public class BST {
	private Node tree;
	private Comparator comparator;

	/**
	 * Metoden ska lägga till nya platser tills det inte finns fler platser att 
	 * lägga till. 
	 * 
	 * Metoden använder sig av en annan metod "Put" för att
	 * "sortera" in places i det binära sökträdet.
	 * måste skapas, typ metod: expandTree eller liknande
	 * @param places, olika städer.
	 */

	public BST(ArrayList<Place> places){
		comparator = new Comp();
		for(int i = 0; i < places.size(); i++){
			Place temp = places.get(i);
			put(temp, temp.getName());
		}
	}

	/**
	 * Metoden placerar in städerna i sökträdet. Med if-elssatserna så
	 * kontrollerar vi först on root-noden är tom, då ska den in där.
	 * Annars kontrollerar först om objektet ska placeras till vänster om noden innan,
	 * dvs: noden innan är större än den vi ska placera in. 
	 * Annars kollar vi om donen innan är mindre än den vi ska 
	 * plcera in då ska vi gå åt höger i vårt träd.
	 * 
	 * I else-satsen har vi en liten felhantering som tar hand om, om ett 
	 * objekt som man försöker placera in redan finns i listan.
	 * 
	 * @param place staden som ska placeras in.
	 * @param key, nyckeln, namnet på staden.
	 */
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
	/**
	 * I vårt sökträd ska vi kunna ta bort en stad om vi vill, 
	 * detta gör denna metoden. Fö att ta bort en önska stad
	 * skickar vi med stadens key. 
	 * @param key stadens namn som vi vill ta bort.
	 * @return staden som tas bort.
	 */

	public Place remove(String key) {
		Place place = get( key );
		if(place!=null) {
			tree = remove(tree,key);
		}
		return place;
	}

	/**
	 * Vi skickar med parametrarna node och key för att bestämma vilken lats staden med
	 * namnet i key som ska tas bort. Vi itererar igenom vårt träd beroende på
	 * vart noden vi letar efter ligger, höger och vänster om rotnoden. När vi
	 * hittat rätt nod kommer vi ta bort den noden och då blir det "null" i noden 
	 * position istället för stadens namn, key.
	 * 
	 * @param node noden som ska tas bort.
	 * @param key namnet på staden.
	 * @return noden som ska tas bort.
	 */
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
	/**
	 * Metoden används för att hitta minsta nod i listan.
	 * @param node som vi letar efter.
	 * @return noden som vi letar efter.
	 */
	private Node getMin(Node node) {
		while(node.left!=null)
			node = node.left;
		return node;
	}
	/**
	 * Innre klassen comp används för att
	 * gämföra två olika städers namn för att sortera i lista
	 * eller när man vill ta bort ur listen eller när
	 * man vill lägga till i listan. Denna klassen anropas 
	 * då från metoderna nämda ovan, och returnerar en gämförelse
	 * med två olika keys.
	 *
	 */
	private class Comp implements Comparator {
		public int compare( Object key1, Object key2 ) {
			Comparable k1 = ( Comparable )key1;
			return k1.compareTo( key2 );
		}
	}

	/**
	 * Metoden används för att leta efter en key i listan
	 * detta görs genom att kolla om node(key) inte är tom, 
	 * är det så kommer värdet för node returneras annars kommer null returneras
	 * det innebär i så fall att det inte finns någon sådan key i listan.
	 * @param key staden vi letar efter.
	 * @return stadens value eller null om den inte finns.
	 */
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
		while( ( node != null ) && ( ( res = node.key.compareTo( key ) ) != 0 ) ) {
			if( res > 0 )
				node = node.left;
			else
				node = node.right;
		}
		return node;
	}

	/**
	 * Innre klassen Node som håller variabler för 
	 * användningen av det binära sökträdet.
	 * @author Andreas
	 *
	 */
	private class Node {
		public String key;
		public Place value;
		public Node left;
		public Node right;
		/**
		 * Konstruktor för variabler som används i klassen.
		 * @param key stadens namn.
		 * @param value platsen för staden.
		 * @param left för att komma vänster i trädet.
		 * @param right för att komma höger i trädet.
		 */
		public Node (String key, Place value, Node left, Node right){
			this.key = key;
			this.value = value;
			this.left = left;
			this.right = right;
		}
	}

}
