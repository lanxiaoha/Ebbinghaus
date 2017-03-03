package com.linmilin.controller;

import com.linmilin.entity.MemoryData;
import com.linmilin.entity.MemoryTask;
import com.linmilin.observer.Observer;
import com.linmilin.observer.Subject;
import com.linmilin.storer.Storer;
import com.linmilin.storer.json.JsonStorer;
import org.omg.PortableInterceptor.SUCCESSFUL;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 执行器
 * Created by lan on 17/2/28.
 */
public class EbbinghausController implements Observer, IView {

    private List<Subject> subjectList = new ArrayList<>();

    private DefaultListModel<MemoryTask> listModel;
    private DefaultListModel<MemoryTask> overLististModel;

    private IViewUpdateListener viewUpdateListener;


    //所有的数据对象
    private List<MemoryTask> taskList;
    private Storer storer;

    public EbbinghausController() {
        listModel = new DefaultListModel<>();
        overLististModel = new DefaultListModel<>();

        init();
    }

    private void init(){

        storer = new JsonStorer();

        List<MemoryData> memoryDataList = storer.getAll();
        if(memoryDataList == null){
            return;
        }
        taskList = new ArrayList<>();
        for(MemoryData data : memoryDataList){
            taskList.add(new MemoryTask(data));
        }

    }

    public List<MemoryTask> getAllMemoryTaskList(){
        return taskList;
    }

    @Override
    public void notifyOver(MemoryTask task) {
        System.out.println("这个记忆已经完成了..." + task.toString());
        listModel.removeElement(task);
        overLististModel.addElement(task);
    }

    @Override
    public void notifyTask(MemoryTask task) {

        System.out.println("这个记忆到点了。。要进行处理了..." + task.toString());
        listModel.addElement(task);

    }

    @Override
    public void addSubject(Subject subject) {
        subjectList.add(subject);
    }

    @Override
    public void removeSubject(Subject subject) {
        subjectList.remove(subject);
    }


    @Override
    public void clickEditSave(MemoryTask memoryTask) {
        storer.update(memoryTask.getMemoryData());

    }

    @Override
    public void clcikNewSave(MemoryTask memoryTask) {

        storer.create(memoryTask.getMemoryData());
        for (Subject subject : subjectList) {
            subject.notifyNewTask(memoryTask);
        }

    }

    @Override
    public void clickNext(MemoryTask memoryTask) {

        listModel.removeElement(memoryTask);
        memoryTask.nextStage();
        for (Subject subject : subjectList) {
            subject.notifyNewTask(memoryTask);
        }
        storer.update(memoryTask.getMemoryData());
    }

    @Override
    public void selectTask(MemoryTask memoryTask) {

        if (viewUpdateListener != null) {
            viewUpdateListener.viewUpdate(memoryTask);
        }

    }

    @Override
    public DefaultListModel<MemoryTask> getListModel() {
        return listModel;
    }


    @Override
    public ListModel getOverListModel() {
        return overLististModel;
    }

    @Override
    public void deleteTask(MemoryTask memoryTask) {

        try{
            overLististModel.removeElement(memoryTask);
            listModel.removeElement(memoryTask);
            storer.delete(memoryTask.getId());
        }catch (Exception e){

        }

    }

    @Override
    public void setViewUpdateListener(IViewUpdateListener listener) {

        viewUpdateListener = listener;
    }


}
