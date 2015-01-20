package edu.ucsc.gameAI.fsm;

import java.util.Collection;
import java.util.LinkedList;

import edu.ucsc.gameAI.IAction;

public class State implements IState {

	private IAction action = null;
	private IAction entryAction = null;
	private IAction exitAction = null;
	
	LinkedList<ITransition> transitions = new LinkedList<>();
	
	@Override
	public IAction getAction() {
		return action;
	}

	@Override
	public void setAction(IAction action) {
		this.action = action;
	}

	@Override
	public IAction getEntryAction() {
		return entryAction;
	}

	@Override
	public void setEntryAction(IAction action) {
		entryAction = action;
	}

	@Override
	public IAction getExitAction() {
		return exitAction;
	}

	@Override
	public void setExitAction(IAction action) {
		exitAction = action;
	}

	/**
	 * @return returns an array list of transitions
	 */
	@Override
	public Collection<ITransition> getTransitions() {
		return transitions;
	}

	/**
	 * @param trans converts trans into an linked
	 */
	@Override
	public void setTransitions(Collection<ITransition> trans) {
		transitions = (LinkedList<ITransition>) trans;
	}

}
