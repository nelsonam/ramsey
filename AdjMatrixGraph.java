// this code comes from http://algs4.cs.princeton.edu/41undirected/AdjMatrixGraph.java.html
// I have made modifications to suit my needs

import java.util.Iterator;
import java.util.*;
import java.math.*;


public class AdjMatrixGraph {
    private int V;
    private int E;
    private boolean[][] adj;
    
    //empty graph with V vertices
    public AdjMatrixGraph(int V, boolean complete) {
        if (V < 0) throw new RuntimeException("Number of vertices must be nonnegative");
        this.V = V;
        this.E = 0;
        this.adj = new boolean[V][V];
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
	//gets the number of vertices we want
        int V = Integer.parseInt(args[0]);
	//makes a new graph object
        AdjMatrixGraph G = new AdjMatrixGraph(V);
	//print the graph
        //System.out.println(G);
	//System.out.println("\n");
	//get a random coloring
	ColorMatrix c = new ColorMatrix(G);

	//System.out.println("\nColoring: ");
	//make a new Chromosome (basically just a ColorMatrix)
	Chromosome chr = new Chromosome(c);
	//print our coloring
	//c.printColoring();
	//get fitness (number of same colored cliques of size x (x is the parameter)
	int fit = chr.getFitness(5);
	//System.out.println("Number of same colored triangles: "+fit+"\n");

	//make a new population
	Population pop = new Population(100, G.getVertices());
	pop = Genetics.evolve(pop);
	/*for(Chromosome chrom:pop.getPop())
	    {
		if(chrom.getFitness(5)==0)
		    {
			chrom.getColorMatrix().printColoring();
			System.out.println();
		    }
	    }
	//print the population
	//System.out.println(pop);
    }

}
