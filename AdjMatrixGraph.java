// this code comes from http://algs4.cs.princeton.edu/41undirected/AdjMatrixGraph.java.html
// I have made modifications to suit my needs

import java.util.Iterator;
import java.util.*;
import java.math.*;


public class AdjMatrixGraph {
    private int V;
    private int E;
    private boolean[][] adj;
    private ArrayList<Node> nodes;
    
    //empty graph with V vertices
    public AdjMatrixGraph(int V, boolean complete) {
        if (V < 0) throw new RuntimeException("Number of vertices must be nonnegative");
        this.V = V;
        this.E = 0;
        this.adj = new boolean[V][V];
	this.nodes = new ArrayList<Node>();
    }

    // Complete graph with V vertices
    public AdjMatrixGraph(int V) {
        this(V, true);

	int totalEdges = (V*(V-1))/2;
        // can be inefficient
	for(int i = 0; i<V; i++)
	    {
		for(int j = 0; j<V; j++)
		    {
			if(i!=j) addEdge(i, j);
		    }
	    }
    }

    // number of vertices and edges
    public int getVertices() { return this.V; }
    public int getEdges() { return this.E; }

    public void initNodes(ColorMatrix C)
    {
	for(int k=0; k<this.V; k++)
	    {
		nodes.add(new Node(k, C));
	    }
    }

    // add undirected edge v-w
    public void addEdge(int v, int w) {
        if (!adj[v][w]) this.E++;
        adj[v][w] = true;
        adj[w][v] = true;
    }

    // does the graph contain the edge v-w?
    public boolean contains(int v, int w) {
        return adj[v][w];
    }

    public Node getNode(int index)
    {
	return nodes.get(index);
    }

    // return list of neighbors of v
    public Iterable<Integer> adj(int v) {
        return new AdjIterator(v);
    }

    // support iteration over graph vertices
    private class AdjIterator implements Iterator<Integer>, Iterable<Integer> {
        int v, w = 0;
        AdjIterator(int v) { this.v = v; }

        public Iterator<Integer> iterator() { return this; }

        public boolean hasNext() {
            while (w < V) {
                if (adj[v][w]) return true;
                w++;
            }
            return false;
        }

        public Integer next() {
            if (hasNext()) { return w++;                         }
            else           { throw new NoSuchElementException(); }
        }

        public void remove()  { throw new UnsupportedOperationException();  }
    }

    //gets a permutation of the vertices referred to by index i
    public ArrayList<Node> getPerm(BigInteger index)
    {
	//implementation adapted from 
	//http://stackoverflow.com/questions/7918806/finding-n-th-permutation-without-computing-others
	int j, k = 0;
	//change this to BigInteger - factorials get really BIG!
	BigInteger[] fact = new BigInteger[nodes.size()];
	BigInteger[] perm = new BigInteger[nodes.size()];
       
	ArrayList<Node> newperm = new ArrayList<Node>();
	fact[k] = new BigInteger("1");
	while(++k<nodes.size())
	    fact[k] = fact[k-1].multiply(BigInteger.valueOf(k));

	for(k=0;k<nodes.size(); ++k)
	    {
		perm[k] = index.divide(fact[nodes.size()-1-k]);
		index = index.mod(fact[nodes.size()-1-k]);
	    }

	for(k=nodes.size()-1; k>0; --k)
	    {
		for(j=k-1;j>=0; --j)
		    {
			//use compareTo == -1 or 0
			if((perm[j].compareTo(perm[k])) <= 0) 
			    perm[k] = perm[k].add(BigInteger.valueOf(1));
		    }
	    }
	
	for(k=0; k<nodes.size(); ++k)
	    {
		newperm.add(getNode((perm[k]).intValue()));
	    }
	return newperm;
    }
	

    // string representation of Graph - takes quadratic time
    public String toString() {
        String NEWLINE = System.getProperty("line.separator");
        StringBuilder s = new StringBuilder();
        s.append("V: "+V + " " + " E: "+E + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (int w : adj(v)) {
                s.append(w + " ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

    public static BigInteger nextRandomBigInteger(BigInteger n) {
	Random rand = new Random();
	BigInteger result = new BigInteger(n.bitLength(), rand);
	while( result.compareTo(n) >= 0 ) {
	    result = new BigInteger(n.bitLength(), rand);
	}
	return result;
    }

    // test client
    public static void main(String[] args) {
	//gets the number of vertices we want
        int V = Integer.parseInt(args[0]);
	//makes a new graph object
        AdjMatrixGraph G = new AdjMatrixGraph(V);
	//print the graph
        System.out.println(G);
	System.out.println("\n");
	//get a random coloring
	ColorMatrix c = new ColorMatrix(G);
	//make node objs for each vertex
	G.initNodes(c);
	//make sure we init'ed the nodes right
	for(int a = 0; a<G.getVertices(); a++)
	    {
		System.out.print(G.getNode(a)+" ");
	    }
	System.out.println("\n");
	//get a random perm of the vertices here
	Random rand = new Random();
	//replace this 50 with the number of possible perms
	// # of possible perms == V! (eg 5 vertices -> 5! perms)
	int k=0;
	BigInteger[] fact = new BigInteger[G.getVertices()+1];
	fact[k] = new BigInteger("1");
	while(++k<G.getVertices()+1)
	    fact[k] = fact[k-1].multiply(BigInteger.valueOf(k));
	
	for(int i=0; i<G.getVertices()+1; i++)
	    {
		System.out.println(fact[i]);
	    }
	//ArrayList<Node> newperm = G.getPerm(rand.nextInt(fact[G.getVertices()].intValue()));

	/////////make a big random value
	BigInteger bigrand = nextRandomBigInteger(fact[G.getVertices()]);
	System.out.println("BIG "+bigrand);
	ArrayList<Node> newperm = G.getPerm(bigrand);

	for(Node i:newperm)
	    {
		System.out.print(i+" ");
	    }
	System.out.println("\n");

	System.out.println("\nColoring: ");
	//make a new Chromosome (basically just a ColorMatrix)
	Chromosome chr = new Chromosome(c);
	//print our coloring
	c.printColoring();
	//get fitness (number of same colored cliques of size x (x is the parameter)
	int fit = chr.getFitness(5);
	//System.out.println("Number of same colored triangles: "+fit);

	//make a new population
	Population pop = new Population(10, G.getVertices());
	//print the population
	System.out.println(pop);
    }

}
