
//Written by Anthony Osswald
// finished 11/13/22
//Skip List node class for the Skip List class
//Self-explanatory, contains up, down, left, and right setters and getters
//also has "has" methods for each link

public class SLNode {
	
	int value;
	protected String info;
	protected SLNode up, down, left, right;
	protected boolean sentinel;
	protected int count = 1;
	
	
	public SLNode(String info) {
		this.info = info;
		up = null;
		down = null;
		left = null;
		right = null;
		
		sentinel = false;
		
	}

	
	
	public void setInfo(String info) {
		this.info = info;
	}

	public String getInfo() {
		return info;
	}
	
	
	
	public void makeSentinel() {
		sentinel = true;
	}
	
	public boolean isSentinel() {
		return sentinel;
	}
	
	
	
	public void setUp(SLNode up) {
		this.up = up;
	}
	
	public SLNode getUp() {
		return up;
	}
	
	public boolean hasUp() {
		if (up == null) {
			return false;
		}
		return true;
	}
	
	
	
	public void setDown(SLNode down) {
		this.down = down;
	}
	
	public SLNode getDown() {
		return down;
	}
	
	public boolean hasDown() {
		if (down == null) {
			return false;
		}
		return true;
	}
	
	
	
	public void setRight(SLNode right) {
		this.right = right;
	}
	
	public SLNode getRight() {
		return right;
	}
	
	public boolean hasRight() {
		if (right == null) {
			return false;
		}
		return true;
	}
	
	
	
	public void setLeft(SLNode left) {
		this.left = left;
	}
	
	public SLNode getLeft() {
		return left;
	}
	
	public boolean hasLeft() {
		if (left == null) {
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
