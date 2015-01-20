package edu.ucsc.gameAI;

import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;
import edu.ucsc.gameAI.decisionTrees.binary.IBinaryNode;

public class MoveAwayFromPacman implements IAction, IBinaryNode {

	MOVE move;

	@Override
	public IAction makeDecision(Game game) {
		return this;
	}

	@Override
	public void doAction() {
	}

	@Override
	public MOVE getMove() {
		int i = (int) Math.round(Math.random() * 3);
		move = MOVE.values()[i];
		return move;
	}

	@Override
	public MOVE getMove(GHOST ghost, Game game) {
		move = game.getApproximateNextMoveAwayFromTarget(
				game.getGhostCurrentNodeIndex(ghost),
				game.getPacmanCurrentNodeIndex(),
				game.getGhostLastMoveMade(ghost), DM.PATH);
		return move;
	}

}
