import java.util.*;

public class Genetics
{
    private static double mutationRate = 0.05;

    public static Population evolve(Population p)
    {
	for(int times = 0; times<10; times++)
	   {
		//parents
		Chromosome mom = selectParent(p);
		Chromosome pop = selectParent(p);
		//crossover
		Chromosome baby = crossover(mom,pop);
		//if it is better add it
		if(baby.getFitness(5)<p.getWorst().getFitness(5))
		    {
			p.addChromosome(p.getWorstPosition(),baby);
		    }
	   }
	//mutate
	for(int j=0; j<p.getSize(); j++)
	    {
		mutation(p.getChromosome(j));
	    }
	return p;
    }
    public static void mutation(Chromosome c)
    {
	//will this one get mutated?
	if(Math.random()<mutationRate)
	    {
		//here we find a first and second random position in the chromosome
		int pos1_x = (int)(c.getColorMatrix().getGraph().getVertices()*Math.random());
		int pos1_y = (int)(c.getColorMatrix().getGraph().getVertices()*Math.random());

		int pos2_x = (int)(c.getColorMatrix().getGraph().getVertices()*Math.random());
		int pos2_y = (int)(c.getColorMatrix().getGraph().getVertices()*Math.random());

		//swap these two elements
		boolean pos1_color = c.getColorMatrix().getColor(pos1_x,pos1_y);
		boolean pos2_color = c.getColorMatrix().getColor(pos2_x,pos2_y);

		c.getColorMatrix().setColor(pos1_x,pos1_y,pos2_color);
		c.getColorMatrix().setColor(pos2_x,pos2_y,pos1_color);
	    }
    }
    public static Chromosome crossover(Chromosome mom, Chromosome pop)
    {
	//take the colormatrix's and swap some stuffs around
	ColorMatrix m = mom.getColorMatrix();
	ColorMatrix p = pop.getColorMatrix();
	
	//just get the graph we're working with
	AdjMatrixGraph g = mom.getColorMatrix().getGraph();

	ColorMatrix babby = new ColorMatrix(g);
	
	for(int i = 0; i<g.getVertices(); i++)
	    {
		for(int j=0; j<g.getVertices(); j++)
		    {
			if(i<j)
			    {
				double random = Math.random();
				//if <50% swap in from mom
				if(random<0.5)
				    {
					babby.setColor(i,j, m.getColor(i,j));
				    }
				//if >50% swap in from pop
				if(random>0.5)
				    {
					babby.setColor(i,j,p.getColor(i,j));
				    }
				//make sure you set [i,j] and [j,1] as the same thing
				
			    }
		    }
	    }
	return new Chromosome(babby);
    }
    public static Chromosome selectParent(Population p)
    {
	//gets a random parent
	int random = (int)(Math.random()*p.getSize());
	//return p.getChromosome(random);

	//gets the best parents evar!!!1!1!
	double chance = Math.random();
	if(chance>0.5)
	    {
		return p.getBest();	
	    }
	else if(chance<0.5)
	    {
		return p.getChromosome(random);	
	    }

	return p.getBest();
    }
}
