package edu.ucsc.gameAI;

import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;
import edu.ucsc.gameAI.decisionTrees.binary.IBinaryNode;

public class EatGhostAction implements IAction, IBinaryNode {
	
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
		int minDistance=Integer.MAX_VALUE;
		GHOST minGhost=null;		
				
		for(GHOST ghost : GHOST.values())
			if(game.getGhostEdibleTime(ghost)>0)
			{
				int distance=game.getShortestPathDistance(game.getPacmanCurrentNodeIndex(),game.getGhostCurrentNodeIndex(ghost));
						
				if(distance<minDistance)
				{
					minDistance=distance;
					minGhost=ghost;
				}
			}
				
		if(minGhost!=null)	//we found an edible ghost
			return game.getNextMoveTowardsTarget(game.getPacmanCurrentNodeIndex(),game.getGhostCurrentNodeIndex(minGhost),DM.PATH);
		return MOVE.NEUTRAL;
	}

	@Override
	public MOVE getMove(GHOST ghost, Game game) {
		// TODO Auto-generated method stub
		return null;
	}
}
