package com.example.zalikovarobota_toropov;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class BMIHistoryActivity extends AppCompatActivity {

    private ListView bmiHistoryListView;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_history);

        bmiHistoryListView = findViewById(R.id.bmiHistoryListView);
        backButton = findViewById(R.id.backButton);

        List<BMIRecord> bmiHistory = BMIHistoryManager.getInstance().getBmiHistory();

        List<String> bmiHistoryStrings = new ArrayList<>();
        for (BMIRecord record : bmiHistory) {
            bmiHistoryStrings.add(String.format("Дата: %s\nВага: %.2f кг\nЗріст: %.2f м\nІМТ: %.2f",
                    record.getDate(), record.getWeight(), record.getHeight(), record.getBmi()));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, bmiHistoryStrings);
        bmiHistoryListView.setAdapter(adapter);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
