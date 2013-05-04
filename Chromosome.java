import java.util.*;

public class Chromosome
{
    //    boolean[][] chromosome;
    int fitness;
    ColorMatrix C;
    public Chromosome(ColorMatrix C)
    {
	this.C = C;
	this.fitness = 0;
	//	chromosome = C.getColorMatrix();
    }

    public int getFitness(int n) //n-clique
    {
	//we really only need two loops
	//loop one (i in 0-(V-(n-1))) : V=vertices, n=n-clique
	//loop two (j in 1-(V-(n-2))) 
	//then each vertex after that is just j+1, j+2, j+3, ...

	//eg 0 1 2 3 4
	//   0 2 3 4 5
	//   0 3 4 5 6
	//   ...
	//   1 2 3 4 5
	//   1 3 4 5 6
	//   ...
	int[] clique = new int[n]; // to hold the vertices we want to make a clique with
	for(int x=0; x<C.getGraph().getVertices()-(n-1); x++)
	    {
		for(int y=1; y<C.getGraph().getVertices()-(n-2); y++)
		    {
			if(x!=y && x<y)
			    {
				clique[0] = x;
				clique[1] = y;
				int yplus = 1;
				for(int i=2; i<n; i++)
				    {
					clique[i] = y+yplus;
					yplus++;
				    }
				//test if all in the clique are the same color
				/*if all are same color, add 1
				if(C.getColor(x,y) == C.getColor(y,z))
				if(C.getColor(z,x) == C.getColor(x,y))
				fitness++;*/
				boolean same = true;
				boolean first = C.getColor(clique[0],clique[1]);
				for(int j=1; j<n-1; j++)
				    {
					if(C.getColor(clique[j],clique[j+1]) != first)
					    {
						same=false;
						break;
					    }
				    }
				if(same)
				    fitness++;
			    }
		    }
	    }	
	return this.fitness;
    }

    public ColorMatrix mutate()
    {
	//do some stuff to it
	return this.C;
    }
    
    public ColorMatrix getColorMatrix()
    {
	return this.C;
    }
    
}
