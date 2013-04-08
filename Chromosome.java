import java.util.*;

public class Chromosome
{
    boolean[] chromosome;
    int fitness;
    public Chromosome(AdjMatrixGraph G)
    {
	chromosome = new boolean[G.getEdges()];
    }

    public int getFitness()
    {
	return this.fitness;
    }

    public boolean[] mutate()
    {
	//do some stuff to it
	return chromosome;
    }
    
}