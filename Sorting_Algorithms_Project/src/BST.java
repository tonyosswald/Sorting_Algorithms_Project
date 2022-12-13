
//Written by Anthony Osswald 12/4/22
//
// Typical binary search tree
// adds new words to either left or right child depending on if it comes
// before or after parent node
// also has minimum, successor, traversal, and print methods

public class BST extends BaseList {

	BSTNode root;
	BSTNode currNode;
	BSTNode newNode;
	
	int height = 0;
	int vocabCount = 0;
	int wordCount = 0;
	
	@Override
	public void add(String word) {
		
		//if there is no tree yet
		if (root == null) {
			root = new BSTNode(word);
			root.setLevel(0);
			refChanges++;
			vocabCount++;
			return;
		}
		
		//search for word
		currNode = root;
		currNode = search(word);
		//either found word or need to add to tree
		int comp = word.compareTo(currNode.getInfo());
		keyCompare++;
		
		
		if (comp == 0) {
			currNode.updateCount();
			
		}
		//if new word is before parent node
		else if (comp < 0) {
			newNode = new BSTNode(word);
			currNode.setLeft(newNode);
			newNode.setParent(currNode);
			
			//check and set current tree height
			currNode.getLeft().setLevel(currNode.getLevel() + 1);
			if (currNode.getLeft().getLevel() > height) height = currNode.getLeft().getLevel();
			refChanges++;
			vocabCount++;
		}
		//if new word is after parent  node
		else {
			newNode = new BSTNode(word);
			currNode.setRight(newNode);
			newNode.setParent(currNode);
			
			//check and set current tree height
			currNode.getRight().setLevel(currNode.getLevel() + 1);
			if (currNode.getRight().getLevel() > height) height = currNode.getRight().getLevel();
			refChanges++;
			vocabCount++;
		}
		
		return;
	}
	
	
	public BSTNode successor(BSTNode node) {
		
		//find first word in the list with minimum
		if (node.hasRight()) return minimum(node.getRight());

		//find the next node after that
		while (node.hasParent()) {
			if (node == node.getParent().getRight()) node = node.getParent();
			else return node.getParent();
		}
		
		return node;
	}
	
	
	public BSTNode minimum(BSTNode node) {
		
		//find first word in the list
		while (node.hasLeft()) {
			node = node.getLeft();
		}
		
		return node;
	}
	
	@Override
	public void printList() {
		
		//start at root
		BSTNode node = minimum(root);
		//print first 100 words
		for (int i = 0; i < 100; i++) {
			System.out.println(node.getInfo() + " " + node.getCount());
			node = successor(node);
		}
		
	}
	
	
	@Override
	public int getDistinctWords() {
		return vocabCount;
	}
	
	
	@Override
	public int getTotalWords() {
		traverseCount(root);
		return wordCount;
	}
	
	public void traverseCount(BSTNode node) {
		
		//traverse whole tree, add each nodes count to word count
		if (node.getLeft() != null) traverseCount(node.getLeft());
		
		wordCount = wordCount + node.getCount();
		
		if (node.getRight() != null) traverseCount(node.getRight());
	}
	
	
	@Override
	public String getHeight() {
		return height + "";
	}
	
	
	public BSTNode search(String word) {
		
		BSTNode pointer = root;
		
		//while not a leaf
		while (pointer.hasLeft() || pointer.hasRight()) {	
			
			int comp = pointer.getInfo().compareTo(word);
			keyCompare++;
			
			//node found
			if (comp == 0) return pointer;
			
			//if new word is before the current node, go left
			else if (comp > 0) {
				if (pointer.getLeft() != null) pointer = pointer.getLeft();
				else return pointer;
			}
			
			//if new word is after the current node, go right
			else {
				if (pointer.getRight() != null) pointer = pointer.getRight();
				else return pointer;
			}
			
		}
		
		return pointer;
	}
	
	
	

}
