
//Written by Anthony Osswald 12/4/22
//
//BSTNode for Binary Search Tree (BST) class
//Self-explanatory: has parent, left, and right links
//with getters, setters, and a "has link" method for each
public class BSTNode {

	protected int count = 1;
	
	protected String info;
	protected BSTNode parent, left, right;
	
	protected int level = 0;
	
	
	
	public BSTNode(String info) {
		this.info = info;
		left = null;
		right = null;
		parent = null;

		
	}
	
	
	
	public void setInfo(String info) {
		this.info = info;
	}

	public String getInfo() {
		return info;
	}
	
	
	
	public void setParent(BSTNode parent) {
		this.parent = parent;
	}
	
	public BSTNode getParent() {
		return parent;
	}
	
	public boolean hasParent() {
		if (parent == null) {
			return false;
		}
		return true;
	}
	
	
	
	public void setRight(BSTNode right) {
		this.right = right;
	}
	
	public BSTNode getRight() {
		return right;
	}
	
	public boolean hasRight() {
		if (right == null) {
			return false;
		}
		return true;
	}
	
	
	
	public void setLeft(BSTNode left) {
		this.left = left;
	}
	
	public BSTNode getLeft() {
		return left;
	}
	
	public boolean hasLeft() {
		if (left == null) {
			return false;
		}
		return true;
	}
	
	
	
	public void setLevel(int level)
	{
		this.level = level;
	}
	
	public int getLevel()
	{
		return level;
	}
	
	
	
	public int getCount() {
		return count;
	}
	
	public void updateCount() {
		count++;
	}
	
	
	
}
