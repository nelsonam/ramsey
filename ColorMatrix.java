import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class ColorMatrix {
    private boolean[][] colors;
    private AdjMatrixGraph G;

    //random coloring of graph G
    public ColorMatrix(AdjMatrixGraph G) {
	colors = new boolean[G.getVertices()][G.getVertices()];
	this.G = G;
	Random rand = new Random();
	for(int i=0; i<G.getVertices(); i++)
	    {
		for(int j=0; j<G.getVertices(); j++)
		    {
			boolean color = rand.nextBoolean();
			if(i<j)
			    {
				colors[i][j] = color;
				colors[j][i] = color;
			    }
		    }		
	    }
    }

    public boolean getColor(int x, int y)
    {
	return colors[x][y];
    }

    public boolean isRed(int x, int y)
    {
	//0 = red
	if(!colors[x][y]) return true;
	else return false;
    }

    public boolean isBlue(int x, int y)
    {
	//1 = blue
	if(colors[x][y]) return true;
	else return false;
    }

    public boolean[] makeChromosome()
    {
	for(int i=0; i<G.getVertices(); i++)
	    {
		for(int j=0; j<G.getVertices(); j++)
		    {
			
		    }
	    }
	return null;
    }

    public void printColoring()
    {
	//print
	for(int i=0; i<G.getVertices(); i++)
	    {
		for(int j=0; j<G.getVertices(); j++)
		    {
			if(colors[i][j])
			    System.out.print(1+" ");
			else
			    System.out.print(0+" ");
		    }
		System.out.println();	
	    }

    }

   
}
