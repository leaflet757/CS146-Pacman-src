package pacman.entries.pacman;

import java.util.ArrayList;

import edu.ucsc.gameAI.*;
import edu.ucsc.gameAI.conditions.*;
import edu.ucsc.gameAI.decisionTrees.binary.BinaryDecision;
import pacman.controllers.Controller;
import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

/*
 * This is the class you need to modify for your entry. In particular, you need to
 * fill in the getAction() method. Any additional classes you write should either
 * be placed in this package or sub-packages (e.g., game.entries.pacman.mypackage).
 */
public class MyPacMan extends Controller<MOVE>
{
	private static final int MIN_DISTANCE=20;	//if a ghost is this close, run away

	public MOVE getMove(Game game, long timeDue) 
	{
		//Build the individual nodes of the tree
		//Strategy 1: if any non-edible ghost is too close (less than MIN_DISTANCE), run away
		BinaryDecision root = new BinaryDecision();
		root.setCondition(new InedibleGhostNearby(MIN_DISTANCE,game.getPacmanCurrentNodeIndex()));
		root.setTrueBranch(new FleeFromGhostAction());
		
		//Strategy 2: find the nearest edible ghost and go after them 
		BinaryDecision edibleGhostCond = new BinaryDecision();
		edibleGhostCond.setCondition(new EdibleGhosts());
		edibleGhostCond.setTrueBranch(new EatGhostAction());
		
		//Strategy 3: go after the pills and power pills
		edibleGhostCond.setFalseBranch(new GetNearestPillAction());
		
		//Finish connecting the nodes
		root.setFalseBranch(edibleGhostCond);
		
			
		//return myMove;
		return root.makeDecision(game).getMove();
		
	}
}