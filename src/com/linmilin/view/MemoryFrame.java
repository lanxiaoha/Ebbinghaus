package com.linmilin.view;

import com.linmilin.controller.EbbinghausController;
import com.linmilin.entity.MemoryTask;
import com.linmilin.timer.EbbinghausAlerm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * Created by lan on 17/2/28.
 */
public class MemoryFrame extends JFrame {

    public MemoryFrame(){


        EbbinghausAlerm alerm = new EbbinghausAlerm();
        EbbinghausController executor = new EbbinghausController();
        alerm.addObserver(executor);
        executor.addSubject(alerm);
        java.util.List<MemoryTask> memoryTaskList = executor.getAllMemoryTaskList();
        alerm.alerm(memoryTaskList);

        setTitle("艾宾浩斯遗忘曲线");
        setSize(500,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MainPanel mainPanel = new MainPanel(executor);
        add(mainPanel);
        setResizable(false);
        setVisible(true);
    }
}
