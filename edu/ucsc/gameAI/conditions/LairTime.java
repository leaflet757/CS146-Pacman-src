package edu.ucsc.gameAI.conditions;

import pacman.game.Constants.GHOST;
import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class LairTime implements ICondition {

	private GHOST ghost;
	private int min;
	private int max;
	boolean isInversed = false;

	public LairTime(GHOST ghost, int min, int max) {
		this.ghost = ghost;
		this.min = min;
		this.max = max;
	}

	public LairTime(GHOST ghost, int min, int max, boolean isInversed) {
		this(ghost, min, max);
		this.isInversed = isInversed;
	}

	@Override
	public boolean test(Game game) {
		int time = game.getGhostLairTime(ghost);
		if (isInversed) {
			return !(time > min && time < max);
		} else {
			return time > min && time < max;
		}
	}

}
