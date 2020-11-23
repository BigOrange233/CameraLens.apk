package com.example.assignment2;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddLenseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lense);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Lens Details");
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.save:
                String saveMake = ((EditText)findViewById(R.id.newMake)).getText().toString();
                String saveAperture = ((EditText)findViewById(R.id.newAperture)).getText().toString();
                String saveFocal = ((EditText)findViewById(R.id.newFocal)).getText().toString();

                double Aperture = Double.parseDouble(saveAperture);
                int focal = Integer.parseInt(saveFocal);

                if(saveMake.matches("") || saveAperture.matches("") || saveFocal.matches("")){
                    Toast.makeText(AddLenseActivity.this, "Please input all Fields",Toast.LENGTH_SHORT).show();
                }else if(saveMake.length() <= 0){
                    Toast.makeText(AddLenseActivity.this,"You have to input a Make.",Toast.LENGTH_SHORT).show();
                }else if(Aperture < 1.4 || Aperture > 22){
                    Toast.makeText(AddLenseActivity.this, "The Aperture is invalid.",Toast.LENGTH_SHORT).show();
                }else if(focal <= 0){
                    Toast.makeText(AddLenseActivity.this,"The Focal Length is invalid.",Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putString("make",saveMake);
                    bundle.putString("aperture", saveAperture);
                    bundle.putString("focal", saveFocal);

                    intent.putExtra("saveLen", bundle);
                    setResult(RESULT_OK,intent);

                    finish();
                }
                return true;

            case android.R.id.home:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }
}