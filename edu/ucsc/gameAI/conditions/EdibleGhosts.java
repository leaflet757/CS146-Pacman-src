package edu.ucsc.gameAI.conditions;

import edu.ucsc.gameAI.ICondition;
import pacman.game.Constants.GHOST;
import pacman.game.Game;

public class EdibleGhosts implements ICondition {

	public boolean test(Game game) {	
		int minDistance=Integer.MAX_VALUE;
		GHOST minGhost=null;		
				
		for(GHOST ghost : GHOST.values())
			if(game.getGhostEdibleTime(ghost)>0)
			{
				int distance=game.getShortestPathDistance(game.getPacmanCurrentNodeIndex(),game.getGhostCurrentNodeIndex(ghost));
						
				if(distance<minDistance)
				{
					minDistance=distance;
					minGhost=ghost;
				}
			}
		if(minGhost!=null)
			return true;
		return false;
	}
}
