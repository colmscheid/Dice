package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Class to create and access database.
 * @author Christian Olmscheid
 *
 */

public class DiceSQLiteHelper extends SQLiteOpenHelper {

  private static final String DATABASE_NAME = "database_dices.db";
  private static final int DATABASE_VERSION = 1;

  // Create table dices
  private static final String TABLE_DICE_CREATE = "CREATE TABLE DICES ("
      + "collection int, " 
      + "model int, "
      + "element text);";
  
  // Create table collections
  private static final String TABLE_COLLECTION_CREATE = "CREATE TABLE COLLECTIONS ("
      + "collection INTEGER PRIMARY KEY autoincrement, "
      + "name text);";

  public DiceSQLiteHelper(Context context) {
	  
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase database) {
	  
    database.execSQL(TABLE_DICE_CREATE);
    database.execSQL(TABLE_COLLECTION_CREATE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	  
    db.execSQL("DROP TABLE IF EXISTS DICES");
    db.execSQL("DROP TABLE IF EXISTS COLLECTIONS");
    onCreate(db);
  }

} 