package com.example.comp3074_assignment_1;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Activity_Lifecycle";
    private EditText editText;
    private EditText editTextHours;
    private EditText editTextRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        editTextHours = findViewById(R.id.inputHours);
        editTextRate = findViewById(R.id.inputRate);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //editText = findViewById(R.id.inputHours);
        Button button = findViewById(R.id.goToDetails);
        button.setOnClickListener(view ->{
            // Navigate from activity 1 to activity 2
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("name", "detail activity");
            startActivity(intent);

            Toast
                .makeText(this, "detail activity button clicked", Toast.LENGTH_LONG)
                .show();
        });

    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d(TAG, "onStart: Activity 1");
    }

    public void getHours(View view){
        // Store input as string values
        String hoursStr = editTextHours.getText().toString();
        String rateStr = editTextRate.getText().toString();

        // Alert user if input is invalid
        if(hoursStr.isEmpty() || rateStr.isEmpty()){
            Toast.makeText(this, "Please, enter a valid input", Toast.LENGTH_SHORT)
                    .show();
            return;
        }

        // Convert values
        double hours = Double.parseDouble(hoursStr);
        double rate = Double.parseDouble(rateStr);

        double regularPay;
        double overtimePay;
        double netPay;
        double tax;
        String result;

        // Calculate overtime pay
        if(hours <= 40){
            regularPay = hours * rate;
            overtimePay = 0;
        }
        else{
            regularPay =  + (40 * rate);
            overtimePay = (hours - 40) * (rate * 1.5);
        }

        tax = (regularPay + overtimePay) * 0.18;

        netPay = (regularPay + overtimePay) - tax;

        result = "\nHours: " + hours +
                "\nRate: " + rate +
                "\nRegular Pay: " + regularPay +
                "\nOvertime Pay: " + overtimePay +
                "\nTax: " + tax +
                "\nTotal Take Home Pay: " + netPay;

        displayDialog(result);

        // Send result info to Detail Activity
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("hours", hours);
        intent.putExtra("rate", rate);
        intent.putExtra("regularPay", regularPay);
        intent.putExtra("overtimePay", overtimePay);
        intent.putExtra("tax", tax);
        intent.putExtra("netPay", netPay);

        startActivity(intent);

    }



    private void displayDialog(String s){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder
                .setTitle("User input")
                .setMessage(s)
                .setNegativeButton("OK", null)
                .setPositiveButton("Show input", (dialog, w)->{
                    displaySnackbar(s);
                })
                .create()
                .show();
    }

    private void displaySnackbar(String s) {
        Snackbar
                .make(findViewById(R.id.main), s, Snackbar.LENGTH_LONG)
                .setAction("OK", (o)->{
                    Toast.makeText(this, s, Toast.LENGTH_SHORT)
                            .show();
                })
                .setActionTextColor(ContextCompat.getColor(this, R.color.white))
                .show();
    }

    private void goToDetails(View view){
        Intent intent = new Intent(this, DetailActivity.class);
        startActivity(intent);
    }


}