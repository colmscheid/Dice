package database;

import java.util.ArrayList;

import model.DiceCollection;
import model.DiceModel;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/***
 * A controller to manipulate data in the database dice.
 * @author Christian Olmscheid
 *
 */

public class DiceDatabaseController {

  // The SQLite database
  private SQLiteDatabase database;
  
  // The helper class to manipulate the data in the database
  private DiceSQLiteHelper dbHelper;

  public DiceDatabaseController(Context context) {
	  
    dbHelper = new DiceSQLiteHelper(context);
  }

  public void open() throws SQLException {
	  
    database = dbHelper.getWritableDatabase();
  }

  public void close() {
	  
    dbHelper.close();
  }

  public void addDiceCollection(DiceCollection collection, int id) {
	  
	  // Values for table COLLECTIONS
	  ContentValues values_collection = new ContentValues();
	  
	  // Use given id if given
	  long insertId;
	  if (id == -1) {
		  values_collection.put("name", collection.getName());
	  	  insertId = database.insert("COLLECTIONS", null, values_collection);
	  } else {
		  values_collection.put("collection", id);
		  values_collection.put("name", collection.getName());
	  	  insertId = database.insert("COLLECTIONS", null, values_collection);
	  }
	  
	  // Set ID
	  collection.setId((int) insertId);
	
	  // Add all dices of the collection in the table
	  for (int i = 0; i < collection.getNumberOfModels(); i++) {
		  
		  addDiceModel(collection.getId(), i, collection.getModel(i));
	  }
  }
  
  private void addDiceModel(int id, int number, DiceModel dice) {
	  
	  // Values for table DICES
	  ContentValues values_dices;
	  
	  // A row for every element of the dice
	  for (String element : dice.getElements()) {

		  values_dices = new ContentValues();
		  values_dices.put("collection", id);
		  values_dices.put("model", number);
		  values_dices.put("element", element);
		  
		  database.insert("DICES", null, values_dices);
	  }
  }
  
  public void deleteDiceCollection(DiceCollection collection) {
	  
	  // Delete from table COLLECTION
	  database.delete("COLLECTIONS", "collection = " + collection.getId(), null);
	  
	  // Delete from table DICES
	  database.delete("DICES", "collection = " + collection.getId(), null);
  }
  
  public void resetDatabase() {
	  
	  dbHelper.onUpgrade(database, 0, 0);
  }
  
  public void changeDiceCollection(DiceCollection oldCollection, DiceCollection newCollection) {
	  
	  // Delete old collection
	  deleteDiceCollection(oldCollection);
	  
	  // Save new collection
	  addDiceCollection(newCollection, oldCollection.getId());
  }
  
  public ArrayList<DiceCollection> getAllDiceCollections() {
	  
	  // List of all dice collections
	  ArrayList<DiceCollection> collections = new ArrayList<DiceCollection>();
	  
	  // Get ID and names of all dice collections
	  String[] columns_collection = {"collection", "name"};
	  String[] columns_dices = {"element"};
	  Cursor cursor_collections = database.query("COLLECTIONS", columns_collection, 
			  null, null, null, null, null);
	  cursor_collections.moveToFirst();

	  // If no collections are saved
	  if (cursor_collections.getCount() == 0)
		  return collections;
	  
	  // Iterate over all collections
	  DiceCollection collection;
	  int id;
	  
	  while (!cursor_collections.isAfterLast()) {

		  // Use ID and name of current collection to initialize a new DiceCollection
		  id = cursor_collections.getInt(0); 
		  collection = new DiceCollection(id, cursor_collections.getString(1));
		  
		  // Add all dice models to the collection
		  int number_model = 0;
		  ArrayList<DiceModel> models = new ArrayList<DiceModel>();
		  Cursor cursor_element;
		  
		  while (true) {
			  
			  // Cursor for all elements of the current model
			  cursor_element = database.query("DICES", columns_dices, 
					  "collection = " + id + " and model = " + number_model, 
					  null, null, null, null);

			  // No more model leads to breaking the loop
			  if (cursor_element.getCount() == 0) break;
			  
			  // Iterate over all elements
			  ArrayList<String> elements = new ArrayList<String>();
			  cursor_element.moveToFirst();

			  while (!cursor_element.isAfterLast()) {

				  elements.add(cursor_element.getString(0));
				  cursor_element.moveToNext();
			  }
			  
			  // Add new model
			  models.add(new DiceModel(elements.toArray(new String[0])));
			  
			  number_model++;
		  }
		  	
		  // Add new collection
		  collection.setModels(models.toArray(new DiceModel[0]));
		  collections.add(collection);
		  
		  cursor_collections.moveToNext();
	    }
	  
	  return collections;
  }
} 