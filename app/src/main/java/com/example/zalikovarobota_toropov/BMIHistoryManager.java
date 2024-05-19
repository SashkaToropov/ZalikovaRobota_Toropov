package com.example.zalikovarobota_toropov;

import java.util.ArrayList;
import java.util.List;

public class BMIHistoryManager {
    private static BMIHistoryManager instance;
    private List<BMIRecord> bmiHistory;

    private BMIHistoryManager() {
        bmiHistory = new ArrayList<>();
    }

    public static synchronized BMIHistoryManager getInstance() {
        if (instance == null) {
            instance = new BMIHistoryManager();
        }
        return instance;
    }

    public void addRecord(BMIRecord record) {
        bmiHistory.add(record);
    }

    public List<BMIRecord> getBmiHistory() {
        return new ArrayList<>(bmiHistory);
    }
}

