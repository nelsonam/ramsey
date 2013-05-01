import java.util.*;

public class Node
{
    Node leftChild;
    Node rightChild;
    int value;
    Node root;

    int leftCount;
    int rightCount;

    ColorMatrix C; //reference to the colorMatrix that this Node is in

    public Node(int value, ColorMatrix C)
    {
	this.leftChild = null;
	this.rightChild = null;
	this.value = value;
	this.leftCount = 0;
	this.rightCount = 0;
	this.C = C;
    }
    public int getValue()
    {
	return this.value;
    }
    public Node getRight()
    {
	return this.rightChild;
    }
    public Node getLeft()
    {
	return this.leftChild;
    }
    //public Node insert(Node t, Node newOne) -- t is the root?
    public void insert(Node root, Node prev, Node curr)
    {
	if(C.getColor(prev.value,curr.value)) //color 1
	{
	    if(root.leftChild!=null)
	    {
		insert(root.leftChild,prev,curr);
	    }
	    else
	    {
		root.leftChild = curr;
		leftCount++;
	    }
	}
	else
	{
	    if(root.rightChild!=null)
	    {
		insert(root.rightChild,prev,curr);
	    }
	    else
	    {
		root.rightChild = curr;
		rightCount++;
	    }
	}
	//derpface
	/*System.out.println("inserting: "+t+", "+newOne);
	if(t.root == null)
	{
	    System.out.println("root created: "+t);
	    t.root = t;
	    leftCount++;
	    rightCount++;
	    return newOne;
	}
	else if(C.getColor(t.value,newOne.value))
	{
	    System.out.println("inserting left: "+t);
	    leftCount++;
	    t.leftChild = t;
	    insert(t.leftChild, newOne);
	    return t;
	}

	else
	{
	    System.out.println("inserting right: "+t);
	    rightCount++;
	    t.rightChild = t;
	    insert(t.rightChild, newOne);
	    return t;
	    }*/
    }

    @Override
    public String toString()
    {
	return Integer.toString(this.value);
    }
}
