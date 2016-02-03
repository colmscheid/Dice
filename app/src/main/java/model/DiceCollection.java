package model;

/***
 * A collection of different dice models.
 * @author Christian Olmscheid
 *
 */

public class DiceCollection {
	
	private DiceModel[] models;
	private String name;
	private int id = -1;
	
	public DiceCollection(int id, String name) {
		
		setId(id);
		setName(name);
	}

	public DiceCollection(DiceModel[] models, String name) {
		
		setModels(models);
		setName(name);
	}
	
	public void setModels(DiceModel[] models) {
		
		this.models = models;
	}
	
	public DiceModel getModel(int i) {
		
		return models[i];
	}
	
	public int getNumberOfModels() {
		
		return models.length;
	}
	
	public String toString() {
		
		String text = "name: " + name + System.getProperty("line.separator");
		text += "ID: " + id + System.getProperty("line.separator");
		
		for (int i = 0; i < models.length; i++) {
			
			text += "model " + (i+1) + ": " + models[i].toString();
			
			if (i+1 < models.length)
				text += System.getProperty("line.separator");
		}
		
		return text;
	}

	public String getName() {
		
		return name;
	}

	public void setName(String name) {
		
		this.name = name;
	}

	public int getId() {
		
		return id;
	}

	public void setId(int id) {
		
		this.id = id;
	}
}