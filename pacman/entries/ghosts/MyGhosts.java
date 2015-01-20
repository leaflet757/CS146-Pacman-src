package pacman.entries.ghosts;

import java.util.EnumMap;
import java.util.LinkedList;

import edu.ucsc.gameAI.*;
import edu.ucsc.gameAI.conditions.*;
import edu.ucsc.gameAI.fsm.*;

import pacman.controllers.Controller;
import pacman.game.Game;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;

/*
 * This is the class you need to modify for your entry. In particular, you need to
 * fill in the getActions() method. Any additional classes you write should either
 * be placed in this package or sub-packages (e.g., game.entries.ghosts.mypackage).
 */
public class MyGhosts extends Controller<EnumMap<GHOST, MOVE>> {

	private final EnumMap<GHOST, MOVE> myMoves = new EnumMap<GHOST, MOVE>(
			GHOST.class);
	// Separate StateMachine for each GHOST
	private EnumMap<GHOST, StateMachine> fsm = new EnumMap<GHOST, StateMachine>(
			GHOST.class);

	public MyGhosts() {
		initializeFSMForGhost(GHOST.INKY);
		initializeFSMForGhost(GHOST.PINKY);
		initializeFSMForGhost(GHOST.SUE);
		initializeFSMForGhost(GHOST.BLINKY);
	}

	/**
	 * Initializes a FSM for GHOST p
	 * 
	 * @param ghost
	 */
	private void initializeFSMForGhost(GHOST ghost) {
	
		StateMachine gfsm = new StateMachine();

		// States
		State spawn = new State();
		State chase = new State();
		State scatter = new State();
		State fear = new State();

		// Transitions
		Transition toChaseFromSpawn = new Transition();
		Transition toScatter = new Transition();
		Transition toChaseFromScatter = new Transition();
		Transition toFearFromScatter = new Transition();
		Transition toFearFromChase = new Transition();
		Transition toChaseFromFear = new Transition();
		Transition toSpawn = new Transition();
		
		// Conditions
		toChaseFromSpawn.setCondition(new LairTime(ghost, 0, 20, true));
		toScatter.setCondition(new IsCrowded(ghost));
		toChaseFromScatter.setCondition(new HasMoved(ghost, 2));
		toFearFromScatter.setCondition(new IsEdible(ghost));
		toFearFromChase.setCondition(new IsEdible(ghost));
		toChaseFromFear.setCondition(new IsEdible(ghost, true));
		toSpawn.setCondition(new GhostEaten(ghost));

		// Actions
//		switch (ghost) {
//		case INKY:
//			chase.setAction(new GetAheadAction());
//			break;
//		case PINKY:
//			chase.setAction(new FollowPacmanAction());
//			break;
//		case BLINKY:
//			chase.setAction(new FollowPacmanAction());
//			break;
//		case SUE:
//			chase.setAction(new GuardPowerPillAction());
//			break;
//		default:
//			break;
//		}
		chase.setAction(new FollowPacmanAction());
		scatter.setAction(new GuardPowerPillAction());
		fear.setAction(new MoveAwayFromPacman());
		

		// Setting Targets
		toChaseFromSpawn.setTargetState(chase);
		toScatter.setTargetState(scatter);
		toChaseFromScatter.setTargetState(chase);
		toFearFromScatter.setTargetState(fear);
		toFearFromChase.setTargetState(fear);
		toChaseFromFear.setTargetState(chase);
		toSpawn.setTargetState(spawn);

		// Setting Transitions
		LinkedList<ITransition> spawnTransitions = new LinkedList<ITransition>();
		spawnTransitions.add(toChaseFromSpawn);
		spawn.setTransitions(spawnTransitions);
		LinkedList<ITransition> chaseTransitions = new LinkedList<ITransition>();
		chaseTransitions.add(toScatter);
		chaseTransitions.add(toFearFromChase);
		chase.setTransitions(chaseTransitions);
		LinkedList<ITransition> scatterTransitions = new LinkedList<ITransition>();
		scatterTransitions.add(toChaseFromScatter);
		scatterTransitions.add(toFearFromScatter);
		scatter.setTransitions(scatterTransitions);
		LinkedList<ITransition> fearTransitions = new LinkedList<ITransition>();
		fearTransitions.add(toChaseFromFear);
		fearTransitions.add(toSpawn);
		fear.setTransitions(fearTransitions);
		

		// Set Current State of GHOST
		gfsm.setCurrentState(spawn);
		// put GHOST FSM in map
		fsm.put(ghost, gfsm);
	}

	@Override
	public EnumMap<GHOST, MOVE> getMove(Game game, long timeDue) {
		for (GHOST ghost : GHOST.values()) {
			if (game.doesGhostRequireAction(ghost)) {
				StateMachine ghostFSM = fsm.get(ghost);
				LinkedList<IAction> actionList = (LinkedList<IAction>) ghostFSM
						.update(game);
				for(IAction action : actionList) {
					if (action != null) {
						myMoves.put(ghost, action.getMove(ghost, game));
					}
				}
			}
		}
		return myMoves;
	}
}
