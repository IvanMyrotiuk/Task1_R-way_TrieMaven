package com.java.myrotiuk.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import com.java.myrotiuk.prefixmatcher.PrefixMatches;
import com.java.myrotiuk.trie.rwaytrie.MyRwayTrie;

/**
 * Class<code> MyrotiukMain</code> consists <i>main()</i> method where is loaded
 * logic
 *
 * @version 1.0
 * @author Ivan Myrotiuk
 * @since 18-03-2016
 */
public class MyotiukMain {
	public static void main(String[] args) {

		File file = new File("src/test/java/resources/words-333333.txt");
		StringBuilder words = new StringBuilder();
		try (BufferedReader fr = new BufferedReader(new FileReader(file));) {
			String strLine = null;
			while ((strLine = fr.readLine()) != null) {
				words.append(strLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		String finalWords = words.toString();
		// System.out.println(finalWords);

		PrefixMatches pm = new PrefixMatches(new MyRwayTrie());
		System.out.println("number words that was added to our dictionary:" + pm.add(finalWords));
		for (String result : pm.wordsWithPrefix("ha", 2)) {
			System.out.println(result);
		}
	}
}
