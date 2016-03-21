package com.java.myrotiuk.rway_trie;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestMyTrie {

	Trie trie = null;
	@Before
	public void setUp() throws Exception {
		trie = new MyTrie();
	}

	@Test
	public void testAdd() {// of and  	cat that  all above under";
		trie.add(new Tuple("the", "the".length()));
		trie.add(new Tuple("cat", "cat".length()));
		trie.add(new Tuple("dog", "dog".length()));
		assertTrue("Element has not been added",3 == trie.size());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddException(){
		trie.add(new Tuple("Q34(", "Q34(".length()));
	}

	@Test
	public void testContains() {
		trie.add(new Tuple("world","world".length()));
		assertTrue(trie.contains("world"));
	}

	@Test
	public void testDelete() {
		trie.add(new Tuple("girl", "girl".length()));
		assertTrue(trie.contains("girl"));
		assertTrue(trie.delete("girl"));
		assertFalse(trie.contains("girl"));
	}

	@Test
	public void testWords() {
		trie.add(new Tuple("and", "and".length()));
		trie.add(new Tuple("me", "me".length()));
		trie.add(new Tuple("world", "world".length()));
		int i = 0;
		for(String words : trie.words()){
			if(i == 0){
				assertEquals("me", words);
				i++;
			}else if( i == 1){
				assertEquals("and", words);
				i++;
			}else if( i == 2){
				assertEquals("world", words);
				i++;
			}
		}
		
	}

	@Test
	public void testWordsWithPrefix() {
		trie.add(new Tuple("be", "and".length()));
		trie.add(new Tuple("happy", "me".length()));
		trie.add(new Tuple("have", "world".length()));
		trie.add(new Tuple("love", "world".length()));
		int count = 0;
		for(String words : trie.wordsWithPrefix("ha")){
			count++;
		}
		assertEquals(2, count);
	}

	@Test
	public void testSize() {
		trie.add(new Tuple("pilot", "the".length()));
		trie.add(new Tuple("people", "cat".length()));
		trie.add(new Tuple("man", "dog".length()));
		assertTrue("Element has not been added",3 == trie.size());
	}

}
