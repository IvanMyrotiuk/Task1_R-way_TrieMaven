package com.java.myrotiuk.rway_trie;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * Class<code> MyrotiukMain</code> consists <i>main()</i> method where is loaded logic
 *
 * @version 1.0
 * @author Ivan Myrotiuk
 * @since 18-03-2016
 */
public class MyotiukMain {
	public static void main(String[] args) {
		
		File file = new File("src/main/java/words-333333.txt");
		StringBuilder words = new StringBuilder();
		try(BufferedReader fr = new BufferedReader(new FileReader(file));){
			String strLine = null;
			while((strLine = fr.readLine()) != null){
				words.append(strLine);
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
		String finalWords = words.toString();
		//System.out.println(finalWords);
		
		PrefixMatches pm3 = new PrefixMatches(new MyTrie());
		System.out.println("number words that was added to our dictionary:"+pm3.add(finalWords));
		for(String result: pm3.wordsWithPrefix("had",1)){
			System.out.println(result);
		}
	
//		
		Trie trie = new MyTrie();
		trie.add(new Tuple("a", 1));
//		trie.add(new Tuple("ab", 1));
//		trie.add(new Tuple("ac", 1));
//		trie.add(new Tuple("ah", 1));
//		trie.add(new Tuple("az", 1));
//		trie.add(new Tuple("ad", 1));
//		trie.add(new Tuple("abc", 1));
//		trie.add(new Tuple("abcd", 1));
//		trie.add(new Tuple("abcde", 1));
//		
//		System.out.println(trie.contains("aa"));
		int count = 0;
		for(String r: trie.words()){//.wordsWithPrefix("a")){
			System.out.println(r);
			count++;
			try {
				Thread.currentThread().sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		System.out.println(count);
		
	}
}
