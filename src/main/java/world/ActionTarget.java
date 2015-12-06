package world;

import utils.CritterAction;

public class ActionTarget {

	private CritterAction action;
	private Coord target;
	
	public ActionTarget( CritterAction action, Coord target ){
		this.setAction(action);
		this.setTarget(target);
	}
	
	public CritterAction getAction() {
		return action;
	}

	public void setAction(CritterAction action) {
		this.action = action;
	}

	public Coord getTarget() {
		return target;
	}

	public void setTarget(Coord target) {
		this.target = target;
	}
	
	@Override
	public String toString(){
		return action.name() + " " + target.toString();
	}
	
}
