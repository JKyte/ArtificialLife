package critters;

import org.junit.Assert;
import org.junit.Test;

public class SimpleCritterTest {

	private SimpleCritter sc;

	@Test
	public void testSimpleCritterConstructor(){

		sc = new SimpleCritter();

		//	Confirm that defaults initialized properly
		Assert.assertEquals(true, sc.isAlive() );
		
		sc.setAlive(false);
		Assert.assertEquals(false, sc.isAlive() );
	}

	@Test
	public void testSimpleCritterLoadedConstructor(){
		String parentUUID = "ParentID";
		sc = new SimpleCritter(parentUUID);
		int[] food = new int[2];
		food[0] = 1;
		food[1] = 3;
		
		//	Confirm that defaults initialized properly
		Assert.assertEquals(true, sc.isAlive() );
		Assert.assertEquals(parentUUID, sc.getParentUUID());

		sc.setCurHealth(20);
		sc.setMaxHealth(20);
		sc.setMaxEnergy(5);
		
		sc.setCurEnergy(5);
		sc.setFood(food);
		sc.setFoodEaten(5);
		
		Assert.assertEquals(20, sc.getCurHealth());
		Assert.assertEquals(20, sc.getMaxHealth());
		Assert.assertEquals(5, sc.getCurEnergy());
		Assert.assertEquals(5, sc.getMaxEnergy());
		Assert.assertEquals(1, sc.getFood()[0]);
		Assert.assertEquals(5, sc.getFoodEaten());
		Assert.assertEquals(5, sc.calculateFitness());
		
		sc.setCurEnergy(12345);
		Assert.assertEquals(5, sc.getMaxEnergy());
		
		Assert.assertEquals("20:5:[1,3]:0:0", sc.toGeonome());
	}

}
