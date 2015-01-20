package edu.ucsc.gameAI.fsm;

import java.util.Collection;
import java.util.LinkedList;

import pacman.game.Game;

import edu.ucsc.gameAI.IAction;

public class StateMachine implements IStateMachine {

	private LinkedList<IAction> actionList;

	private IState initialState;
	private IState currentState;

	public StateMachine() {
		actionList = new LinkedList<IAction>();
	}

	/**
	 * Checks and applies transitions, return a list of Actions
	 * 
	 * @return a collection of actions: These actions are based on the actions
	 *         of transitions and entering and exiting states
	 */
	@Override
	public Collection<IAction> update(Game game) {

		ITransition triggeredTransition = null;
		actionList.clear();

		// Checks each transition to see which one is triggered first
		for (ITransition transition : currentState.getTransitions()) {
			if (transition.isTriggered(game)) {
				triggeredTransition = transition;
				break;
			}
		}

		// Checks if there is a transition to fire
		if (triggeredTransition != null) {
			IState targetState = triggeredTransition.getTargetState();

			// Add the exit action of the old state,
			// transition action then the entry action
			if (currentState.getExitAction() != null)
				actionList.add(currentState.getExitAction());
			if (triggeredTransition.getAction() != null)
				actionList.add(triggeredTransition.getAction());
			if (targetState.getEntryAction() != null)
				actionList.add(targetState.getEntryAction());

			// Complete the transition
			currentState = targetState;
		} else // otherwise just return the current states action
		{
			actionList.add(currentState.getAction());
		}

		return actionList;
	}

	/**
	 * Gets the current state
	 */
	@Override
	public IState getCurrentState() {
		return currentState;
	}

	/**
	 * Sets the Current State and the initial state if it not specified
	 */
	@Override
	public void setCurrentState(IState state) {
		if (initialState == null) {
			initialState = state;
		}
		currentState = state;
	}

}
