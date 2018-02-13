package com.abdulmuqeethmohammed.todoapp;

/*
 * Created by AbdulMuqeeth Mohammed
 */

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fab1;

    private static final int REQUEST_CODE = 800;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab1 = (FloatingActionButton) findViewById(R.id.addButton);
        fab1.setOnClickListener(addActivity);

    }
    //OnClickListener for addActivity Button
    private View.OnClickListener addActivity = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switchToTaskDetails();
        }
    };

    //Using an intent to start TaskDetailsActivity for result
    private void switchToTaskDetails(){
        Intent i = new Intent(MainActivity.this, TaskDetailsActivity.class);
        MainActivity.this.startActivityForResult(i, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //TODO Code for retrieving data from intent
    }
}
