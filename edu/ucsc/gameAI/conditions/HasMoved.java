package edu.ucsc.gameAI.conditions;

import pacman.game.Constants.GHOST;
import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class HasMoved implements ICondition {

	GHOST ghost;
	final int movementTimes;
	int moves;
	
	public HasMoved(GHOST ghost, int movementTimes) {
		this.ghost = ghost;
		this.movementTimes = movementTimes;
		moves = movementTimes;
	}

	@Override
	public boolean test(Game game) {
		if (moves == 0) {
			moves = movementTimes;
			return true;
		} else {
			moves--;
			return false;
		}
	}

}
