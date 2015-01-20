package edu.ucsc.gameAI.conditions;

import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class TimeOfLastGlobalReversal implements ICondition {

	private int min;
	private int max;

	public TimeOfLastGlobalReversal(int min, int max) {
		this.min = min;
		this.max = max;
	}

	@Override
	public boolean test(Game game) {
		int time = game.getTimeOfLastGlobalReversal();
		if (time < 0) {
			time = 0;
		}
		return (time >= min && time <= max);
	}

}
