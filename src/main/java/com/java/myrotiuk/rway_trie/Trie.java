package com.java.myrotiuk.rway_trie;

/**
 * Interface<code> Trie</code> for the representation in-memory dictionary
 *
 * @version 1.0
 * @author Ivan Myrotiuk
 * @since 18-03-2016
 */
public interface Trie {

	/**
	 * Method for adding into Trie pair(word and length of word - weight)  
	 * @param tuple holder for word and weight
	 */
	public void add(Tuple tuple);

    /**
     * Method for checking if there are such word
     * @param word is a word to check 
     * @return true if there is such otherwise false
     */
    public boolean contains(String word);

    /**
     * Method for deleting specific word
     * @param word is a word to delete
     * @return true if deletion was success otherwise false
     */
    public boolean delete(String word);

    /**
     * Method that iterate through all words using BFS(Breadth-first search) 
     * @return Iterable with all words
     */
    public Iterable<String> words();
    
    /**
     * Method that iterate through all words that begin with specific 
     * prefix using BFS(Breadth-first search)
     * @param pref prefix for words that you want to choose
     * @return Iterable with all words with specific prefix
     */
    public Iterable<String> wordsWithPrefix(String pref);

    /**
     * Method for getting number of words that in out data structure
     * @return size of our data structure
     */
    public int size();

}
