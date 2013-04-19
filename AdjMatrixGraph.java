// this code comes from http://algs4.cs.princeton.edu/41undirected/AdjMatrixGraph.java.html
// I have made a few modifications to suit my needs

import java.util.Iterator;
import java.util.*;


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

	for(int k=0; k<V; k++)
	    {
		nodes.add(new Node(k));
	    }
    }

    // number of vertices and edges
    public int getVertices() { return this.V; }
    public int getEdges() { return this.E; }


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
    public ArrayList<Node> getPerm(int index)
    {
	//implementation adapted from 
	//http://stackoverflow.com/questions/7918806/finding-n-th-permutation-without-computing-others
	int j, k = 0;
	int[] fact = new int[nodes.size()];
	int[] perm = new int[nodes.size()];
       
	ArrayList<Node> newperm = new ArrayList<Node>();

	fact[k] = 1;
	while(++k<nodes.size())
	    fact[k] = fact[k-1]*k;

	for(k=0;k<nodes.size(); ++k)
	    {
		perm[k] = index/fact[nodes.size()-1-k];
		index = index%fact[nodes.size()-1-k];
	    }

	for(k=nodes.size()-1; k>0; --k)
	    {
		for(j=k-1;j>=0; --j)
		    {
			if(perm[j]<=perm[k])
			    perm[k]++;
		    }
	    }
	
	for(k=0; k<nodes.size(); ++k)
	    {
		newperm.add(getNode(perm[k]));
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


    // test client
    public static void main(String[] args) {
        int V = Integer.parseInt(args[0]);
        AdjMatrixGraph G = new AdjMatrixGraph(V);
        System.out.println(G);
	System.out.println("\n");
	for(int a = 0; a<G.getVertices(); a++)
	    {
		System.out.print(G.getNode(a)+" ");
	    }
	System.out.println("\n");
	//get a random perm of the vertices here
	Random rand = new Random();
	//replace this 50 with the number of possible perms
	ArrayList<Node> newperm = G.getPerm(rand.nextInt(50));
	for(Node i:newperm)
	    {
		System.out.print(i+" ");
	    }
	System.out.println("\n");

	System.out.println("\nColoring: ");
	ColorMatrix c = new ColorMatrix(G);
	Chromosome chr = new Chromosome(c);
	c.printColoring();
	int fit = chr.getFitness(5);
	//System.out.println("Number of same colored triangles: "+fit);

	Population pop = new Population(10, G.getVertices());
	System.out.println(pop);
    }

}
