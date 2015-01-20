package edu.ucsc.gameAI.conditions;

import pacman.game.Constants.GHOST;
import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class EdibleTime implements ICondition {

	private GHOST ghost;
	private int min;
	private int max;

	public EdibleTime(GHOST ghost, int min, int max) {
		this.ghost = ghost;
		this.min = min;
		this.max = max;
	}

	@Override
	public boolean test(Game game) {
		int time = game.getGhostEdibleTime(ghost);
		return time > min && time < max;
	}

}
