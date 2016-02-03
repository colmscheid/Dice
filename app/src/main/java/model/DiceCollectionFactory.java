package model;

/***
 * A factory for common dice collections.
 * @author Christian Olmscheid
 *
 */

public class DiceCollectionFactory {

	public static DiceCollection createNumberDiceCollection(int start, int end, int number) {
		
		DiceModel[] array = new DiceModel[number];
		
		for (int i = 0; i < number; i++) {
			
			array[i] = DiceModelFactory.createNumberDice(start, end);
		}
		
		return new DiceCollection(array, "");
	}
	
	public static DiceCollection createOriginalDiceCollection(int number) {
		
		DiceModel[] array = new DiceModel[number];
		
		for (int i = 0; i < number; i++) {
			
			array[i] = DiceModelFactory.creatOriginalDice();
		}
		
		return new DiceCollection(array, "");
	}
}