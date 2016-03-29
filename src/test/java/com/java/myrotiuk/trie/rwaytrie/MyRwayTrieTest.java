package com.java.myrotiuk.trie.rwaytrie;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

import com.java.myrotiuk.trie.Trie;
import com.java.myrotiuk.trie.rwaytrie.MyRwayTrie;
import com.java.myrotiuk.trie.rwaytrie.Tuple;

public class MyRwayTrieTest {

	Trie trie = null;
	@Before
	public void setUp() throws Exception {
		trie = new MyRwayTrie();
	}

	@Test
	public void shouldReturnCorrectSizeWhenAddedThreeElement() {// of and  	cat that  all above under";
		trie.add(new Tuple("the", "the".length()));
		trie.add(new Tuple("cat", "cat".length()));
		trie.add(new Tuple("dog", "dog".length()));
		assertTrue("Element has not been added",3 == trie.size());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowAnExceptionWhenWordContainsSybols(){
		trie.add(new Tuple("Q34(", "Q34(".length()));
	}

	@Test
	public void shouldReturnIfThereIsSuchWord() {
		trie.add(new Tuple("world","world".length()));
		assertTrue(trie.contains("world"));
	}

	@Test
	public void shouldCheckDeletionOfTheElement() {
		trie.add(new Tuple("girl", "girl".length()));
		assertTrue(trie.contains("girl"));
		assertTrue(trie.delete("girl"));
		assertFalse(trie.contains("girl"));
	}

	@Test
	public void checkProperIterationOverWords() {
		trie.add(new Tuple("and", "and".length()));
		trie.add(new Tuple("me", "me".length()));
		trie.add(new Tuple("world", "world".length()));
		Iterable<String> iter = trie.words();
		ArrayList<String> list  = new ArrayList<>();
		for(String words: iter){
			list.add(words);
		}
		assertArrayEquals(new ArrayList<String>(){
			{
				add("me");
				add("and");
				add("world");
			}
		}.toArray(), list.toArray());
	}

	@Test
	public void shouldReturnTwoWordsWithSpecificPrefix() {
		trie.add(new Tuple("be", "be".length()));
		trie.add(new Tuple("happy", "happy".length()));
		trie.add(new Tuple("have", "have".length()));
		trie.add(new Tuple("love", "love".length()));
		int count = 0;
		for(String words : trie.wordsWithPrefix("ha")){
			count++;
		}
		assertEquals(2, count);
	}

	@Test
	public void testSize() {
		trie.add(new Tuple("pilot", "pilot".length()));
		trie.add(new Tuple("people", "people".length()));
		trie.add(new Tuple("man", "man".length()));
		assertTrue("Element has not been added",3 == trie.size());
	}

}
