package com.example.recipe;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ShowsRecepi extends AppCompatActivity {

    private CheckBox checkBoxEggs, checkBoxFlour, checkBoxSugar;
    private RadioGroup radioGroupMealType;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch switchVegetarian;
    private TextView textViewCookingTime;
    private final ArrayList<String> selectedIngredients = new ArrayList<>();
    private AlertDialog.Builder builder;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shows_recepi);

        checkBoxEggs = findViewById(R.id.checkbox_eggs);
        checkBoxFlour = findViewById(R.id.checkbox_flour);
        checkBoxSugar = findViewById(R.id.checkbox_sugar);
        radioGroupMealType = findViewById(R.id.radioGroup_meal_type);
        RatingBar ratingBar = findViewById(R.id.rating_bar);
        SeekBar seekBarCookingTime = findViewById(R.id.seekbar_cooking_time);
        textViewCookingTime = findViewById(R.id.textview_cooking_time);
        switchVegetarian = findViewById(R.id.switch_vegetarian);
        findViewById(R.id.selected_ingredients_text);
        findViewById(R.id.selected_cooking_time_text);
        Button showSummaryButton = findViewById(R.id.show_summary_button);
        builder = new AlertDialog.Builder(this);

        // Set up SeekBar listener
        seekBarCookingTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewCookingTime.setText("Cooking time: " + progress + " mins");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        showSummaryButton.setOnClickListener(v -> {
            StringBuilder summary = new StringBuilder();
            summary.append("Selected Ingredients:\n");

            if (checkBoxEggs.isChecked()) {
                selectedIngredients.add(checkBoxEggs.getText().toString());
            }
            if (checkBoxFlour.isChecked()) {
                selectedIngredients.add(checkBoxFlour.getText().toString());
            }
            if (checkBoxSugar.isChecked()) {
                selectedIngredients.add(checkBoxSugar.getText().toString());
            }

            for (String ingredient : selectedIngredients) {
                summary.append("- ").append(ingredient).append("\n");
            }

            int selectedMealTypeId = radioGroupMealType.getCheckedRadioButtonId();
            RadioButton selectedMealType = findViewById(selectedMealTypeId);
            if (selectedMealType != null) {
                summary.append("Meal Type: ").append(selectedMealType.getText()).append("\n");
            }


            summary.append("Rating: ").append(ratingBar.getRating()).append("\n");

            int cookingTime = seekBarCookingTime.getProgress();
            summary.append("Cooking Time: ").append(cookingTime).append(" mins\n");

            summary.append("Vegetarian: ").append(switchVegetarian.isChecked() ? "Yes" : "No").append("\n");

            builder.setTitle("Recipe Summary")
                    .setMessage(summary.toString())
                    .setPositiveButton("OK", null)
                    .show();
        });
    }
}
