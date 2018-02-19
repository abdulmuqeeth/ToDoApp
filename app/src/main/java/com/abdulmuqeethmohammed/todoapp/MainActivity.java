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
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DbHelper dbHelper;
    private FloatingActionButton addTaskButton;
    private ListView mainPageList;
    private ArrayList<String> arrayList;
    private ArrayAdapter<String> arrayAdapter;
    private String taskDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.main_activity_title);
        setContentView(R.layout.activity_main);

        dbHelper = new DbHelper(this);
        mainPageList = (ListView) findViewById(R.id.listViewMain);
        addTaskButton = (FloatingActionButton) findViewById(R.id.addButton);
        addTaskButton.setOnClickListener(addActivity);

        loadTasks();
    }

    //OnClickListener for addActivity Button
    private View.OnClickListener addActivity = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switchToTaskDetails();
        }
    };

    //Using an intent to start TaskDetailsActivity for result
    private void switchToTaskDetails() {
        Intent startTaskActivityIntent = new Intent(MainActivity.this, TaskDetailsActivity.class);
        MainActivity.this.startActivityForResult(startTaskActivityIntent, AppConstants.REQUEST_CODE);
    }

    //After returning from sub activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Checking if we are returning from TaskDetailsActivity
        if(requestCode==AppConstants.REQUEST_CODE){
            //When data is returned from TaskDetailsActivity
            if(resultCode==RESULT_OK){
                try {
                    taskDetails = data.getStringExtra(AppConstants.TASK_DETAILS_KEY);
                }catch (NullPointerException e){
                    Log.i(AppConstants.EXCEPTION_TAG, "NULL POINTER");
                }
                dbHelper.insertNewTask(taskDetails);
                loadTasks();
            }
            //When no data is returned from TaskDetailsActivity
            else if(resultCode==RESULT_CANCELED){
                Log.i(AppConstants.RESULT_CODE_CHECK, "CANCELLED");
            }
        }
        else {
            Log.i(AppConstants.REQUEST_CODE_CHECK, "INCORRECT");
        }
    }

    //Method to load tasks from DB and display on the list
    private void loadTasks() {
        arrayList = dbHelper.getTaskList();
        if (arrayAdapter == null) {
            arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
            //Linking the ListView to the ArrayAdapter
            mainPageList.setAdapter(arrayAdapter);
        }
        else {
            arrayAdapter.clear();
            arrayAdapter.addAll(arrayList);
            arrayAdapter.notifyDataSetChanged();
        }
    }
}
