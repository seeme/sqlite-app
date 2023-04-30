package fcu.mp.sqliteapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.appcompat.app.AppCompatActivity;

public class SqliteHandler {

  private AppCompatActivity activity;

  private static final String DATABASE_NAME = "breakfast.db";

  private static final String CREATE_TABLE_MEALS = "CREATE TABLE Meals (" +
      "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
      "name TEXT NOT NULL," +
      "description TEXT," +
      "imageId INTEGER," +
      "price DECIMAL(8, 2)" +
      ");";

  private SQLiteDatabase database = null;

  public SqliteHandler(AppCompatActivity activity) {
    this.activity = activity;
  }

  public void openDatabase() {
    database = activity.openOrCreateDatabase(DATABASE_NAME, 0, null);
    try {
      database.execSQL(CREATE_TABLE_MEALS);
    }catch(Exception e) {
      e.printStackTrace();
    }
  }

  public void addMeal(Meal meal) {
    ContentValues values = new ContentValues();
    values.put("name", meal.getName());
    values.put("description", meal.getDescription());
    values.put("price", meal.getPrice());
    values.put("imageId", meal.getImageId());
    database.insert("Meals", null, values);
  }

  public Cursor getAllMeals() {
    Cursor cursor = database.rawQuery("SELECT * FROM Meals", null);
    while (cursor.moveToNext()) {
      String name = cursor.getString(1);
      System.out.println(name);
    }
    return cursor;
  }

  public void deleteTable() {
    database.execSQL("Drop table if exists Meals");
  }
}
