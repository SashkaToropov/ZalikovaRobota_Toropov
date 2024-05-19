package com.example.zalikovarobota_toropov;

public class BMIRecord {
    private double weight;
    private double height;
    private double bmi;
    private String date;

    public BMIRecord(double weight, double height, double bmi, String date) {
        this.weight = weight;
        this.height = height;
        this.bmi = bmi;
        this.date = date;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public double getBmi() {
        return bmi;
    }

    public String getDate() {
        return date;
    }
}

