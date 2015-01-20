package edu.ucsc.gameAI.conditions;

import pacman.game.Constants.GHOST;
import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

//Checks whether any inedible ghosts are too close
public class InedibleGhostNearby implements ICondition {

	private int min_distance;
	private int ipac;

	public InedibleGhostNearby(int distT, int ipacT) {
		this.min_distance = distT;
		this.ipac = ipacT;
	}

	@Override
	public boolean test(Game game) {
		for (GHOST ghost : GHOST.values())
			if(game.getGhostEdibleTime(ghost)==0 && game.getGhostLairTime(ghost)==0)
				if(game.getShortestPathDistance(ipac,game.getGhostCurrentNodeIndex(ghost))<min_distance)
					return true;
		return false;
	}

}
