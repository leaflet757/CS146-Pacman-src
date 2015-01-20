package edu.ucsc.gameAI;

import edu.ucsc.gameAI.decisionTrees.binary.IBinaryNode;
import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

public class GuardPowerPillAction implements IAction, IBinaryNode {

	MOVE move;

	@Override
	public void doAction() {
		// TODO Auto-generated method stub

	}

	@Override
	public IAction makeDecision(Game game) {
		return this;
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

		int powerpillNode = -1;
		int pmNode = game.getPacmanCurrentNodeIndex();

		int pmDistanceToPP = Integer.MAX_VALUE; // Some random big number
		for (int powerpill : game.getActivePowerPillsIndices()) {
			if (game.isPowerPillStillAvailable(powerpill)) {
				int distance = game.getShortestPathDistance(pmNode, powerpill);
				if (distance < pmDistanceToPP) {
					pmDistanceToPP = distance;
					powerpillNode = powerpill;
				}
			}
		}
		
		if (powerpillNode != -1) {
			move = game.getApproximateNextMoveTowardsTarget(
					game.getGhostCurrentNodeIndex(ghost), powerpillNode,
					game.getGhostLastMoveMade(ghost), DM.PATH);
		} else {
			move = game.getApproximateNextMoveTowardsTarget(
					game.getGhostCurrentNodeIndex(ghost), pmNode,
					game.getGhostLastMoveMade(ghost), DM.PATH);
		}
//		while (move == game.getGhostLastMoveMade(ghost)) {
//			move = getMove();
//		}

		return move;
	}

}
