import java.util.*;

public class Node
{
    Node leftChild;
    Node rightChild;
    int value;

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
	if(t.equals(null))
	{
	    leftCount++;
	    rightCount++;
	    return newOne;
	}

	//if colorMatrix == 1
	// left++
	// insert(t.left, newOne);
	// return t;

	//else
	// right++
	// insert(t.right, newOne);
	// return t;
	return t;
    }

    @Override
    public String toString()
    {
	return Integer.toString(this.value);
    }
}
