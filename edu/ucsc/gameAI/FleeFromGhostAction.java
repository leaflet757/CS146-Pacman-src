package edu.ucsc.gameAI;

import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;
import edu.ucsc.gameAI.decisionTrees.binary.IBinaryNode;
import static pacman.game.Constants.DM;

public class FleeFromGhostAction implements IAction, IBinaryNode {
	
	private Game game;
	
	public void doAction() {
	}
	
	public IAction makeDecision() {return this;}

	@Override
	public IAction makeDecision(Game game) {
		this.game = game;
		return this;
	}

	@Override
	public MOVE getMove() {
		for(GHOST ghost : GHOST.values())
			if(game.getShortestPathDistance(game.getPacmanCurrentNodeIndex(),game.getGhostCurrentNodeIndex(ghost))<20)
				return game.getNextMoveAwayFromTarget(game.getPacmanCurrentNodeIndex(),game.getGhostCurrentNodeIndex(ghost),DM.PATH);
		return MOVE.NEUTRAL;
	}

	@Override
	public MOVE getMove(GHOST ghost, Game game) {
		// TODO Auto-generated method stub
		return null;
	}
}
