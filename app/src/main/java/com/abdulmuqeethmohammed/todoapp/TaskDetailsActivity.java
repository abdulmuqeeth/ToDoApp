package com.abdulmuqeethmohammed.todoapp;

/*
 * Created by AbdulMuqeeth Mohammed
 */

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class TaskDetailsActivity extends AppCompatActivity {

    private EditText taskDetails;
    private FloatingActionButton submit;
    private String details;
    private final static String check ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(getClass().getSimpleName(),"Inside onCreate");
        setContentView(R.layout.activity_task_details);

        taskDetails = (EditText) findViewById(R.id.taskDetails);
        submit = (FloatingActionButton) findViewById(R.id.addButton2);

        submit.setOnClickListener(submitButton);

    }

    //Capturing the text and returning to calling activity when done button is pressed
    private View.OnClickListener submitButton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.i(getClass().getSimpleName(),"Inside Submit Button Listener");
            Intent i = getIntent();
            details=taskDetails.getText().toString().trim();
            if(details.equals(check)) {
                    Log.i(TaskDetailsActivity.this.getClass().getSimpleName(),"No data entered");
                    setResult(RESULT_CANCELED, i);
                    finish();
                }else {
                    setResult(RESULT_OK, i);
                    i.putExtra(AppConstants.TAG, taskDetails.getText().toString());
                    Log.i(TaskDetailsActivity.this.getClass().getSimpleName(), "Returning To Main Activity");
                }
            finish();
        }
    };
}
