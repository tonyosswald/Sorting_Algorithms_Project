
//Written by Anthony Osswald
// finished 11/1/22
//Class for this Self Adjusting list contains it's specific add method.
//This add method receives a word, iterates through the list to check
//whether the word already exists, and either adds to that words count
//and moves the counted word to the top of the list, 
//or adds the word to the top of the list.

public class SAList1 extends BaseList {

	@Override
	public void add(String word) {
		
		currNode = top;
		
		//while currNode is not null, iterate through the list
		while (currNode != null) {
			keyCompare++;
			
			//if the word is the same as the node
			if ((word.compareTo(currNode.getInfo()) == 0)) {
				
				//update count
				currNode.updateCount();
				
				//if currNode is the top node then return
				if (currNode == top) return;
				
				//if currNode is not the last node link the previous node to the next node
				//otherwise remove the link from the previous node
				if (currNode.getLink() != null) previousNode.setLink(currNode.getLink());
				else previousNode.setLink(null);
				refChanges++;

				//move the currNode to the top
				currNode.setLink(top);
				top = currNode;
				refChanges++;
				
				return;
				
			} //otherwise continue iteration:
			
			//set previousNode to currNode
			previousNode = currNode;
			//set currNode to next node
			currNode = currNode.getLink();
			
		} //if the word is not in the list:
		
		LLNode newNode = new LLNode(word);
		newNode.setLink(top);
		top = newNode;
		refChanges++;
		
		return;
	}

}