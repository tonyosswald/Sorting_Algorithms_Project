
//Written partially by Anthony Osswald, or pulled from lab packet/presentations
// finished 12/4/22
//Contains initialized LLNodes for use in lists, as well as a printList method which prints
//the top 100 words from the list
//Also contains top and isEmpty method from presentations

public abstract class BaseList implements ListInterface
{
	LLNode list;
	long refChanges;
	long keyCompare;
	
	protected LLNode top;
	protected LLNode currNode;
	protected LLNode previousNode;
	protected LLNode prePreviousNode;

	
	BaseList() {
		
		list = null;
		refChanges = 0;
		keyCompare = 0;
		
	}
	
	//prints top 100 words for SAList1 and SAList2
	@Override
	public void printList() {
		currNode = top;
		for (int f = 0; f < 100; f++) {
			System.out.println(currNode.getInfo() + " " + currNode.getCount());
			currNode = currNode.getLink();
		}
	}
	
	//returns empty string, Skip List actually returns something though
	@Override
	public String getHeight() {
		return ("");
	}
	
	//used to output total nodes in Skip List, not needed for other lists
	@Override
	public int getTotalNodes() {
		return 0;
	}
	
	public String top() throws StackUnderflowException {
		// (1) If stack is NOT empty, returns the object from the top of the
		// (2) stack. If stack IS empty, throws StackUnderflowException

		if (isEmpty()) throw new StackUnderflowException("Attempted top() on empty stack");
		else return top.getInfo();
	}
	
	public boolean isEmpty() {
		return (top == null);
	}
	
	
	@Override
	public long getRefChanges() {
		return refChanges;
	}
	
	
	@Override
	public long getKeyCompare() {
		return keyCompare;
	}
	
	
	@Override
	public int getDistinctWords() {
		
		int count = 0;
		LLNode p = top;
		
		while (p != null) {
			// return count;
			count++; //
			p = p.getLink();
		}
		
		return count;
		
	}
	
	
	@Override
	public int getTotalWords() {

		int count = 0;
		LLNode p = top;
		
		while (p != null) {
			count += p.getCount();
			p = p.getLink();
		}
		
		return count;
		
	}

	
}