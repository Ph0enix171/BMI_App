package com.raccoon.bmicalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.raccoon.bmicalculator.R;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private RadioButton male;
    private RadioButton female;
    private EditText age;
    private EditText height;
    private EditText mass;
    private Button calc;
    private TextView weightResult;
    private TextView valueResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();

        setupClickListener();


    }

    private void setupClickListener() {
        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!male.isChecked()&&!female.isChecked()){
                    Toast.makeText(MainActivity.this,"Select Gender",Toast.LENGTH_SHORT).show();
                }
                else if(age.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Enter Valid Age",Toast.LENGTH_SHORT).show();

                }
                else if(height.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Enter Valid Height",Toast.LENGTH_SHORT).show();
                }
                else if(mass.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Enter Valid Weight",Toast.LENGTH_SHORT).show();
                }
                else {
                    double BMIVal = calcBMI();
                    displayResults(BMIVal);
                    Toast.makeText(MainActivity.this, "BMI calculated", Toast.LENGTH_LONG).show();
                }




            }
        });
    }

    private double calcBMI() {

            Double Height = Double.parseDouble(height.getText().toString());
            Double Weight = Double.parseDouble(mass.getText().toString());

        Height=Height/100.0;

        Double BMI=(Weight*1.00)/(Height*Height);
        return BMI;

    }
    private void displayResults(double BMI){
        DecimalFormat bmiformat=new DecimalFormat("0.00");
        String BMIText=bmiformat.format(BMI);
        valueResult.setText(BMIText);
        String resultString="";
        if(male.isChecked()) {
            if (BMI < 19.1)
                resultString = "Underweight";
            else if (BMI > 27.1 && BMI < 31)
                resultString = "Overweight";
            else if(BMI>31)
                resultString="Obese";
            else
                resultString = "Healthy";
        }
        if(female.isChecked()) {
            if (BMI < 18.5)
                resultString = "Underweight";
            else if (BMI > 26.4 && BMI < 30.1)
                resultString = "Overweight";
            else if(BMI>30.1)
                resultString="Obese";
            else
                resultString = "Healthy";
        }
        int Age=Integer.parseInt(age.getText().toString());
        if(Age<18||Age>60)
        {
            resultString="Please consult your doctor for an overview of healthy weight";
        }
        weightResult.setText(resultString);
    }
    private void findView() {
        male = findViewById(R.id.male_radio_button);
        female = findViewById(R.id.female_radio_button);

        age = findViewById(R.id.age_text);
        height = findViewById(R.id.height_text);
        mass = findViewById(R.id.weight_text);

        calc = findViewById(R.id.calc_button);

        weightResult = findViewById(R.id.result_text);
        valueResult = findViewById(R.id.value_text);

    }


}