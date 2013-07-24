// this code comes from http://algs4.cs.princeton.edu/41undirected/AdjMatrixGraph.java.html
// I have made modifications to suit my needs

import java.util.Iterator;
import java.util.*;
import java.math.*;
import java.io.*;


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

    //takes a file (in the buffreader) and loads it into a population
    //for pre-formed pops, not random
    public static Population graphLoad(BufferedReader b, AdjMatrixGraph G)
    {
	Population p = new Population();
	ArrayList<Chromosome> poplist = new ArrayList<Chromosome>();
	int linenum = 0;
	try{
	String read = b.readLine();
	boolean[][] c = new boolean[G.getVertices()][G.getVertices()]; //holds the colors in a matrix

	//while((read.contains("0") || read.contains("1")) && read != null)
	while(read != null)
	    {
		//we want only the lines with numbers, this is how we will break it up
		if((read.contains("0") || read.contains("1")))
		    {
			//System.out.println(read);
			for(int pos = 0; pos<read.length(); pos++)
			    {
				if(read.charAt(pos) == '0')
				    {
					//use these positions to set up a colormatrix
					//System.out.println("0 at position "+linenum+","+pos/2);

					//load these values into a matrix
					c[linenum][pos/2] = false; //0
				    }
				else if(read.charAt(pos) == '1')
				    {
					//System.out.println("1 at position "+linenum+","+pos/2);
					
					c[linenum][pos/2] = true; //1
				    }
				else
				    {
					//other stuff
				    }
			    }
			//gets the next line
			read = b.readLine();
			linenum++;
		    }
		else
		    {
			//testing values of the matrix
			/*for(int first=0; first<c.length; first++)
			    {
				for(int second=0; second<c.length; second++)
				    {
					System.out.print(c[first][second]+" ");
					
				    }
				System.out.println();
				}*/

			//end of a block, that means load in the matrix to an object
			ColorMatrix cm  = new ColorMatrix(G, c);
			//cm.printColoring();
			Chromosome chro = new Chromosome(cm);
			//add this Chromosome to a list so we can have a pop at the end
			poplist.add(chro);
			
			//reset to read in a new block of nums
			linenum = 0;
			//reset the matrix
			/*for(int a = 0; a<G.getVertices(); a++)
			    {
				for(int d = 0; d<G.getVertices(); d++)
				    {
					c[a][d] = false;
				    }
				    }*/

			read = b.readLine();
		    }
	    }
	//System.out.println(poplist);
	for(Chromosome chromo: poplist)
	    {
		//c.getColorMatrix().printColoring();
		//System.out.println("\n");
		p.addChromosome(chromo);
	    }
	}catch(Exception e)
	    {e.printStackTrace(System.out);}
	return p;
    }

    // test client
    public static void main(String[] args) {
	long startTime = System.nanoTime();

	//gets the number of vertices we want
        int V = Integer.parseInt(args[0]);
	//makes a new graph object
        AdjMatrixGraph G = new AdjMatrixGraph(V);
	//after this, we want to check if we have a file to pull from
	System.out.println("if you have a data file, enter the name now:");
	Scanner in = new Scanner(System.in);
	String file = in.nextLine();
	BufferedReader br = null;
	DataInputStream data = null;
	try{
	    FileInputStream fstream = new FileInputStream(file);
	    data = new DataInputStream(fstream);
	    br = new BufferedReader(new InputStreamReader(data));
	}catch(Exception e){
	    System.out.println("file didn't work");
	}
	Population pop = null;
	if(!br.equals(null))
	    {
		pop = graphLoad(br, G); //load in the data from the file
		System.out.println(pop);
		try{
		    data.close();
		}catch(Exception e)
		    {}
		System.exit(1); //for testing purposes, no need to do all the other stuff yet
	    }
	
	else{
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
	    pop = new Population(200, G.getVertices());
	}
	//begin the mating process!!!
	int gen = 0;
	while(pop.getWorst().getFitness(5)>0)
	//while(gen<10)
	    {
		System.out.println("Generation "+gen);
		System.out.println("=================");
		pop = Genetics.evolve(pop);
		System.out.println(pop);
		System.out.println("\n");
		gen++;
	    }
	
	System.out.println("Took "+gen+" generations");
	long endTime = System.nanoTime();
	long elapsed = endTime-startTime;
	double seconds = (double)elapsed / 1000000000.0;
	double minutes = (double)seconds / 60;
	System.out.println("Took "+(seconds) + " s"); 
	System.out.println("Took "+(minutes) + " m"); 

	//print the "0s" and their colorings to a file
	//do further testing on these
	PrintWriter writer = null;
	int number = 0;
	System.out.println(pop);
	try{
	    writer = new PrintWriter("datazeroes.txt", "UTF-8");
	    for(Chromosome chrom:pop.getPop())
		{
		//if(chrom.getFitness(5)==0)
		//    {
			//chrom.getColorMatrix().printColoring();
			//System.out.println();

		    //number++;
		    //System.out.println(number);
		    writer.println(chrom.getColorMatrix().getColoring());
		    writer.println();

			//	    }
		}
	    writer.close();
	    System.out.println("possible zeroes dumped");
	}
	catch(Exception e)
	    {
	        System.out.println("fail");
	    }
	//print the population
	//System.out.println(pop);
    }

}
