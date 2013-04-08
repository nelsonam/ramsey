import java.util.*;

public class Population
{
    ArrayList<Chromosome> pop;
    public Population(int size)
    {
	pop = new ArrayList<Chromosome>();
	for(int i=0; i<size; i++)
	    {
		pop.add(new Chromosome(G));
	    }
    }

    public int getSize()
    {
	return pop.size();
    }
}