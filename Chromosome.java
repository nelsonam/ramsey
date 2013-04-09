import java.util.*;

public class Chromosome
{
    boolean[][] chromosome;
    int fitness;
    ColorMatrix C;
    public Chromosome(ColorMatrix C)
    {
	this.C = C;
	this.fitness = 0;
	chromosome = C.getColorMatrix();
    }

    public int getFitness()
    {
	int x = 0;
	int y = 1;
	int z = 2;
	
	for(x=0; x<C.getGraph().getVertices(); x++)
	    {
		for(y=1; y<C.getGraph().getVertices(); y++)
		    {
			for(z=2; z<C.getGraph().getVertices(); z++)
			    {
				if(x<y && y<z && (z-y)==1)
				    {
					if(C.getColor(x,y) == C.getColor(y,z))
					    if(C.getColor(z,x) == C.getColor(x,y))
						fitness++;
				    }

			    }
		    }
	    }	
	return this.fitness;
    }

    public boolean[][] mutate()
    {
	//do some stuff to it
	return chromosome;
    }
    
}
