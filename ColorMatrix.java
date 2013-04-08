import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class ColorMatrix {
    private boolean[][] colors;
    
    //random coloring of graph G
    public ColorMatrix(AdjMatrixGraph G) {
	colors = new boolean[G.getVertices()][G.getVertices()];
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
