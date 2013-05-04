import java.util.*;

public class Population
{
    ArrayList<Chromosome> pop;
    public Population(int size, int graphsize)
    {
	pop = new ArrayList<Chromosome>();
	for(int i=0; i<size; i++)
	    {
		//make a new graph
		AdjMatrixGraph G = new AdjMatrixGraph(graphsize);
		//then a new coloring for that graph
		ColorMatrix C = new ColorMatrix(G);
		//then add that coloring to the pop
		pop.add(new Chromosome(C));
	    }
    }

    public int getSize()
    {
	return pop.size();
    }
    
    @Override
    public String toString()
    {
	int i = 0;
	String p="";
	for(Chromosome c:pop)
	    {
		p+="Fitness for graph "+i+": "+c.getFitness(3);
		p+="\n";
		i++;
	    }
	return p;
    }

    public Chromosome getChromosome(int index)
    {
	return pop.get(index);
    }
    public void addChromosome(int index, Chromosome c)
    {
	//if element at index exists replace it
	if(pop.get(index) != null)
	    pop.set(index, c);
	//otherwise just add it
	else
	    pop.add(c);
    }
}
