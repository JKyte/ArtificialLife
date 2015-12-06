package utils;


import org.junit.Assert;
import org.junit.Test;

public class CritterActionTest {
	
	@Test
	public void testCritterActions(){
		CritterAction eat = CritterAction.EAT;
		CritterAction wait = CritterAction.WAIT;
		CritterAction move = CritterAction.MOVE;
		
		Assert.assertEquals(CritterAction.EAT, eat);
		Assert.assertEquals(CritterAction.WAIT, wait);
		Assert.assertEquals(CritterAction.MOVE, move);
		
		//	Collection of asserts to get 100% coverage for an Enum
		Assert.assertEquals(3, CritterAction.values().length);
		
		Assert.assertEquals(CritterAction.EAT, CritterAction.valueOf("EAT"));
	}

}
