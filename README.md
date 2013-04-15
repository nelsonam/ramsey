Computing Ramsey Numbers with Genetic Algorithms
==============

to work on
-------
-  crossover
  - take two random graphs
  - switch some points in the ColorMatrix?
-  implement the actual genetic algorithm part
-  static var for clique size (prompt user) eg R(3,3) R(5,5) etc
-  if there is a graph that gets a 0 fitness (no same colored cliques), save that graph somehow for further inspection

the graph algorithm (McRae's idea)
--------------

1. make a random permutation of the vertices (eg, 3 6 8 23 41 22 11 ...)
2. then insert them into a binary tree 

```java
for(int i=0; i<vertices; i++)
{
    leftcount=0
    rightcount=0
    t=insert(t,nodeArray[perm[i]]) //make a node array with Node objects of all the vertices ahead of time
    if(leftcount>4 || rightcount>4)
    cliques++;
}
```

here is the insert code:

```java
public Node insert(Node t, Node newOne)
{
  if(t==null)
  {
    leftcount++;
    rightcount++;
    return newOne;
  }
  else if(colorMatrix[t.value][newOne.value])
  {
    leftcount++;
    insert(t.left, newOne);
    return t;
  }
  else
  {
    rightcount++;
    insert(t.right,newOne);
    return t;
  }
}
```

use Node objects for inserting into the binary tree:
```java
public Node
{
  Node leftChild;
  Node rightChild;
  int value; //the number of the node (eg, vertex 9)
}
```

Apr 15 : I derped up pretty hard so reset this code back a few commits (testing)