package com.example.assignment2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.assignment2.model.Lens;
import com.example.assignment2.model.LensManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class EditLen extends AppCompatActivity {
    private LensManager temp1 = LensManager.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_len);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Select one lens you want to edit.");
        showEditView();
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);


    }

//
    private void showEditView() {
        ArrayList<String> editLens = new ArrayList<>();
        ListView listview = null;
        listview = (ListView) findViewById(R.id.EditLens);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, editLens);
        listview.setAdapter(arrayAdapter);

        for (int i = 0; i < temp1.size(); i++) {
            Lens temp = temp1.retrieve(i);

            editLens.add(temp.getMake() + " " + temp.getFocalLengthInMM() + "mm F" + temp.getMaxAperture());

        }

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = makeLaunchIntent(EditLen.this, position);
                startActivity(intent);
            }


        });
    }


    private Intent makeLaunchIntent(Context context, int position) {
        Intent intent = new Intent(context, EditOne .class);
        intent.putExtra("index",position);
        return intent;
    }


}