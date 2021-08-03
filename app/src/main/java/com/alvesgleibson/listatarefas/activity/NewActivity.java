package com.alvesgleibson.listatarefas.activity;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alvesgleibson.listatarefas.R;
import com.alvesgleibson.listatarefas.helper.TaskDao;
import com.alvesgleibson.listatarefas.model.Task;
import com.google.android.material.textfield.TextInputEditText;

public class NewActivity extends AppCompatActivity {

    private TextInputEditText editTextVar;
    private Task taskCallBackMainActivity;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        editTextVar = findViewById(R.id.idInputTxt);

        taskCallBackMainActivity = (Task) getIntent().getSerializableExtra( "taskSelected" );

        if (taskCallBackMainActivity != null){

            editTextVar.setText( taskCallBackMainActivity.getName() );

        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

       getMenuInflater().inflate(R.menu.my_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {

            case R.id.menuSave:

                TaskDao taskDao = new TaskDao( getApplicationContext() );
                if(taskCallBackMainActivity != null){

                    String s = editTextVar.getText().toString();

                    if ( !s.isEmpty() ){

                        Task task = new Task();
                        task.setName( s );
                        task.setId( taskCallBackMainActivity.getId() );


                        if (taskDao.upgradeTask( task )){
                            Toast.makeText(getApplicationContext(), "Update with success!! ", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "Problem Updating ", Toast.LENGTH_SHORT).show();
                        }

                        finish();

                    }else {
                        Toast.makeText(getApplicationContext(), "Enter your note to update ", Toast.LENGTH_SHORT).show();
                    }

                }else{

                    Task task = new Task();
                    String s = editTextVar.getText().toString();

                    if ( s.equals("") ){
                        Toast.makeText(getApplicationContext(), "Enter your note to save", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        task.setName(s);

                        if (taskDao.saveTask(task))Toast.makeText(getApplicationContext(), "Saved with success!!!", Toast.LENGTH_SHORT).show();
                        else Toast.makeText(getApplicationContext(), "Problem Saving ", Toast.LENGTH_SHORT).show();

                        finish();
                    }
                }

                break;

        }
        return super.onOptionsItemSelected(item);
    }


}