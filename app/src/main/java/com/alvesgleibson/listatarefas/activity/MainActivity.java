package com.alvesgleibson.listatarefas.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alvesgleibson.listatarefas.R;
import com.alvesgleibson.listatarefas.adapter.MyAdapter;
import com.alvesgleibson.listatarefas.helper.MyRecyclerItemClickListener;
import com.alvesgleibson.listatarefas.helper.TaskDao;
import com.alvesgleibson.listatarefas.model.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fab;
    private RecyclerView mRecyclerView;
    private MyAdapter myAdapter;
    private List<Task> myTaskList = new ArrayList<>();
    private Task taskDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.fabMain);
        mRecyclerView = findViewById(R.id.recyclerListaTarefa);


        mRecyclerView.addOnItemTouchListener(
                new MyRecyclerItemClickListener(getApplicationContext(), mRecyclerView, new MyRecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        Task task = myTaskList.get(position);

                        Intent intent = new Intent(MainActivity.this, NewActivity.class);
                        intent.putExtra("taskSelected", task);

                        startActivity( intent );


                    }


                    @Override
                    public void onLongItemClick(View view, int position) {

                        taskDelete = myTaskList.get( position );

                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        TaskDao taskDao = new TaskDao( getApplicationContext() );

                        //Setup the AlertDialog
                        builder.setTitle("Confirm Deletion ");
                        builder.setMessage("Would you really like to delete : "+taskDelete.getName()+" ?");
                        builder.setIcon(R.drawable.ic_delete_app);
                        builder.setCancelable(false);

                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                if(taskDao.deleteTask( taskDelete )) {
                                    Toast.makeText(getApplicationContext(), "Deleted with Success!!! ", Toast.LENGTH_SHORT).show();
                                    loadingTask();
                                }else {
                                    Toast.makeText(getApplicationContext(), "Problem Delete ", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        builder.setNegativeButton("No", null);
                        builder.create();
                        builder.show();



                    }

                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    }
                })

        );


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), NewActivity.class);
                startActivity(intent);

            }
        });


    }
        public void loadingTask() {

            //List Task

            TaskDao taskDao = new TaskDao(getApplicationContext());
            myTaskList = taskDao.listTask();

            //Show list of task in the RecycleView

            //Setting a Adapter

            myAdapter = new MyAdapter(myTaskList);

            //Setting RecycleView


            RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());



            mRecyclerView.setLayoutManager(manager);
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.addItemDecoration( new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));

            mRecyclerView.setAdapter( myAdapter );



    }

    @Override
    protected void onStart() {
        loadingTask();
        super.onStart();
    }
}