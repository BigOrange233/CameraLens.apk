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

public class EditOne extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_one);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar1 = getSupportActionBar();
        actionbar1.setDisplayHomeAsUpEnabled(true);
        actionbar1.setTitle("Edit The Lens you choosed.");

        Intent intent = getIntent();
        Lens temp = LensManager.getInstance().retrieve(intent.getIntExtra("index",0));
        TextView changetext = findViewById(R.id.EditLensDetail);
        changetext.setText(temp.getMake() + " " + temp.getFocalLengthInMM() + "mm F" + temp.getMaxAperture() );
        CenterTextView();
        ActivateButton(temp.getMaxAperture());

    }


    private void CenterTextView(){
        TextView askMake = findViewById(R.id.askMake);
        TextView AskFocal = findViewById(R.id.askFocal);
        TextView askAperture = findViewById(R.id.askAperture);

        Display display = getWindowManager().getDefaultDisplay();
        int displayWith = display.getWidth() / 2;
        askMake.setWidth(displayWith);
        AskFocal.setWidth(displayWith);
        askAperture.setWidth(displayWith);
    }

    private void ActivateButton(final double maxAperture) {
        final Button calculation = (Button)findViewById(R.id.Edit);
        calculation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String EditededMake = ((EditText)findViewById(R.id.EditMake)).getText().toString();
                String EditedFocal = ((EditText)findViewById(R.id.EditFocal)).getText().toString();
                String EditedAperture = ((EditText)findViewById(R.id.EditAperture)).getText().toString();

                if(EditedFocal.matches("" ) || EditedAperture.matches("")){
                    Toast.makeText(EditOne.this, "Please input all Fields", Toast.LENGTH_SHORT).show();
                }else{
                    int Focal = Integer.parseInt(EditedFocal);
                    double Aperture = Double.parseDouble(EditedAperture);

                    if(Aperture > 22 || Aperture < maxAperture){
                        Toast.makeText(EditOne.this, "Aperture is not valid.",Toast.LENGTH_SHORT).show();
                    }else if(Focal < 0 ){
                        Toast.makeText(EditOne.this,"Coc should be positive", Toast.LENGTH_SHORT).show();
                    }else{
                        Intent intent = getIntent();
                        Lens EditLens = LensManager.getInstance().retrieve(intent.getIntExtra("index",0));
                        int indexNeeded = LensManager.getInstance().returnint(intent.getIntExtra("index",0));
                        LensManager.getInstance().EditLen(EditededMake,Aperture,Focal,indexNeeded);

                        Intent Home=new Intent(EditOne.this,MainActivity.class);
                        startActivity(Home);
//                        finish();


                    }
                }
            }
        });{

        };
    }


}