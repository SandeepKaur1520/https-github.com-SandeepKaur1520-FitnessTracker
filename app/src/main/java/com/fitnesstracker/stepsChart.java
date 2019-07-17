package com.fitnesstracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

public class stepsChart extends AppCompatActivity {
    int completed = 0;
    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.steps_chart);
        Intent intent = getIntent();
        completed = (int) intent.getIntExtra("stepValue",0);

        int target = 10000;

        updateChart(completed,target);
    }

    private int updateChart(int completed , int target){
        int percentage = 0;
        Log.e(" Completed : ",String.valueOf(completed));
        percentage = (completed * 100)/target;
        TextView numberOfCals = findViewById(R.id.number_of_calories);
        numberOfCals.setText(percentage+"% Completed");
        ProgressBar pieChart = findViewById(R.id.stats_progressbar);
        int progress = (int) percentage;
        pieChart.setProgress(progress);
        return progress;
    }
}
