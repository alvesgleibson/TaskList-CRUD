package com.alvesgleibson.listatarefas.helper;

import com.alvesgleibson.listatarefas.model.Task;

import java.util.List;

public interface InterfaceTaskDAO {

    public boolean saveTask(Task task);
    public boolean upgradeTask(Task task);
    public boolean deleteTask(Task task);
    public List<Task> listTask();

}
