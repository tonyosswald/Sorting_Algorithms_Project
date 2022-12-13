
//Written partially by Anthony Osswald, or pulled from lab packet
// finished 11/1/22
//added printList, getHeight, and getTotalNodes methods

public interface ListInterface {
	
	public void add(String word); // add this word to the linked list
	public long getKeyCompare(); // Get the number of key comparisons
	public long getRefChanges(); // Get the number of reference changes
	public int getDistinctWords(); // Get the # of distinct words on the list
	public int getTotalWords(); // Get the total number of ALL words
	public void printList();
	
	public String getHeight(); //used by Skip List to output list height
	
	public int getTotalNodes(); //used by Skip List to count all nodes
	
}