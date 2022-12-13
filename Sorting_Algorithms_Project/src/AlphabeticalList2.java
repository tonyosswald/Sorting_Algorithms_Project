
//Written by Anthony Osswald
// finished 11/13/22
//Almost exactly the same as AlphabeticalList, except it uses a save node
//to determine whether to search for the word starting from the top or 
//starting after the previously added word.

public class AlphabeticalList2 extends BaseList {
	
	LLNode saveNode;
	
	@Override
	public void add(String word) {
		
		currNode = top;
		
		//if new word comes after the saveNode from the previous word
		if (saveNode != null) {
			if ((word.compareTo(saveNode.getInfo()) >= 0)) {
				//begin at the saveNode, rather that from the top
				currNode = saveNode;
			}
		}
		
		
		//iterate through the list
		while (currNode != null) {
			
			keyCompare++;
			int comparison = word.compareTo(currNode.getInfo());
			
			//if the word is the same as the node
			if (comparison == 0) {
				currNode.updateCount();
				
				saveNode = currNode;
				
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
						
						saveNode = currNode;
						
						return;
					}
					
				}
				
				//if currNode has no link then we're at the end of the list, thus add it
				else {
					LLNode newNode = new LLNode(word);
					newNode.setLink(currNode.getLink());
					currNode.setLink(newNode);
					refChanges = refChanges + 2;
					
					saveNode = currNode;
					
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
