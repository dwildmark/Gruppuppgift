package f13;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import collections.ArrayList; // f7.ArrayList
import collections.List;      // f7.List

public class BinarySearchTree<K,V> implements SearchTree<K,V> {
    private Comparator<K> comparator;
    private BSTNode<K,V> tree;
    
    public BinarySearchTree() {
        comparator = new Comp();
    }
    
    public BinarySearchTree( Comparator<K> comp ) {
        comparator = comp;
    }
    
    private BSTNode<K,V> find(K key) {
        int res;
        BSTNode<K,V> node=tree;
        while( ( node != null ) && ( ( res = comparator.compare( key, node.key ) ) != 0 ) ) {
            if( res < 0 )
                node = node.left;
            else
                node = node.right;
        }
        return node;
    }
    
    public BSTNode<K,V> root() {
        return tree;
    }
    
    public V get(K key) {
        BSTNode<K,V> node = find( key );
        if(node!=null)
            return node.value;
        return null;
    }
    
    
    public void put(K key, V value) {
        tree = put(tree,key,value);        
    } 
    
    private BSTNode<K,V> put(BSTNode<K,V> node, K key, V value) {
        if( node == null ) {
            node = new BSTNode<K,V>( key, value, null, null );
        } else {
            if(comparator.compare(key,node.key)<0) {
                node.left = put(node.left,key,value);
            } else if(comparator.compare(key,node.key)>0) {
                node.right = put(node.right,key,value);
            }
        }
        return node;
    }
    
    public V remove(K key) {
        V value = get( key );
        if(value!=null) {
            tree = remove(tree,key);
        }
        return value;
    }
    
    private BSTNode<K,V> remove(BSTNode<K,V> node, K key) {
        int compare = comparator.compare(key,node.key);
        if(compare==0) {
            if(node.left==null && node.right==null)
                node = null;
            else if(node.left!=null && node.right==null)
                node = node.left;
            else if(node.left==null && node.right!=null)
                node = node.right;
            else {
                BSTNode<K,V> min = getMin(node.right);
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
    
    private BSTNode<K,V> getMin(BSTNode<K,V> node) {
        while(node.left!=null)
            node = node.left;
        return node;
    }
        
    public boolean contains( K key ) {
        return find( key ) != null;
    }
    
    public int height() {
        return height( tree );
    }
    
    private int height( BSTNode<K,V> node ) {
        if( node == null )
            return -1;
        return 1 + Math.max( height( node.left ), height( node.right ));
    }
    
    public Iterator<V> iterator() {
        return new Iter();
    }
    
    // Laboration 13
    public int size() {
        return 0;
    }
    public List<K> keys(){
        return null;
    }
    public List<V> values(){
        return null;
    }
    public V first(){
        return null;
    }
    public V last(){
        return null;
    }
    
    private class Comp implements Comparator<K> {
        public int compare( K key1, K key2 ) {
            Comparable<K> k1 = ( Comparable<K> )key1;
            return k1.compareTo( key2 );
        }
    }
    
    private class Iter implements Iterator<V> {
        private ArrayList<V> list = new ArrayList<V>();
        private int index = 0;
        
        public Iter() {
            inOrder(tree);
        }
        
        private void inOrder(BSTNode<K,V> node) {
            if(node!=null) {
                inOrder(node.left);
                list.add(node.value);
                inOrder(node.right);
            }
        }
        
        public boolean hasNext() {
            return index<list.size();
        }
        
        public V next() {
            if(!hasNext())
                throw new NoSuchElementException();
            return list.get(index++);
        }
        
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    
    public static void main(String[] args) {
        BinarySearchTree<String,String> tree = new BinarySearchTree<String,String>();
        tree.put("karta","map");
        tree.put("vacker","beautiful");
        tree.put("svart","black");
        tree.put("lärare","teacher");
        tree.put("boll", "ball");
        tree.put("vit","white");
        tree.put("hus","house");
        tree.put("vänster","left");
        tree.put("höger","right");
        tree.root().showTree();
        String res = (String)tree.get("lärare");
        System.out.println(res);
        System.out.println(tree.get("LÄRARE"));
        System.out.println("---------------------");
        Iterator<String> elements = tree.iterator();
        while(elements.hasNext()) {
            System.out.println(elements.next());
        }
    }
}
