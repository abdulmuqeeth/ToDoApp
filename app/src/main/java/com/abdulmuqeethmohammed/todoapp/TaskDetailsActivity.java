package com.abdulmuqeethmohammed.todoapp;

/*
 * Created by AbdulMuqeeth Mohammed
 */

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class TaskDetailsActivity extends AppCompatActivity {

    private EditText taskDetails;
    private FloatingActionButton submitTaskButton;
    private String retrievedData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.task_activity_title);
        setContentView(R.layout.activity_task_details);

        taskDetails = (EditText) findViewById(R.id.taskDetails);
        submitTaskButton = (FloatingActionButton) findViewById(R.id.submitTaskButton);

        submitTaskButton.setOnClickListener(submitButton);
    }

    public boolean onCreateOptionsMenu (Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.options_menu,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem){
        //TODO respond to menu item selecetion
        return true;
    }

    //Capturing the text and returning to calling activity when done button is pressed
    private View.OnClickListener submitButton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent retrievedIntent = getIntent();
            retrievedData = taskDetails.getText().toString().trim();
            if(retrievedData.isEmpty()) {
                    setResult(RESULT_CANCELED, retrievedIntent);
            }
            else {
                    setResult(RESULT_OK, retrievedIntent);
                    retrievedIntent.putExtra(AppConstants.TASK_DETAILS_KEY, retrievedData);
            }
            finish();
        }
    };
}
