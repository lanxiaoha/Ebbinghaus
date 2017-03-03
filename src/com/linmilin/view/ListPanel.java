package com.linmilin.view;

import com.linmilin.controller.IView;
import com.linmilin.entity.MemoryTask;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

/**
 * Created by lan on 17/3/1.
 */
public class ListPanel extends Panel implements ListSelectionListener {

    private IView iView;
    private final JList jList;
    private final JList jOverList;

    public ListPanel(IView iView){

        this.iView = iView;
        setBounds(0,0,150,400);
        setLayout(new BorderLayout());

        Box verticalBox = Box.createVerticalBox();

        JLabel jLabel = new JLabel("要复习的任务");
        verticalBox.add(jLabel);

        jList = new JList(iView.getListModel());
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        verticalBox.add(new JScrollPane(jList));

        jLabel = new JLabel("已完成的任务");
        jOverList = new JList(iView.getOverListModel());
        verticalBox.add(jLabel);
        verticalBox.add(new JScrollPane(jOverList));

        add(verticalBox);

        jList.addListSelectionListener(this);
        jOverList.addListSelectionListener(overListListener);

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

        Object obj = jList.getSelectedValue();
        iView.selectTask((MemoryTask) obj);
    }

    private ListSelectionListener overListListener = new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {

            Object obj = jOverList.getSelectedValue();
            iView.selectTask((MemoryTask) obj);
        }
    };
}
