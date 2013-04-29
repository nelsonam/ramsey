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
    public Node insert(Node t, Node newOne)
    {
	System.out.println("inserting: "+t+", "+newOne);
	if(t.root.equals(null) || t.equals(null))
	{
	    t.root = t;
	    leftCount++;
	    rightCount++;
	    return newOne;
	}
	else if(C.getColor(t.value,newOne.value))
	{
	    leftCount++;
	    insert(t.leftChild, newOne);
	    return t;
	}

	else
	{
	    rightCount++;
	    insert(t.rightChild, newOne);
	    return t;
	}
    }

    @Override
    public String toString()
    {
	return Integer.toString(this.value);
    }
}
