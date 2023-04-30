package fcu.mp.sqliteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MealManagementActivity extends AppCompatActivity {

  private EditText etMealName;
  private EditText etMealPrice;
  private EditText etMealDescription;
  private EditText etMealImage;
  private Button btnAddMeal;
  private ListView lvMeals;
  private SqliteHandler sqliteHandler;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_meal_management);

    etMealName = findViewById(R.id.et_meal_name);
    etMealPrice = findViewById(R.id.et_meal_price);
    etMealDescription = findViewById(R.id.et_meal_description);
    etMealImage = findViewById(R.id.et_meal_image);
    btnAddMeal = findViewById(R.id.btn_add_meal);
    lvMeals = findViewById(R.id.list_mgt_meals);

    sqliteHandler = new SqliteHandler(this);
    sqliteHandler.openDatabase();
    //sqliteHandler.deleteTable();


    View.OnClickListener onClickListener = new View.OnClickListener() {

      @Override
      public void onClick(View view) {
        String mealName = etMealName.getText().toString();
        String mealDescription = etMealDescription.getText().toString();
        double mealPrice = Double.parseDouble(etMealPrice.getText().toString());
        String mealImage = etMealImage.getText().toString();
        int mealImageId = getResources().getIdentifier(mealImage, "drawable", getPackageName());
        sqliteHandler.addMeal(new Meal(0, mealName, mealDescription, mealPrice,  mealImageId));
        updateMealList();
      }
    };

    btnAddMeal.setOnClickListener(onClickListener);
    updateMealList();

  }

  @Override
  protected void onResume() {
    super.onResume();
    updateMealList();
  }

  private void updateMealList() {
    Cursor cursor = sqliteHandler.getAllMeals();
    SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2,
        cursor, new String[]{"name", "price"}, new int[]{android.R.id.text1, android.R.id.text2}, 0);

    lvMeals.setAdapter(adapter);
  }
}