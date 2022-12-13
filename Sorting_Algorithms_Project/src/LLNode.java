
//Written partially by Anthony Osswald, or pulled from class presentations
// finished 11/1/22
//Typical node class which also contains a counter to count how many of 
//the node's word exists. Also has "has link" methods for each link

public class LLNode {
	
	protected String info;
	protected LLNode link;
	protected int count = 1;
	
	public LLNode(String info) {
		this.info = info;
		link = null;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getInfo() {
		return info;
	}
	
	public void setLink(LLNode link) {
		this.link = link;
	}

	public LLNode getLink() {
		return link;
	}
	
	public boolean hasLink() {
		if (link == null) {
			return false;
		}
		return true;
	}
	
	public int getCount() {
		return count;
	}
	
	public void updateCount() {
		count++;
	}
	
}
