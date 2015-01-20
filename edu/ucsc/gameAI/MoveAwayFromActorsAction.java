package edu.ucsc.gameAI;

import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;
import edu.ucsc.gameAI.decisionTrees.binary.IBinaryNode;

public class MoveAwayFromActorsAction implements IAction, IBinaryNode {

	MOVE move;

	@Override
	public IAction makeDecision(Game game) {
		return this;
	}

	@Override
	public void doAction() {
	}

	/**
	 * Use overloaded method getMove(GHOST, game) to get precise moves
	 * 
	 * @return a random move, does not check if valid move
	 */
	@Override
	public MOVE getMove() {
		int i = (int) Math.round(Math.random() * 3);
		move = MOVE.values()[i];
		return move;
	}

	@Override
	public MOVE getMove(GHOST ghost, Game game) {

		int closeGhostNode = -1;
		int distance = Integer.MAX_VALUE;

		for (GHOST g : GHOST.values()) {
			if (g != ghost) {
				int measure = game.getShortestPathDistance(
						game.getGhostCurrentNodeIndex(ghost),
						game.getGhostCurrentNodeIndex(g));
				if (measure < distance) {
					distance = measure;
					closeGhostNode = game.getGhostCurrentNodeIndex(g);
				}
			}
		}

		move = game.getApproximateNextMoveAwayFromTarget(
				game.getGhostCurrentNodeIndex(ghost), closeGhostNode,
				game.getGhostLastMoveMade(ghost), DM.PATH);

		return move;
	}
}
