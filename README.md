Computing Ramsey Numbers with Genetic Algorithms
==============

to work on
-------
- IMPORTANT
  - instead of using the same set of edges to check fitness each time, generate `x` random ones
  - http://stackoverflow.com/questions/7918806/finding-n-th-permutation-without-computing-others
-  if there is a graph that gets a 0 fitness (no same colored cliques), save that graph somehow for further inspection
-  change fitness function so it gets `x` random perms instead of the same every time
-  do something with the  "0" graphs


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


if we have `leftcount` or `rightcount` > 4 (for R(5,5)) then that means that a 5 clique has been found somewhere. 

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
