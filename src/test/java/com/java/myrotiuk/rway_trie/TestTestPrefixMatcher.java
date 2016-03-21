package com.java.myrotiuk.rway_trie;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@RunWith(MockitoJUnitRunner.class)
public class TestTestPrefixMatcher {

	@Mock
	private Trie trie;
	
	@Mock
	private Tuple tuple;
	
	@InjectMocks
	private PrefixMatches pm;

	@Test
	public void testAdd() {
		String str = "do not wory be 	 happy";
		pm.add(str);
		verify(trie, times(3)).add(isA(Tuple.class));
	}

	@Test
	public void testContains() {
		pm.contains("world");
		verify(trie).contains("world");
	}

	@Test
	public void testDelete() {
		pm.delete("some");
		verify(trie).delete("some");
	}

	@Test
	public void testSize() {
		pm.size();
		verify(trie).size();
	}

	@Test
	public void testWordsWithPrefix() {
		Trie trie = new MyTrie();
		Trie spyTrie = spy(trie);
		
		spyTrie.add(new Tuple("have", "have".length()));
		spyTrie.add(new Tuple("hands", "hands".length()));
		spyTrie.add(new Tuple("happy", "happy".length()));
		spyTrie.add(new Tuple("happiness", "happiness".length()));
		spyTrie.add(new Tuple("handle", "handle".length()));
		spyTrie.add(new Tuple("girl", "girl".length()));
		
		PrefixMatches lpm = new PrefixMatches(spyTrie);
		
		int count = 0;
		for(String words: lpm.wordsWithPrefix("ha")){
			count++;
		}
		assertEquals(4, count);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testWordsWithPrefixException(){
		pm.wordsWithPrefix("a");
	}

	@Test
	public void testWordsWithPrefixAndNumberLengths() {
		Queue<String> q = new LinkedList<>();
		q.offer("have");
		q.offer("hands");
		q.offer("happy");
		q.offer("handle");
		q.offer("happiness");
		q.offer("girl");
		when(trie.wordsWithPrefix("ha")).thenReturn(q);
		int count = 0;
		for(String word: pm.wordsWithPrefix("ha", 2)){
			count++;
		}
		assertEquals(3, count);
		
		int count2 = 0;
		for(String word: pm.wordsWithPrefix("ha", 1)){
			count2++;
		}
		assertEquals(1, count2);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testWordsWithPrefixAndNumberLengthsException(){
		pm.wordsWithPrefix("a", 7);
	}

}
