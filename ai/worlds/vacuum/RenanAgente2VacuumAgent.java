package ai.worlds.vacuum;

import java.util.Vector;
/**
 * A vacuum agent which reacts to its percepts.
 * @author Jill Zimmerman -- jill.zimmerman@goucher.edu
 *
 */
public class RenanAgente2VacuumAgent extends VacuumAgent
{
	boolean saindoCasa = true, virouEsq = false, vaiVirarEsq = false,
			vaiBaixar = false, vaiMoverDireita = false, vaiSubir = false,
			prontoParaRetornar = false;
	
    public void determineAction()
    {
		Vector p = (Vector) percept;
		
		if (p.elementAt(1) == "dirt") action = "suck";
		
		else if (p.elementAt(2) == "home" && !saindoCasa) 
		{
			action = "shut-off";
		}
		  
		else if (p.elementAt(0) == "bump") //sugestão: usar operador lógico
		{
			if(vaiSubir)
			{
				action = "turn left";
				vaiSubir = false;
			}
			else if(virouEsq)
			{
				action = "turn left";
				vaiVirarEsq = true;
			}
			else
				action = "turn left";
		    	virouEsq = true;
		}		
		
		else if (vaiVirarEsq)
		{
			action = "forward";
			vaiVirarEsq = false;
			vaiBaixar = true;
		}
		else if(vaiBaixar)
		{
			action = "turn left";
			prontoParaRetornar = true;
			vaiBaixar = false;
		}
		else if(super.body.loc.y == 2 && prontoParaRetornar)
		{
			action = "turn right";
			vaiMoverDireita = true;
			prontoParaRetornar = false;
		}
		else if(vaiMoverDireita)
		{
			action = "forward";
			vaiMoverDireita = false;
			vaiSubir = true;
		}
		else if (vaiSubir)
		{
			action = "turn right";
			vaiSubir = false;
		}
		else
		{
			action = "forward";
			saindoCasa = false;
		}
	}
}