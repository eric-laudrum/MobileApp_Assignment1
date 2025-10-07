package com.example.comp3074_assignment_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener{

    @Override
    public void onClick(View v) {
        int id = v.getId();

        Intent intent = null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_details);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        double hours = intent.getDoubleExtra("hours", 0);
        double rate = intent.getDoubleExtra("rate", 0);
        double regularPay = intent.getDoubleExtra("regularPay", 0);
        double overtimePay = intent.getDoubleExtra("overtimePay", 0);
        double tax = intent.getDoubleExtra("tax", 0);
        double netPay = intent.getDoubleExtra("netPay", 0);


        // Dynamically Set values
        TextView hoursView = findViewById(R.id.hours);

        TextView rateView = findViewById(R.id.rate);
        TextView regularPayView = findViewById(R.id.regularPay);
        TextView overtimePayView = findViewById(R.id.overtimePay);
        TextView taxView = findViewById(R.id.tax);
        TextView netPayView = findViewById(R.id.netPay);

        rateView.setText("Rate:  " + rate);
        hoursView.setText("Hours:  " + hours);
        regularPayView.setText("Regular Pay:  " + regularPay);
        overtimePayView.setText("Overtime Pay:  " + overtimePay);
        taxView.setText("Tax:  " + tax);
        netPayView.setText("Net Pay:  " + netPay);

    }


    @Override
    public boolean onLongClick(View v) {
        return false;
    }
}