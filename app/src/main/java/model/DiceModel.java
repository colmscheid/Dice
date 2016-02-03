package model;

import java.util.Random;

/***
 * A model for a dice with n elements.
 * @author Christian Olmscheid
 *
 */

public class DiceModel {

	private String[] elements;
	private Random random;
	
	public DiceModel(String[] elements) {
		
		this.elements = elements;
		random = new Random();
	}
	
	public String[] getElements() {
		
		return elements;
	}
	
	public String randomElement() {
		
		return elements[random.nextInt(elements.length)];
	}
	
	public String toString() {
		
		String text = "";
		
		for (int i = 0; i < elements.length; i++) {
			
			text += elements[i].toString();
			
			if (i+1 < elements.length)
				text += " | ";
		}
		
		return text;
	}
}