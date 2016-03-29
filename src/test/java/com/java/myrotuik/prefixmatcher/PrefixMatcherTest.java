package com.java.myrotuik.prefixmatcher;

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

import com.java.myrotiuk.prefixmatcher.PrefixMatches;
import com.java.myrotiuk.trie.Trie;
import com.java.myrotiuk.trie.rwaytrie.MyRwayTrie;
import com.java.myrotiuk.trie.rwaytrie.Tuple;

import static org.mockito.Mockito.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@RunWith(MockitoJUnitRunner.class)
public class PrefixMatcherTest {

	@Mock
	private Trie trie;
	
	@Mock
	private Tuple tuple;
	
	@InjectMocks
	private PrefixMatches pm;

	@Test
	public void shouldCallAddMethodOfTrieThreeTimes() {
		String str = "do not wory be 	 happy";
		pm.add(str);
		verify(trie, times(3)).add(isA(Tuple.class));
	}

	@Test
	public void shouldCallContainMethodOfTrie() {
		pm.contains("world");
		verify(trie).contains("world");
	}

	@Test
	public void shouldCallDeleteMethodOfTrie() {
		pm.delete("some");
		verify(trie).delete("some");
	}

	@Test
	public void shouldCallSizeMethodOfTrie() {
		pm.size();
		verify(trie).size();
	}

	@Test
	public void shouldReturnWordsForThreeLengthsForSpecificPrefix(){
		Trie trie = new MyRwayTrie();
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
		assertEquals(4, count);//hold 3 length
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionAsPrefixTooSmall(){
		pm.wordsWithPrefix("a");
	}

	@Test
	public void shouldReturnWordsForTwoLengths() {
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
	}
	
	@Test
	public void shouldReturnWordsForOneLengths() {
		Queue<String> q = new LinkedList<>();
		q.offer("have");
		q.offer("hands");
		q.offer("happy");
		q.offer("handle");
		q.offer("happiness");
		q.offer("girl");
		when(trie.wordsWithPrefix("ha")).thenReturn(q);
		
		int count = 0;
		for(String word: pm.wordsWithPrefix("ha", 1)){
			count++;
		}
		assertEquals(1, count);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionAsPrefixTooSmallForLengths(){
		pm.wordsWithPrefix("a", 7);
	}

}
