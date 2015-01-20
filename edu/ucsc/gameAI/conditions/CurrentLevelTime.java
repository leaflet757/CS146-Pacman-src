package edu.ucsc.gameAI.conditions;

import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class CurrentLevelTime implements ICondition {

	private int min;
	private int max;

	public CurrentLevelTime(int min, int max) {
		this.min = min;
		this.max = max;
	}

	@Override
	public boolean test(Game game) {
		int time = game.getCurrentLevelTime();
		return time > min && time < max;
	}

}
