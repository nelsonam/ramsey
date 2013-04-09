import java.util.*;

public class Population
{
    ArrayList<Chromosome> pop;
    public Population(int size)
    {
	pop = new ArrayList<Chromosome>();
	for(int i=0; i<size; i++)
	    {
		//make a new graph
		//then a new coloring for that graph
		//then add that coloring to the pop
		//pop.add(new Chromosome(C));
	    }
    }

    public int getSize()
    {
	return pop.size();
    }
}
