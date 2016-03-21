package com.java.myrotiuk.rway_trie;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import javax.print.attribute.standard.RequestingUserName;

/**
 * Class<code> PrefixMatches</code> for the representation an instance that will handle your words
 *
 * @version 1.0
 * @author Ivan Myrotiuk
 * @since 18-03-2016
 */
public class PrefixMatches {
	private Trie trie;

	public PrefixMatches(Trie trie){
		this.trie = trie;
	}
	
	/**
	 * Method for creating in-memory dictionary with words. Method can handle word, sentence,
	 * array of words/sentences. If argument is a sentence then it is divided into words by spaces.
	 * Words that contain more than two symbol will be added to in-memory dictionary. 
	 * @param strings word, sentence, array of words/sentences without punctuation mark.
	 * @return number of words that was added
	 */
	public int add(String... strings) {
		int countAdd = 0;
		for(String strs: strings){
			String[] words = strs.split("\\s+");
			for(String word: words){
				if(word.matches("[a-z]{3,}")){
					countAdd++;
					trie.add(new Tuple(word, word.length()));
				}
			}
		}
		return countAdd;
	}
	
    /**
     * Method for checking if there are such word in dictionary
     * @param word is a word to check 
     * @return true if there is such otherwise false
     */
	public boolean contains(String word) {
		return trie.contains(word);
	}

    /**
     * Method for deleting specific word
     * @param word is a word to delete
     * @return true if deletion was success otherwise false
     */
	public boolean delete(String word) {
		return trie.delete(word);
	}

    /**
     * Method for getting number of words that in our data structure
     * @return size of our data structure
     */
	public int size() {
		return trie.size();
	}

	/**
	 * Method for getting words with prefix that contain 2 or more symbol will return k different lengths
	 * that start from minimal and from current prefix.
	 * For instance: there are words and their length and pref = 'abc'
	 * abc 3
	 * abcd 4
	 * abce 4
	 * abcde 5
	 * abcdef 6
	 * - when k=1 will be return 'abc'
	 * - when k=2  will be return 'abc', 'abcd', 'abce'
	 * - when k=3  will be return 'abc', 'abcd', 'abce', 'abcde'
	 * - when k=4  will be return 'abc', 'abcd', 'abce', 'abcde', 'abcdef' 
	 * @param pref specific prefix
	 * @param k number of lengths
	 * @return words with prefix for lengths 
	 */
	public Iterable<String> wordsWithPrefix(String pref, int k) {
		checkLength(pref);
		return getWordsWithPrefix(pref, k);
	}

	/**
	 * Method for getting words with prefix that contain 2 or more symbol will return k=3 different lengths
	 * that start from minimal and from current prefix.
	 * @param pref specific prefix
	 * @return words with prefix for lengths
	 */
	public Iterable<String> wordsWithPrefix(String pref){
		checkLength(pref);
		return getWordsWithPrefix(pref, 3);
	}
	
	private void checkLength(String pref){
		if(pref.length() < 2)
			throw new IllegalArgumentException("Length of prefix less then 2");
	}
	
	private Iterable<String> getWordsWithPrefix(String pref, final int kk){
		Queue<String> myWords = (Queue<String>)trie.wordsWithPrefix(pref);
		return new Iterable<String>(){
			public Iterator<String> iterator(){
				return new Iterator<String>(){
					int k = kk;
					int n = 1;
					int length_prev;
					int triger = 1;
					boolean stopiteration = false;
					public boolean hasNext(){
						if(myWords.size() != 0 && triger == 1){
							length_prev = myWords.peek().length();
							triger = 0;
						}
						if(myWords.size() == 0){
							return false;
						}
						
						String word = myWords.peek();
						int length = word.length();
						if(length != length_prev){
							length_prev = length;
							n++;
						}
						if(n != k+1){
							return true;
						}
						return false;
					}
					
					public String next(){
						String word = myWords.poll();
						return word;
					}
				};
			}
		};
	}
}