package edu.ucsc.gameAI.conditions;

import edu.ucsc.gameAI.ICondition;
import pacman.game.Constants.GHOST;
import pacman.game.Game;

public class IsEdible implements ICondition {

	GHOST gghost;
	boolean invert = false;
	
	public IsEdible(GHOST ghost) {
		gghost = ghost;
	}
	
	public IsEdible(GHOST ghost, boolean invertTest) {
		this(ghost);
		invert = invertTest;
	}

	public boolean test(Game ggame) {
		
		if (!invert) {
			return ggame.isGhostEdible(gghost);
		} else {
			return !ggame.isGhostEdible(gghost);
		}
	}
}
