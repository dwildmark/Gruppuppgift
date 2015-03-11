package f13;

import java.util.Comparator;
import java.util.Iterator;
import collections.List; // import f7.List

public class AVLTree<K, V> implements SearchTree<K, V> {
	private Comparator<K> comparator;
	private AVLNode<K, V> tree;
	private int size;

	public AVLTree() {
		comparator = new Comp();
	}

	public AVLTree(Comparator<K> comp) {
		comparator = comp;
	}

	private AVLNode<K, V> find(K key) {
		int res;
		AVLNode<K, V> node = tree;
		while ((node != null)
				&& ((res = comparator.compare(key, node.key)) != 0)) {
			if (res < 0)
				node = node.left;
			else
				node = node.right;
		}
		return node;
	}

	public AVLNode<K, V> root() {
		return tree;
	}

	public V get(K key) {
		AVLNode<K, V> node = find(key);
		if (node != null)
			return node.value;
		return null;
	}

	public boolean contains(K key) {
		return find(key) != null;
	}

	public int height() {
		return height(tree);
	}

	private int height(AVLNode<K, V> node) {
		if (node == null)
			return -1;
		return 1 + Math.max(height(node.left), height(node.right));
	}

	public void put(K key, V value) {
		tree = put(tree, key, value);
	}

	private AVLNode<K, V> put(AVLNode<K, V> node, K key, V value) {
		if (node == null) {
			node = new AVLNode<K, V>(key, value, null, null);
		} else {
			if (comparator.compare(key, node.key) < 0) {
				node.left = put(node.left, key, value);
				node = balanceLeft(node);
			} else if (comparator.compare(key, node.key) > 0) {
				node.right = put(node.right, key, value);
				node = balanceRight(node);
			}
		}
		return node;
	}

	public V remove(K key) {
		V value = get(key);
		if (value != null) {
			tree = remove(tree, key);
		}
		return value;
	}

	private AVLNode<K, V> remove(AVLNode<K, V> node, K key) {
		int compare = comparator.compare(key, node.key);
		if (compare == 0) {
			if (node.left == null && node.right == null)
				node = null;
			else if (node.left != null && node.right == null)
				node = node.left;
			else if (node.left == null && node.right != null)
				node = node.right;
			else {
				AVLNode<K, V> min = getMin(node.right);
				min.height = node.height;
				min.right = remove(node.right, min.key);
				min.left = node.left;
				node = min;
			}
		} else if (compare < 0) {
			node.left = remove(node.left, key);
		} else {
			node.right = remove(node.right, key);
		}
		node = balanceNode(node);
		return node;
	}

	private AVLNode<K, V> getMin(AVLNode<K, V> node) {
		while (node.left != null)
			node = node.left;
		return node;
	}

	private AVLNode<K, V> balanceNode(AVLNode<K, V> node) {
		if (node != null) {
			node = balanceLeft(node);
			node = balanceRight(node);
		}
		return node;
	}

	private AVLNode<K, V> balanceLeft(AVLNode<K, V> node) {
		if (height(node.left) - height(node.right) == 2) {
			if (height(node.left.left) - height(node.left.right) == -1) { // LeftRight
				node.left = rotateLeft(node.left);
			}
			node = rotateRight(node);
		}
		return node;
	}

	private AVLNode<K, V> balanceRight(AVLNode<K, V> node) {
		if (height(node.left) - height(node.right) == -2) {
			if (height(node.right.left) - height(node.right.right) == 1) { // RightLeft
				node.right = rotateRight(node.right);
			}
			node = rotateLeft(node);
		}
		return node;
	}

	private AVLNode<K, V> rotateLeft(AVLNode<K, V> node) {
		AVLNode<K, V> rootNode = node.right;
		node.right = rootNode.left;
		rootNode.left = node;
		return rootNode;
	}

	private AVLNode<K, V> rotateRight(AVLNode<K, V> node) {
		AVLNode<K, V> rootNode = node.left;
		node.left = rootNode.right;
		rootNode.right = node;
		return rootNode;
	}

	private class Comp implements Comparator<K> {
		public int compare(K key1, K key2) {
			Comparable<K> k1 = (Comparable<K>) key1;
			return k1.compareTo(key2);
		}
	}

	// Laboration 13
	public Iterator<V> iterator() {
		return null;
	}

	public int size() {
		return size;
	}

	public List<K> keys() {
		return null;
	}

	public List<V> values() {
		return null;
	}

	@Override
	public V first() {
		return null;
	}

	@Override
	public V last() {
		return null;
	}
}
