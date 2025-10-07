package com.example.comp3074_assignment_1;

import android.app.AlertDialog;
import android.os.Bundle;
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

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editText = findViewById(R.id.inputHours);

    }
    public void getHours(View view){
        String s = editText.getText().toString();

        // Pop up message if input is invalid
        if(s.isEmpty()){
            Toast.makeText(this, "Enter a valid number of hours", Toast.LENGTH_SHORT)
                    .show();
            return;
        }

        displayDialog(s);
    }



    private void displayDialog(String s){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder
                .setTitle("User input")
                .setMessage(s)
                .setNegativeButton("OK", null)
                .setPositiveButton("show hours", (dialog, w)->{
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


}