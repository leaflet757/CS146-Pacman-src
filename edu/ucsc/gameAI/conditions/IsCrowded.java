package edu.ucsc.gameAI.conditions;

import pacman.game.Constants.GHOST;
import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class IsCrowded implements ICondition {

	private final int DISTANCE = 20;
	GHOST ghost;

	public IsCrowded(GHOST ghost) {
		this.ghost = ghost;
	}

	@Override
	public boolean test(Game game) {

		float distance = 0;

		for (GHOST g : GHOST.values()) {
			if (g != ghost) {
				distance += game.getShortestPathDistance(
						game.getGhostCurrentNodeIndex(ghost),
						game.getGhostCurrentNodeIndex(g));
			}
		}
		return (distance / 6) < DISTANCE;
	}
}
