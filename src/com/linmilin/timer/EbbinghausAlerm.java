package com.linmilin.timer;

import com.linmilin.entity.MemoryData;
import com.linmilin.entity.MemoryTask;
import com.linmilin.observer.Observer;
import com.linmilin.observer.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by lan on 17/2/28.
 */
public class EbbinghausAlerm implements Subject {

    private List<Observer> observerList = new ArrayList<>();
    private List<MemoryTask> memoryList;
    private ScheduledExecutorService service;


    public void alerm(List<MemoryTask> taskList){
        this.memoryList = taskList;
        if(memoryList == null){
            return;
        }
        service = Executors.newScheduledThreadPool(10);

        for(int i = memoryList.size()-1;i>=0;i--){
            MemoryTask task = memoryList.get(i);
            calTask(task);


        }
    }

    private void calTask(MemoryTask task){

        if(task.isOver()){

            for(Observer observer : observerList){
                observer.notifyOver(task);
            }
            memoryList.remove(task);
            return;
        }

        long curDate = System.currentTimeMillis();
        long nextAlermDate = task.getNextMemoryAlermDate();
        long relativeDate = nextAlermDate - curDate;

        System.out.println("relativeDate:"+relativeDate);

        if(relativeDate <= 0){//这个记忆都过期了，肯定提醒赶快复习。
            for(Observer observer : observerList){
                observer.notifyTask(task);
            }
            memoryList.remove(task);

        }else{
            addAlerm(relativeDate,task);
        }
    }

    private void addAlerm(long relateDate,MemoryTask task){

        service.schedule(new Runner(task),relateDate, TimeUnit.MILLISECONDS);
    }

    @Override
    public void addObserver(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyNewTask(MemoryTask task) {
        addTask(task);
    }

    private class Runner implements Runnable{

        private MemoryTask memoryTask;

        public Runner(MemoryTask memoryTask){
            this.memoryTask = memoryTask;
        }
        @Override
        public void run() {

            for(Observer observer : observerList){
                observer.notifyTask(memoryTask);
            }

        }
    };

    public void addTask(MemoryTask task){
        memoryList.add(task);
        calTask(task);
    }

}
