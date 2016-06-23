package critters;

import org.junit.Assert;
import org.junit.Test;
import utils.Coord;

public class BaseCritterTest {

	private BaseCritter bc;
	
	@Test
	public void fullBaseCritterTest(){
		bc = new BaseCritter();
		
		Assert.assertEquals(0, bc.getCritterID() );
		Assert.assertEquals(0, bc.getCurrentLifeLen() );
		Assert.assertEquals(0, bc.getGenerationID() );
		Assert.assertEquals("-1", bc.getParentUUID() );
		Assert.assertEquals(0, bc.getSpeed() );
		Assert.assertEquals(0, bc.getVision() );
		Assert.assertEquals(null, bc.getWorldLocation() );
		
		Coord worldLocation = new Coord(7,7);
		bc = new BaseCritter("2_2");
		bc.setCritterID(3);
		bc.setCurrentLifeLen(4);
		bc.setGenerationID(5);
		bc.setSpeed(6);
		bc.setVision(7);
		bc.setWorldLocation(worldLocation);
		
		Assert.assertEquals(3, bc.getCritterID() );
		Assert.assertEquals(4, bc.getCurrentLifeLen() );
		Assert.assertEquals(4, bc.calculateFitness() );
		Assert.assertEquals(5, bc.getGenerationID() );
		Assert.assertEquals("2_2", bc.getParentUUID() );
		Assert.assertEquals(6, bc.getSpeed() );
		Assert.assertEquals(7, bc.getVision() );
		Assert.assertEquals("7 7", bc.getWorldLocation().toString() );
		
		//	Pick up trailing method that wasn't exercised
		bc.setParentUUID("5_5");
		Assert.assertEquals("5_5", bc.getParentUUID());
		
		Assert.assertEquals("6:7", bc.toGenome());
		
	}
}
