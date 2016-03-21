package com.java.myrotiuk.rway_trie;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Class<code> MyTrie</code> for the representation in-memory dictionary
 *
 * @version 1.0
 * @author Ivan Myrotiuk
 * @since 18-03-2016
 */
public class MyTrie implements Trie {

	private static final int R = 26;
	private Node root = null;
	private int n;

	private static class Node {
		int weight = 0;
		String substr;
		Node[] next = new Node[R];
	}

	@Override
	/**
	 * Method for adding into Trie pair(word and length of word - weight)  
	 * @param tuple holder for word and weight
	 */
	public void add(Tuple tuple) {
		if(!tuple.getWord().matches("[a-z]+")){
			throw new IllegalArgumentException("wrong word");
		}
		root = put(root, tuple.getWord(), tuple.getWeight(), 0);
	}

	private Node put(Node x, String word, int weight, int d) {
		if (x == null)
			x = new Node();
		if (d == word.length()) {
			x.weight = weight;
			n++;
			return x;
		}
		char c = word.charAt(d);
		int position = getPosition(c);
		x.next[position] = put(x.next[position], word, weight, d + 1);
		return x;
	}

	@Override
    /**
     * Method for checking if there are such word
     * @param word is a word to check 
     * @return true if there is such otherwise false
     */
	public boolean contains(String word) {
		Node x = get(root, word, 0);
		if (x == null) {
			return false;
		}
		return true;
	}

	private Node get(Node x, String word, int d) {

		if (x == null) {
			return null;
		}
		if (d == word.length()) {
			return x;
		}
		char c = word.charAt(d);
		int position = getPosition(c);
		return get(x.next[position], word, d + 1);
	}

	private int getPosition(char c) {
		if (c >= 'a' && c <= 'z') {// c >= 97 && c <= 122
			return c - 'a';
		}
		return -1;
	}

	@Override
    /**
     * Method for deleting specific word
     * @param word is a word to delete
     * @return true if deletion was success otherwise false
     */
	public boolean delete(String word) {
		Node refRoot = root;
		Node x = delete(root, refRoot, word, 0);
		if (x != null) {
			return false;
		}
		n--;
		return true;
	}

	private Node delete(Node x, Node refX, String word, int d) {
		if (x == null) {
			return null;
		}
		if (d == word.length()) {
			if (x.weight == 0) {
				return null;
			}
			x.weight = 0;
			boolean canBeDeleted = true;
			for (int i = 0; i < R; i++) {
				if (x.next[i] != null) {
					canBeDeleted = false;
				}
				if (canBeDeleted == true) {
					char c = word.charAt(d - 1);
					int position = getPosition(c);
					refX.next[position] = null;
				}
			}
			return null;
		}
		char c = word.charAt(d);
		int position = getPosition(c);
		refX = x;
		Node tmp = delete(x.next[position], refX, word, d + 1);
		// deleting up by trunk
		if (tmp == null) {
			if (d - 1 == -1) {
				return null;// delete root
			}
			if (x.weight == 0) {
				boolean canBeDeleted = true;
				for (int i = 0; i < R; i++) {
					if (x.next[i] != null) {
						canBeDeleted = false;
					}
					if (canBeDeleted == true) {
						c = word.charAt(d - 1);// another char
						position = getPosition(c);// another position
						refX.next[position] = null;
					}
				}
				return null;
			}
		}
		return tmp;
	}

	@Override
    /**
     * Method that iterate through all words using BFS(Breadth-first search) 
     * @return Iterable with all words
     */
	public Iterable<String> words() {
		return wordsWithPrefix("");
	}

    /**
     * Method that iterate through all words that begin with specific 
     * prefix using BFS(Breadth-first search)
     * @param pref prefix for words that you want to choose
     * @return Iterable with all words with specific prefix
     */
	public Iterable<String> wordsWithPrefix(String prefix) {
		Queue<String> results = new LinkedList<String>();
		Node x = get(root, prefix, 0);
		collect(x, new StringBuilder(prefix), results);
		return results;
	}

	private void collect(Node x, StringBuilder prefix, Queue<String> results) {
		if (x == null)
			return;
		Queue<Node> myNodes = new LinkedList<Node>();
		x.substr = prefix.toString();
		myNodes.offer(x);
		if (x.weight != 0)
			results.offer(prefix.toString());
		while (!myNodes.isEmpty()) {
			Node currentX = myNodes.remove();
			for (char c = 0; c < R; c++) {
				if (currentX.next[c] != null) {
					char charToAdd = (char) (c + 'a');
					currentX.next[c].substr = currentX.substr + charToAdd;
					if (currentX.next[c].weight != 0) {
						results.offer(currentX.next[c].substr);
					}
					myNodes.offer(currentX.next[c]);
				}
			}
		}
	}

	@Override
    /**
     * Method for getting number of words that in our data structure
     * @return size of our data structure
     */
	public int size() {
		return n;
	}
}
