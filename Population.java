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
    
    public Chromosome getWorst()
    {
	Chromosome worst = pop.get(0);
	for(Chromosome c:pop)
	    {
		if(c.getFitness(5)>worst.getFitness(5))
		    worst = c;
	    }
	return worst;
    }

    public int getWorstPosition()
    {
	int worst = 0;
	for(int i=0; i<pop.size(); i++)
	    {
		if(pop.get(i).getFitness(5)>pop.get(worst).getFitness(5))
		    worst = i;
	    }
	return worst;
    }

    public Chromosome getBest()
    {
	Chromosome best = pop.get(0);
	for(Chromosome c:pop)
	    {
		if(c.getFitness(5)<best.getFitness(5))
		    best = c;
	    }
	return best;
    }

    public int getBestPosition()
    {
	int best = 0;
	for(int i=0; i<pop.size(); i++)
	    {
		if(pop.get(i).getFitness(5)<pop.get(best).getFitness(5))
		    best = i;
	    }
	return best;
    }


    @Override
    public String toString()
    {
	int i = 0;
	String p="";
	for(Chromosome c:pop)
	    {
		//p+="Fitness for graph "+i+": "+c.getFitness(5);
		p+=c.getFitness(5);
		//if(c.getFitness(5)==0)
		    //    c.getColorMatrix().printColoring();
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
    public ArrayList<Chromosome> getPop()
    {
	return this.pop;
    }
}
