import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/*(10 points)
1. Write a simple program that uses a HashSet to find and print duplicate words 
in an input stream (either prompt the user for a text file or user input from the console).
*/


public class HashSetDupes {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//create hashset
		HashSet<String> setWords = new HashSet<String>();
		ArrayList<String> dupeWords = new ArrayList<String>();
		
		
		//get input from user - either file or user input
		
		System.out.println("Please enter a sentance");
		Scanner S = new Scanner(System.in);
		String text = S.nextLine();
		
		Scanner T = new Scanner(text);
		//add words to hashset
		while(T.hasNext()){
			String nextWord = T.next();
			if(setWords.add(nextWord) == false && dupeWords.indexOf(nextWord) <0){dupeWords.add(nextWord);}
			else{setWords.add(nextWord);}
		}
		
		for (int i =0; i<dupeWords.size(); i++){
			System.out.println(dupeWords.get(i));
		}

	}//end main

}//end class
