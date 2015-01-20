package edu.ucsc.gameAI.conditions;

import pacman.game.Constants.GHOST;
import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class GhostInRegion implements ICondition {

	private int x1;
	private int y1;
	private int x2;
	private int y2;

	public GhostInRegion(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	@Override
	public boolean test(Game game) {
		for (GHOST ghost : GHOST.values()) {
			int node = game.getGhostCurrentNodeIndex(ghost);
			int ghostx = game.getNodeXCood(node);
			int ghosty = game.getNodeYCood(node);
			if (ghostx >= x1 && ghostx <= x2 && ghosty >= y1 && ghosty <= y2) {
				return true;
			}
		}
		return false;
	}

}
