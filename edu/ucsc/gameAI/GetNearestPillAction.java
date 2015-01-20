package edu.ucsc.gameAI;

import java.util.ArrayList;

import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;
import edu.ucsc.gameAI.decisionTrees.binary.IBinaryNode;
//import static pacman.game.Constants.DM;

public class GetNearestPillAction implements IAction, IBinaryNode {
	
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
		int ipac = game.getPacmanCurrentNodeIndex();
		
		//Strategy 3: go after the pills and power pills
		int[] pills=game.getPillIndices();
		int[] powerPills=game.getPowerPillIndices();		
				
		ArrayList<Integer> targets=new ArrayList<Integer>();
				
		for(int i=0;i<pills.length;i++)					//check which pills are available			
			if(game.isPillStillAvailable(i))
				targets.add(pills[i]);
				
		for(int i=0;i<powerPills.length;i++)			//check with power pills are available
			if(game.isPowerPillStillAvailable(i))
				targets.add(powerPills[i]);				
				
		int[] targetsArray=new int[targets.size()];		//convert from ArrayList to array
				
		for(int i=0;i<targetsArray.length;i++)
			targetsArray[i]=targets.get(i);
		return game.getNextMoveTowardsTarget(ipac,game.getClosestNodeIndexFromNodeIndex(ipac,targetsArray,DM.PATH),DM.PATH);				
	}

	@Override
	public MOVE getMove(GHOST ghost, Game game) {
		// TODO Auto-generated method stub
		return null;
	}
}
