package edu.ucsc.gameAI;

import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;
import edu.ucsc.gameAI.decisionTrees.binary.IBinaryNode;

public class GetAheadAction implements IAction, IBinaryNode {

	private int AHEAD = 4;
	MOVE move;

	@Override
	public IAction makeDecision(Game game) {
		return this;
	}

	@Override
	public void doAction() {
		// TODO Auto-generated method stub

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

		int pmNode = game.getPacmanCurrentNodeIndex();
		MOVE pmDirection = game.getPacmanLastMoveMade();

		int neighbor = pmNode;
		for (int i = 0; i < AHEAD; i++) {
			int last = neighbor;
			neighbor = game.getNeighbour(neighbor, pmDirection);
			if (neighbor == -1) {
				int[] n = game.getNeighbouringNodes(last);
				int index = (int) Math.round(Math.random() * n.length - 1);
				if (index != -1) {
					neighbor = n[index];
				} else {
					neighbor = last;
				}
			}
		}

		move = game.getApproximateNextMoveTowardsTarget(
				game.getGhostCurrentNodeIndex(ghost), neighbor,
				game.getGhostLastMoveMade(ghost), DM.PATH);

//		while (move == game.getGhostLastMoveMade(ghost)) {
//			move = getMove();
//		}

		return move;
	}

}
