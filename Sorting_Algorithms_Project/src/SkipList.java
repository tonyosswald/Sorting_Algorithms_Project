
//Written by Anthony Osswald
// finished 11/13/22
//Uses "fast lanes" to search for the correct insertion location faster
//otherwise, relatively similar to other the other linked lists
//searches for word, if found increases word count, if not found adds to 
//bottom lane and randomly creates higher lane nodes

import java.util.Random;

public class SkipList extends BaseList {

	//initializing variables
	
	boolean found = false;
	protected SLNode start;
	protected SLNode end;
	
	protected SLNode currNode;
	
	private Random r;
	protected int height = 1;
	
	
	public SkipList() {
		
		//initializing list
		
		list = null;
		refChanges = 0;
		keyCompare = 0;
		
		start = new SLNode("%start%");
		end = new SLNode("%end%");
		start.makeSentinel();
		end.makeSentinel();
		
		start.setRight(end);
		end.setLeft(start);
		
		r = new Random();
		
		
	}
	
	
	//based on class presentation
	public SLNode search(String word) {
		
		currNode = start;
		
		while(true) {
			//work to the right and down to find the node
			while ( (!currNode.getRight().isSentinel()) && (currNode.getRight().info.compareTo(word) <= 0)) {
				keyCompare++;
				currNode = currNode.getRight();
			}
			//either we found the node, or the word is not in the list yet
			if (currNode.getDown() == null) {
				if (currNode.getInfo().equals(word)) found = true;
				else found = false;
				return currNode;
			}
			//move down to next lane
			currNode = currNode.getDown();
		}
		
	}
	
	
	@Override
	public void add(String word) {
		
		//search for word
		currNode = search(word);
		
		//if found, increase count of word and every node above, and return
		if (found == true) {
			while(true) {
				currNode.updateCount();
				if (currNode.hasUp()) currNode = currNode.getUp();
				else return;
			}
			
		}
		
		//if not found, create a new node
		SLNode newNode = new SLNode(word);
		
		newNode.setRight(currNode.getRight());
		newNode.setLeft(currNode);
		currNode.getRight().setLeft(newNode);
		currNode.setRight(newNode);
		
		refChanges = refChanges + 4;
		
		//then add random amount of fast lane nodes
		while(true) {
			//break randomly
			if (!r.nextBoolean()) break;
			
			//create a new upNode, link it with newNode
			SLNode upNode = new SLNode(word);
			upNode.setDown(newNode);
			newNode.setUp(upNode);
			
			refChanges = refChanges + 2;
			
			
			while(true) {
				
				//move left until an up link is found
				if (!newNode.getLeft().hasUp() && !newNode.getLeft().isSentinel()) {
					newNode = newNode.getLeft();
					continue;
				}
				
				//if no up link is found and were at the sentinel, make a new lane
				if (!newNode.getLeft().hasUp() && newNode.getLeft().isSentinel()) {
					
					//make new start node and link with old start node
					SLNode newStart = new SLNode("%start%");
					newStart.makeSentinel();
					
					newStart.setDown(start);
					start.setUp(newStart);
					
					//make new end node and link with old end node
					SLNode newEnd = new SLNode("%end%");
					newEnd.makeSentinel();
					
					newEnd.setDown(end);
					end.setUp(newEnd);
					
					//link upNode with newStart
					newStart.setRight(upNode);
					newEnd.setLeft(upNode);
					
					//link upNode with newEnd
					upNode.setRight(newEnd);
					upNode.setLeft(newStart);
					
					refChanges = refChanges + 8;
					height++;
					
					start = newStart;
					end = newEnd;
					newNode = upNode;
					
					break;
				}
				
				//link the new upNode to the nodes to it's left and right
				else {
					//link upNode to the node to it's right
					upNode.setRight(newNode.getLeft().getUp().getRight());
					newNode.getLeft().getUp().getRight().setLeft(upNode);
					
					//link upNode to the node to it's left
					newNode.getLeft().getUp().setRight(upNode);
					upNode.setLeft(newNode.getLeft().getUp());
					
					refChanges = refChanges + 4;
					
					newNode = upNode;
					
					break;
				}
				
			}
		}
		
	}
	
	
	@Override
	public String getHeight() {
		String h = "" + height;
		return h;
	}
	
	
	@Override
	public int getDistinctWords() {
		
		int count = 0;
		SLNode p = start;
		
		//start at the bottom lane
		while(true) {
        	if (p.hasDown()) p = p.getDown();
        	else break;
        }
		
		//start at the first word
		p = p.getRight();
		
		while (!p.getInfo().equals("%end%")) {
			count++; 
			p = p.getRight(); 
		} 
		
		return count;
	}
	
	
	//similar to getDistinctWords, which is a count of every node in the
	//bottom lane, but also counts nodes in fast lanes
	public int getTotalNodes() {
		
		int count = 0;
		SLNode p = start;
		SLNode saveNode;
		
		//for each lane
		while(true) {
        	if (p.hasDown()) {
        		saveNode = p;
        		//start at the first word
        		p = p.getRight();
        		
        		while (!p.getInfo().equals("%end%")) {
        			count++; 
        			p = p.getRight(); 
        		}
        		
        		p = saveNode;
        		p = p.getDown();
        		
        	}
        	else break;
        }
		
		count = count + getDistinctWords();
		
		
		return count;
	}
	
	
	@Override
	public int getTotalWords() {
		
		// How many TOTAL words? That's the sum of the counts in each node.
		//
		int count = 0;
		SLNode p = start;
		
		//start at the bottom lane
		while(true) {
        	if (p.hasDown()) p = p.getDown();
        	else break;
        }
		
		//start at the first word
		p = p.getRight();
		
		while (!p.getInfo().equals("%end%")) {
			count += p.getCount(); 
			p = p.getRight(); 
		} 
		
		return count;
	}
	
	
	//prints list, for testing purposes
	@Override
    public void printList()
    {

        System.out.println("");
        SLNode p = start;
        
        while(true) {
        	if (p.hasDown()) p = p.getDown();
        	else break;
        }
        
        SLNode savePoint;

        p = p.getRight();
        while (!p.isSentinel()) {
        	savePoint = p;
        	System.out.print(p.getInfo() + " ");
        	
        	while(p.hasUp()) {
        		
        		System.out.print(p.getInfo() + " ");
        		p = p.getUp();
        	}
        	
        	
        	p = savePoint.getRight();
        	System.out.println("");
        }
    }

	
}