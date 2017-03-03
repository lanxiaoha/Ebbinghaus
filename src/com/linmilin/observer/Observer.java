package com.linmilin.observer;

import com.linmilin.entity.MemoryData;
import com.linmilin.entity.MemoryTask;

/**
 * Created by lan on 17/2/28.
 */
public interface Observer {

    /**
     * 通知这个记忆已经完成了.
     * @param task
     */
    public void notifyOver(MemoryTask task);

    /**
     * 通知这个记忆到点了，赶快记忆.
     * @param task
     */
    public void notifyTask(MemoryTask task);

    public void addSubject(Subject subject);
    public void removeSubject(Subject subject);
}
