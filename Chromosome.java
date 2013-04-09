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

    public int getFitness()
    {
	//all teh loops
	for(int x=0; x<C.getGraph().getVertices(); x++)
	    {
		for(int y=1; y<C.getGraph().getVertices(); y++)
		    {
			for(int z=2; z<C.getGraph().getVertices(); z++)
			    {
				//just my weird way of enumerating some
				//(but not all) cliques
				if(x<y && y<z && (z-y)==1)
				    {
					//if all are same color, add 1
					if(C.getColor(x,y) == C.getColor(y,z))
					    if(C.getColor(z,x) == C.getColor(x,y))
						fitness++;
				    }

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
    
}
