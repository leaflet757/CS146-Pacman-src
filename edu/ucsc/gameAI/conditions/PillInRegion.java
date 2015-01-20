package edu.ucsc.gameAI.conditions;

import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class PillInRegion implements ICondition {

	private int x1;
	private int y1;
	private int x2;
	private int y2;

	public PillInRegion(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	@Override
	public boolean test(Game game) {
		for (int pillNode : game.getActivePillsIndices()) {
			int pillx = game.getNodeXCood(pillNode);
			int pilly = game.getNodeYCood(pillNode);
			if (pillx >= x1 && pillx <= x2 && pilly >= y1 && pilly <= y2) {
				return true;
			}
		}
		return false;
	}

}
