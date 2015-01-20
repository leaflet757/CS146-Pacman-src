package edu.ucsc.gameAI.fsm;

import pacman.game.Game;
import edu.ucsc.gameAI.IAction;
import edu.ucsc.gameAI.ICondition;

public class Transition implements ITransition {

	private IState targetState = null;
	private ICondition condition = null;
	private IAction action = null;
	
	@Override
	public IState getTargetState() {
		return targetState;
	}

	@Override
	public void setTargetState(IState targetState) {
		this.targetState = targetState;
	}

	@Override
	public IAction getAction() {
		return action;
	}

	@Override
	public void setAction(IAction action) {
		this.action = action;
	}

	@Override
	public void setCondition(ICondition condition) {
		this.condition = condition;
	}

	@Override
	public boolean isTriggered(Game game) {
		return condition.test(game);
	}

}
