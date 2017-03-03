package com.linmilin.controller;

import com.linmilin.entity.MemoryData;
import com.linmilin.entity.MemoryTask;

import javax.swing.*;
import java.util.List;

/**
 * Created by lan on 17/3/2.
 */
public interface IView {

    /**
     * 编辑数据后，点击保存。保存数据
     * @param memoryTask
     */
    public void clickEditSave(MemoryTask memoryTask);
    public void clcikNewSave(MemoryTask memoryTask);
    /**
     *  点击已复习按钮
     * @param memoryTask
     */
    public void clickNext(MemoryTask memoryTask);

    /**
     * list列表里面选择了一个任务
     * @param memoryTask
     */
    public void selectTask(MemoryTask memoryTask);

    /**
     * 得到正在进行任务的list列表的模型
     * @return
     */
    public DefaultListModel<MemoryTask> getListModel();

    public void setViewUpdateListener(IViewUpdateListener listener);

    /**
     * 已经完成任务的list列表的模型
     * @return
     */
    ListModel getOverListModel();

    /**
     * 删除任务
     * @param memoryTask
     */
    public void deleteTask(MemoryTask memoryTask);
}
