
//Written by Anthony Osswald finished 11/1/22
//
//Class for the Unsorted list contains it's specific add method.
//This add method receives a word, iterates through the list to check
//whether the word already exists, and either adds to that words count
//or adds the word to the top of the list.

public class UnsortedList extends BaseList {

	@Override
	public void add(String word) {
		
		currNode = top;
		
		//iterate through the list
		while (currNode != null) {
			
			keyCompare++;
			
			//if the word is the same as the node
			if ((word.compareTo(currNode.getInfo()) == 0)) {
				currNode.updateCount();
				
				return;
			}
			
			//move to next node
			currNode = currNode.getLink();
		}
		
		//word is not in the list, so add it to the top of the list
		LLNode newNode = new LLNode(word);
		newNode.setLink(top);
		top = newNode;
		
		refChanges++;
		
		return;
	}
	
}
