package com.alvesgleibson.listatarefas.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



import androidx.recyclerview.widget.RecyclerView;

import com.alvesgleibson.listatarefas.R;
import com.alvesgleibson.listatarefas.model.Task;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Task> myTaskList;

    public MyAdapter(List<Task> myTaskList){
        this.myTaskList = myTaskList;
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_layout_task, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Task mTask = myTaskList.get(position);
        holder.textViewMyViewHold.setText(mTask.getName());

    }

    @Override
    public int getItemCount() {
        return this.myTaskList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textViewMyViewHold;

        public MyViewHolder(View itemView) {
            super(itemView);

            textViewMyViewHold = itemView.findViewById(R.id.textViewTask);

        }
    }










}
