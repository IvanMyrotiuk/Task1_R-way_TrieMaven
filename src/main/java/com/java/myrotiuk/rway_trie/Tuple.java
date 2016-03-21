package com.java.myrotiuk.rway_trie;

/**
 * Class<code> Tuple</code> for creating box for word and weight
 *
 * @version 1.0
 * @author Ivan Myrotiuk
 * @since 18-03-2016
 */
public class Tuple {
	private String word;
	private int weight;

	public Tuple(String word, int weight) {
		this.word = word;
		this.weight = weight;
	}

	public String getWord() {
		return word;
	}

	public int getWeight() {
		return weight;

	}
}
