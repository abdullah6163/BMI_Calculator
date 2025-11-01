package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {

    EditText etHeightFeet, etHeightInch, etWeight;
    Button btnCalculate;
    TextView tvResult, tvCategory;
    LottieAnimationView lottieAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etHeightFeet = findViewById(R.id.etHeightFeet);
        etHeightInch = findViewById(R.id.etHeightInch);
        etWeight = findViewById(R.id.etWeight);
        btnCalculate = findViewById(R.id.btnCalculate);
        tvResult = findViewById(R.id.tvResult);
        tvCategory = findViewById(R.id.tvCategory);
        lottieAnimation = findViewById(R.id.lottieAnimation);

        btnCalculate.setOnClickListener(v -> calculateBMI());
    }

    private void calculateBMI() {
        String feetStr = etHeightFeet.getText().toString().trim();
        String inchStr = etHeightInch.getText().toString().trim();
        String weightStr = etWeight.getText().toString().trim();

        if (feetStr.isEmpty() || inchStr.isEmpty() || weightStr.isEmpty()) {
            Toast.makeText(this, "Please enter height (feet & inches) and weight!", Toast.LENGTH_SHORT).show();
            return;
        }

        double feet = Double.parseDouble(feetStr);
        double inches = Double.parseDouble(inchStr);
        double weight = Double.parseDouble(weightStr);

        double totalInches = (feet * 12) + inches;
        double heightMeters = totalInches * 0.0254;

        double bmi = weight / (heightMeters * heightMeters);
        tvResult.setText(String.format("Your BMI: %.1f", bmi));

        String category;
        int animation = R.raw.healthy;

        if (bmi < 18.5) {
            category = "Underweight";
            animation = R.raw.underweight;
        } else if (bmi < 25) {
            category = "Normal";
            animation = R.raw.healthy;
        } else if (bmi < 30) {
            category = "Overweight";
            animation = R.raw.overweight;
        } else {
            category = "Obese";
            animation = R.raw.obese;
        }
        
        tvCategory.setText("Category: " + category);
        lottieAnimation.setAnimation(animation);
        lottieAnimation.playAnimation();
    }
}
