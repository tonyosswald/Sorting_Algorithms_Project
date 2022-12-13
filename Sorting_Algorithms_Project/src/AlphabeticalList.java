
//Written by Anthony Osswald
// finished 11/1/22
//Class for the Alphabetical list contains it's specific add method.
//This add method receives a word, iterates through the list to check
//whether the word already exists, and either adds to that words count
//or places that word where it belongs alphabetically.

public class AlphabeticalList extends BaseList {

	@Override
	public void add(String word) {
		
		currNode = top;
		
		//iterate through the list
		while (currNode != null) {
			
			keyCompare++;
			int comparison = word.compareTo(currNode.getInfo());
			
			//if the word is the same as the node
			if (comparison == 0) {
				currNode.updateCount();
				return;
			}
			
			//if the word belongs after the current node
			if (comparison > 0) {
				
				//if currNode has a link
				if (currNode.hasLink()) {
					
					//if the word belongs before the next node (and not equal to the next node)
					keyCompare++;
					if (word.compareTo(currNode.getLink().getInfo()) < 0) {
						
						LLNode newNode = new LLNode(word);
						newNode.setLink(currNode.getLink());
						currNode.setLink(newNode);
						refChanges = refChanges + 2;
						return;
					}
					
				}
				
				//if currNode has no link then we're at the end of the list, thus add it
				else {
					LLNode newNode = new LLNode(word);
					newNode.setLink(currNode.getLink());
					currNode.setLink(newNode);
					refChanges = refChanges + 2;
					return;
				}
				
			}
			

			currNode = currNode.getLink();
		}
		
		//if I get here then there's no nodes in the list
		LLNode newNode = new LLNode(word);
		newNode.setLink(top);
		top = newNode;
		
		refChanges++;
		
		return;
		
	}

}
