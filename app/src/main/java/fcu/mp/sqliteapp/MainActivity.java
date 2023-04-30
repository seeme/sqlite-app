package fcu.mp.sqliteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {

  private Button btnNewMeal;
  private ListView listMeals;

  private SqliteHandler sqliteHandler;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    sqliteHandler = new SqliteHandler(this);
    sqliteHandler.openDatabase();

    listMeals = findViewById(R.id.list_main_meals);
    btnNewMeal = findViewById(R.id.btn_new_meal);
    View.OnClickListener listener = new View.OnClickListener() {

      @Override
      public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, MealManagementActivity.class);
        startActivity(intent);
      }
    };

    btnNewMeal.setOnClickListener(listener);
    updateMealList();
  }

  private void updateMealList(){
    Cursor cursor = sqliteHandler.getAllMeals();

    SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.meal_item,
        cursor, new String[]{"name", "price", "imageId"}, new int[]{R.id.tv_meal_name,
        R.id.tv_meal_price, R.id.iv_meal}, 0);
    listMeals.setAdapter(adapter);
  }
}