package com.example.assignment2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.assignment2.model.Lens;
import com.example.assignment2.model.LensManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private LensManager manager = LensManager.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        toolbar.setTitle("Depth of Field Calculator");
        showListView();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddLenseActivity.class);
                startActivityForResult(intent,1);
            }
        });

        FloatingActionButton edit = findViewById(R.id.edit);
        if(manager.size() != 0) {

            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, EditLen.class);
                    startActivityForResult(intent, 1);
                }
            });
        }else if(manager.size() == 0){
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this, "Please Add a new Lens.\n At the bottom right corner \"+\" sign.",Toast.LENGTH_LONG).show();
                }
            });

        }

        FloatingActionButton delete = findViewById(R.id.delete);
        if(manager.size() != 0) {
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, delete.class);
                    startActivityForResult(intent, 1);
                }
            });
        }else if(manager.size() == 0){
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this, "Please Add a new Lens.\n At the bottom right corner \"+\" sign.",Toast.LENGTH_LONG).show();
                }
            });

        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == 1){
            Bundle user = data.getBundleExtra("saveLen");
            String make = user.getString("make");
            String aperture = user.getString("aperture");
            String focal = user.getString("focal");

            manager.add(new Lens(make,Double.parseDouble(aperture),Integer.parseInt(focal)));
            showListView();
        }
    }

    private void showListView() {
        ArrayList<String> inputLens = new ArrayList<>();
        ListView listview = null;
       if(manager.size() == 0){
           Toast.makeText(MainActivity.this, "Please Add a new Lens.\n At the bottom right corner \"+\" sign.",Toast.LENGTH_LONG).show();
       }else{
           for (int i = 0; i < manager.size(); i++) {
               Lens temp = manager.retrieve(i);
               inputLens.add(temp.getMake() + " " + temp.getFocalLengthInMM() + "mm F" + temp.getMaxAperture());
           }
       }


        listview = (ListView) findViewById(R.id.LensListing);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, inputLens);
        listview.setAdapter(arrayAdapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = makeLaunchIntent(MainActivity.this, position);
                startActivity(intent);
            }


        });
    }
    private Intent makeLaunchIntent(Context context, int position) {
        Intent intent = new Intent(context, CalculateDoFActivity.class);
        intent.putExtra("index",position);
        return intent;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}