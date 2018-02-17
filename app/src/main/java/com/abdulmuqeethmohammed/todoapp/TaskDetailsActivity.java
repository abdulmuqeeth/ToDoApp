package com.abdulmuqeethmohammed.todoapp;

/*
 * Created by AbdulMuqeeth Mohammed
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

public class TaskDetailsActivity extends AppCompatActivity {

    private EditText taskDetails;
    private String retrievedData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.task_activity_title);
        setContentView(R.layout.activity_task_details);

        taskDetails = (EditText) findViewById(R.id.taskDetails);
    }

    public boolean onCreateOptionsMenu (Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.options_menu,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch(menuItem.getItemId()) {
            case R.id.saveOption:
                retrieveData();
                return true;
        }
        return false;
    }

    //Capturing the text and returning to calling activity when save button is pressed
        private void retrieveData() {
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
}
