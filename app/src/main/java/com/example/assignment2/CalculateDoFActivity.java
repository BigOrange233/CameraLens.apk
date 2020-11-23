package com.example.assignment2;

import android.content.Intent;
import android.os.Bundle;

import com.example.assignment2.model.Calculator;
import com.example.assignment2.model.Lens;
import com.example.assignment2.model.LensManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class CalculateDoFActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_dof);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setTitle("Calculate Dof");

        Intent intent = getIntent();
        Lens temp = LensManager.getInstance().retrieve(intent.getIntExtra("index",0));
        TextView changetext = findViewById(R.id.lensDetail);
        changetext.setText(temp.getMake() + " " + temp.getFocalLengthInMM() + "mm F" + temp.getMaxAperture() );
        CenterTextView();
        ActivateButton(temp.getMaxAperture(),temp.getFocalLengthInMM());
        ActionBar actionbar1 = getSupportActionBar();
        actionbar1.setDisplayHomeAsUpEnabled(true);

    }



    private void CenterTextView(){
        TextView Cocview = findViewById(R.id.askCoc);
        TextView Distanceview = findViewById(R.id.askDistance);
        TextView Apertureview = findViewById(R.id.askAperture);
        TextView nearFocalview = findViewById(R.id.nearFocal);
        TextView Farfocalview = findViewById(R.id.Farfocal);
        TextView hyperview = findViewById(R.id.hyper);
        TextView dofview = findViewById(R.id.Dof);

        Display display = getWindowManager().getDefaultDisplay();
        int displayWith = display.getWidth() / 2;
        Cocview.setWidth(displayWith);
        Distanceview.setWidth(displayWith);
        Apertureview.setWidth(displayWith);
        nearFocalview.setWidth(displayWith);
        Farfocalview.setWidth(displayWith);
        hyperview.setWidth(displayWith);
        dofview.setWidth(displayWith);


    }

    private void ActivateButton(final double maxAperture, final double focalLengthInMM) {
    final Button calculation = (Button)findViewById(R.id.Calculation);
    calculation.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String Cocinput = ((EditText)findViewById(R.id.inputCoc)).getText().toString();
            String Distanceinput = ((EditText)findViewById(R.id.inputDistance)).getText().toString();
            String Apertureinput = ((EditText)findViewById(R.id.inputAperture)).getText().toString();

            if(Cocinput.matches("" ) || Distanceinput.matches("") || Apertureinput.matches("")){
                Toast.makeText(CalculateDoFActivity.this, "Please input all Fields", Toast.LENGTH_SHORT).show();
                String section = "Please input all Fields";
                invalidShow(section);
            }else{
                double Coc = Double.parseDouble(Cocinput);
                double Distance = Double.parseDouble(Distanceinput);
                double Aperture = Double.parseDouble(Apertureinput);

                if(Distance < 0 ){
                    Toast.makeText(CalculateDoFActivity.this,"Distance should be positive", Toast.LENGTH_SHORT).show();
                    String section = "Invalid Distance";
                    invalidShow(section);
                }else if(Aperture > 22 || Aperture < maxAperture){
                    Toast.makeText(CalculateDoFActivity.this, "Aperture is not valid.",Toast.LENGTH_SHORT).show();
                    String section = "Invalid Aperture";
                    invalidShow(section);
                }else if(Coc < 0 ){
                    Toast.makeText(CalculateDoFActivity.this,"Coc should be positive", Toast.LENGTH_SHORT).show();
                    String section = "Invalid Coc";
                    invalidShow(section);
                }else{
                    Calculator calculator = new Calculator(focalLengthInMM,Distance,Aperture,Coc);
                    ((TextView)findViewById(R.id.nearFocalResult)).setText(formatM(calculator.getNearfocal())+ "m");
                    ((TextView)findViewById(R.id.FarfocalResult)).setText(formatM(calculator.getFarfocal())+ "m");
                    ((TextView)findViewById(R.id.DofResult)).setText(formatM(calculator.getDepthofField())+ "m");
                    ((TextView)findViewById(R.id.hyperResult)).setText(formatM(calculator.getHyperfocal()) + "m");
                }
            }
        }
    });{

        };
    }

    private void invalidShow(String section){
        ((TextView)findViewById(R.id.nearFocalResult)).setText(section );
        ((TextView)findViewById(R.id.FarfocalResult)).setText(section );
        ((TextView)findViewById(R.id.DofResult)).setText(section);
        ((TextView)findViewById(R.id.hyperResult)).setText(section );
    }

    private String formatM(double distanceInM) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(distanceInM);
    }
}


//Calculationback