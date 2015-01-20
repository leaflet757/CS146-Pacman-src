package edu.ucsc.gameAI.conditions;

import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class PacmanInRegion implements ICondition {

	private int x1;
	private int y1;
	private int x2;
	private int y2;

	public PacmanInRegion(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	@Override
	public boolean test(Game game) {
		int node = game.getPacmanCurrentNodeIndex();
		int pacx = game.getNodeXCood(node);
		int pacy = game.getNodeYCood(node);
		if (pacx >= x1 && pacx <= x2 && pacy >= y1 && pacy <= y2) {
			return true;
		}
		return false;
	}

}
