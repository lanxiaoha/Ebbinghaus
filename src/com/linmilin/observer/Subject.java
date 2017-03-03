package com.linmilin.observer;

import com.linmilin.entity.MemoryTask;

/**
 * Created by lan on 17/2/28.
 */
public interface Subject {

    void addObserver(Observer observer);
    void removeObserver(Observer observer);

    /**
     * 通知有个新的记忆任务
     * @param task
     */
    void notifyNewTask(MemoryTask task);
}
