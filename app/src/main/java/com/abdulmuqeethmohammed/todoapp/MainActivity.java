package com.abdulmuqeethmohammed.todoapp;

/*
 * Created by AbdulMuqeeth Mohammed
 */

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fab1;
    ListView mainPageList;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;
    private String taskDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(getClass().getSimpleName(), "Inside oOCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab1 = (FloatingActionButton) findViewById(R.id.addButton);
        fab1.setOnClickListener(addActivity);

        mainPageList = (ListView) findViewById(R.id.listViewMain);
        arrayList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
        //Linking the ListView to the ArrayAdapter
        mainPageList.setAdapter(arrayAdapter);
    }

    //OnClickListener for addActivity Button
    private View.OnClickListener addActivity = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.i(MainActivity.this.getClass().getSimpleName(),"Add Button Clicked");
            switchToTaskDetails();
        }
    };

    //Using an intent to start TaskDetailsActivity for result
    private void switchToTaskDetails(){
        Intent i = new Intent(MainActivity.this, TaskDetailsActivity.class);
        MainActivity.this.startActivityForResult(i, AppConstants.REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==AppConstants.REQUEST_CODE){
            if(resultCode==RESULT_OK){
                try {
                    taskDetails = data.getStringExtra(AppConstants.TAG);
                    Log.i(MainActivity.this.getClass().getSimpleName(), "Task Details Retrieved");
                }catch (NullPointerException e){
                    Log.i(AppConstants.EXCEPTION_TAG, "Null Pointer");
                }
                arrayList.add(taskDetails);
                arrayAdapter.notifyDataSetChanged();

            }
            else if(resultCode==RESULT_CANCELED){
                Log.i("Result Code", "CANCELLED");
            }
        }
        else {
            Log.i(AppConstants.REQUEST_CODE_CHECK,AppConstants.REQUEST_CODE_FAIL);
        }

    }
}
