package world;

import org.junit.Assert;
import org.junit.Test;

import utils.CritterAction;

public class ActionTargetTest {
	
	private CritterAction action;
	private Coord target;
	private ActionTarget actionTarget;

	@Test
	public void fullActionTargetTest(){
		action = CritterAction.WAIT;
		target = new Coord(2,3);
		actionTarget = new ActionTarget(action, target);
		
		Coord getTarget = actionTarget.getTarget();
		Assert.assertEquals(CritterAction.WAIT, actionTarget.getAction());
		Assert.assertEquals(2, getTarget.getX());
		Assert.assertEquals(3, getTarget.getY());
		
		Assert.assertEquals("WAIT 2 3", actionTarget.toString());
	}
}
