package model;

/***
 * A factory for common dice models.
 * @author Christian Olmscheid
 *
 */

public class DiceModelFactory {

	public static DiceModel createNumberDice(int start, int end) {
		
		String[] array = new String[end-start+1];
		
		for (int i = start; i <= end; i++) {
			array[i-start] = "" + i;
		}
		
		return new DiceModel(array);
	}
	
	public static DiceModel creatOriginalDice() {
		
		return createNumberDice(1, 6);
	}
}
