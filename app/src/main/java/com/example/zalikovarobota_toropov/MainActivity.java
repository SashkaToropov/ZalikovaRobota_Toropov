package com.example.zalikovarobota_toropov;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText weightInput, heightInput;
    private Button calculateButton;
    private TextView resultTextView;
    private View colorIndicator;
    private Button viewHistoryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weightInput = findViewById(R.id.weightInput);
        heightInput = findViewById(R.id.heightInput);
        calculateButton = findViewById(R.id.calculateButton);
        resultTextView = findViewById(R.id.resultTextView);
        colorIndicator = findViewById(R.id.colorIndicator);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });

        viewHistoryButton = findViewById(R.id.viewHistoryButton);

        viewHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BMIHistoryActivity.class);
                startActivity(intent);
            }
        });
    }

    private void calculateBMI() {
        String weightStr = weightInput.getText().toString();
        String heightStr = heightInput.getText().toString();

        if (!weightStr.isEmpty() && !heightStr.isEmpty()) {
            double weight = Double.parseDouble(weightStr);
            double height = Double.parseDouble(heightStr) / 100;
            double bmi = weight / (height * height);

            displayBMI(bmi);
            saveBMIResult(weight, height, bmi);

            Toast.makeText(this, "BMI розраховано!", Toast.LENGTH_SHORT).show();
        }
    }

    private void displayBMI(double bmi) {
        String bmiLabel = "";
        int color = Color.BLACK;

        if (bmi < 16) {
            bmiLabel = getString(R.string.excessive_underweight);
            color = Color.RED;
        } else if (bmi >= 16 && bmi < 18.5) {
            bmiLabel = getString(R.string.underweight);
            color = Color.YELLOW;
        } else if (bmi >= 18.5 && bmi < 25) {
            bmiLabel = getString(R.string.normal_weight);
            color = Color.GREEN;
        } else if (bmi >= 25 && bmi < 30) {
            bmiLabel = getString(R.string.overweight);
            color = Color.YELLOW;
        } else if (bmi >= 30) {
            bmiLabel = getString(R.string.obesity);
            color = Color.RED;
        }

        resultTextView.setText(String.format(getString(R.string.bmi_result), bmi, bmiLabel));
        resultTextView.setTextColor(Color.BLACK);
        colorIndicator.setBackgroundColor(color);
    }

    private void saveBMIResult(double weight, double height, double bmi) {
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        BMIRecord record = new BMIRecord(weight, height, bmi, currentDate);
        BMIHistoryManager.getInstance().addRecord(record);
    }
}
