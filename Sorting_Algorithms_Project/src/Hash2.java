
//Written by Anthony Osswald 12/4/22
//
//lists are functionally the same as the unsorted list, however a hashing function
//determines which list to put the word in
//
public class Hash2 extends BaseList {

	
	LLNode[] hashTable = new LLNode[256];
	
	@Override
	public void add(String word) {
		
		int hash = hash(word);
		
		//if hash table location doesn't have any nodes yet
		if (hashTable[hash] == null) {
			hashTable[hash] = new LLNode(word);
			return;
		}
		
		currNode = hashTable[hash];
		
		//iterate through the list
		while (currNode != null) {
			
			keyCompare++;
			
			//if the word is the same as the node update count
			if ((word.compareTo(currNode.getInfo()) == 0)) {
				currNode.updateCount();
				
				return;
			}
			
			//move to next node
			currNode = currNode.getLink();
		}
		
		//word is not in the list, so add it to the top of the list
		LLNode newNode = new LLNode(word);
		newNode.setLink(hashTable[hash]);
		hashTable[hash] = newNode;
		
		refChanges++;
		
	}
	
	
	int hash(String word) {
		//hash 2 hashes using only the first letter
		
		StringBuilder newWord = new StringBuilder(word);
		int hash = newWord.charAt(0) % 256;
		return hash;
	}
	
	
	@Override
	public int getDistinctWords() {

		int count = 0;
		
		for (int i = 0; i < 256; i++) {
			LLNode p = hashTable[i];
			while (p != null) {
				count++;
				p = p.getLink();
			}
		}
		
		return count;
	}
	
	
	@Override
	public int getTotalWords() {
		
		int count = 0;

		for (int i = 0; i < 256; i++) {
			LLNode p = hashTable[i];
			while (p != null) {
				count += p.getCount();
				p = p.getLink();
			}
		}
		
		return count;
	}
	
	
	
	@Override
	public void printList() {
		
		int[] hashData = new int[256];
		int minimum = 1000000;
		int maximum = 0;
		
		int totalCount = 0;
		int average;
		
		double deviationSum = 0;
		double variance;
		double SD = 0;
		
		for (int i = 97; i < 123; i++) {
			int count = 0;
			
		//count all the nodes in the current list:
			//get list head
			LLNode p = hashTable[i];
			
			while (p != null) {
				count++;
				p = p.getLink();
			}
			
			//add count to total count for finding average
			totalCount += count;
			
			//put data into array for finding standard deviation
			hashData[i] = count;
			
			System.out.println(hashData[i]);
			
			//check if minimum
			if ((count != 0) && (count < minimum)) minimum = count;
			//check if maximum
			if (count > maximum) maximum = count;
		
		}
		
	//Population Standard Deviation:
		//find the average
		average = totalCount / 26;
		
		//sum the deviations
		for (int i = 97; i < 123; i++) {
							//deviation equation
			deviationSum += Math.pow(hashData[i] - average, 2);
		}
		
		//calculate the variance
		variance = deviationSum / 26;
		
		//calculate the standard deviation
		SD = Math.sqrt(variance);
		
		//print results
		System.out.println("/////////////////////////////////");
		
		System.out.println("Min: " + minimum);
		System.out.println("Max: " + maximum);
		System.out.println("Average: " + average);
		System.out.println("STD DEV: " + SD);
		
		System.out.println("/////////////////////////////////");
		
		
	}
	
	

}
