
//Written by Anthony Osswald, finished 12/4/22
//
//Reads input words from a file one string at a time, 
//then formats and adds word to different lists.
//Metrics are taken and printed to the output to measure list efficiency


import java.io.*;
import java.util.*;


public class Main {

	public static void main(String[] args) throws IOException {
		
		//print header
		System.out.println( " #    List Name      Run Time  Vocabulary  Total Words   Key Comp     Ref Chgs    h");
		System.out.println("-- ----------------- --------  ----------  ----------- ------------ ------------ ---");
		
		//initialize scanner
		Scanner input = null;
		String word = null;
		
		//create array of lists
		ListInterface[] Lists = new ListInterface [10];
		
		Lists[0] = new UnsortedList();
		Lists[1] = new AlphabeticalList();
		Lists[2] = new AlphabeticalList2();
		Lists[3] = new SAList1();
		Lists[4] = new SAList2();
		Lists[5] = new SkipList();
		Lists[6] = new Hash1();
		Lists[7] = new Hash2();
		Lists[8] = new Hash3();
		Lists[9] = new BST();
		
		//array of list names to output later
		String[] listNames =  {	" 1 Unsorted          " ,
								" 2 Sorted            " ,
								"2a Sorted Modified   " ,
								" 3 Self-Adj (Front)  " ,
								" 4 Self-Adj (By One) " ,
								" 5 Skip List         " ,
								"6a Hash1             " ,
								"6b Hash2             " ,
								"6c Hash3             " ,
								" 7 BST               " };
		
		//run through lists 6 times
		for (int i = 0; i <= (Lists.length + 1); i++) {
			
			long time = 0;
			double seconds = 0;
			
			//start time stopwatch only after the first loop
			if (i > 0) time = System.nanoTime();
			
			//setup input file and scanner
			File fileInput = new File(args[0]);
			input = new Scanner(fileInput);
			
			//loop forever until break
			while (true) {
				
				//while loop breaks when the input runs out
				try {
					word = input.next();
				} 
				catch (Exception e) {
					break;
				}
					
				//if unformatted word has letters in it then format the word and add it to the list
				for (int j = 0; (j < word.length()); j++) {
					
					if (	((word.charAt(j) >= 'A') && (word.charAt(j) <= 'Z'))	||
							((word.charAt(j) >= 'a') && (word.charAt(j) <= 'z'))	){
						
						word = formatWord(word);
						
						if (i >= 2) Lists[i - 2].add(word);
						break;
					}
				}
				
			}
			
			//format seconds
			if (i >= 1) seconds = ((System.nanoTime() - time) / 1000000000.0);
			
			//print metrics in correct format
			if (i >= 2) {

				System.out.printf("%17s%-8.3f    %-7d   %-9d   %-11d  %-11d  %-2s \n",
				listNames[i - 2], seconds, 
				Lists[i - 2].getDistinctWords(), 
				Lists[i - 2].getTotalWords(),
				Lists[i - 2].getKeyCompare(),
				Lists[i - 2].getRefChanges(),
				Lists[i - 2].getHeight());
				
			}
			
			input.close();
		}
		
	} //end of main
	

	
	public static String formatWord(String word) {
		
		//convert to StringBuilder, format StringBuilder word, then convert back to String
		String formattedWord = removeLeading(removeTrailing(new StringBuilder(word))).toString().toLowerCase();
		
		return formattedWord;
	}
	
	
	
	public static StringBuilder removeLeading(StringBuilder word) {
		
		//At the beginning of the word aka at charAt(0):
		//if not a letter in the Latin alphabet
		
		while (!(	((word.charAt(0) >= 'A') && (word.charAt(0) <= 'Z'))	||
					((word.charAt(0) >= 'a') && (word.charAt(0) <= 'z'))	)){
			
			//then delete the character and run again
			removeLeading(word.deleteCharAt(0));

		}
		
		return word;	
	}
	
	
	
	public static StringBuilder removeTrailing(StringBuilder word) {

		//At the end of the word aka at charAt(word.length() - 1):
		//if not a letter in the Latin alphabet 
		while (!(	(((word.charAt(word.length() - 1)) >= 'A') && ((word.charAt(word.length() - 1)) <= 'Z'))	||
					(((word.charAt(word.length() - 1)) >= 'a') && ((word.charAt(word.length() - 1)) <= 'z'))	)){

			//then delete the character and run again
			removeTrailing(word.deleteCharAt(word.length() - 1));

		}
		
		return word;
	}
	
	
	
	
	

}
