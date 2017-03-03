package com.linmilin.view;

import com.linmilin.controller.IView;
import com.linmilin.controller.IViewUpdateListener;
import com.linmilin.entity.IMemoryStage;
import com.linmilin.entity.MemoryData;
import com.linmilin.entity.MemoryTask;
import sun.jvm.hotspot.types.JBooleanField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by lan on 17/3/1.
 */
public class DetailPanel extends Panel {

    private MemoryTask memoryTask;
    private IView iView;
    private final JTextField jTitleField;
    private final JTextArea jDetailArea;
    private final JButton jEditBtn;
    private final JButton jOkBtn;

    private boolean isClickNewBtn;

    private boolean isEdit;
    private final JButton jNewBtn;
    private final JLabel jTip;
    private final JLabel jStageLabel;
    private final JLabel jStageLabel1;

    public DetailPanel(IView iView) {

        this.iView = iView;
        iView.setViewUpdateListener(viewUpdateListener);
        setBounds(150, 0, 350, 400);

        Box verticalBox = Box.createVerticalBox();

        JLabel jTitle = new JLabel("标题");
        Box firstBox = Box.createHorizontalBox();
        jTitleField = new JTextField(20);
        firstBox.add(jTitle);
        jTitleField.setEnabled(false);
        firstBox.add(jTitleField);
        verticalBox.add(firstBox);

        Box secondBox = Box.createHorizontalBox();

        JLabel jDetailLabel = new JLabel("详情");
        secondBox.add(jDetailLabel);

        jDetailArea = new JTextArea(10, 25);
        jDetailArea.setEnabled(false);
        Panel panel = new Panel();
        panel.add(jDetailArea);
        panel.setBounds(0, 0, 200, 200);
        panel.add(new JScrollPane(jDetailArea));
        secondBox.add(panel);
        verticalBox.add(secondBox);

        Box second2Box = Box.createHorizontalBox();
        jStageLabel1 = new JLabel("当前复习阶段");
        jStageLabel = new JLabel("");
        second2Box.add(jStageLabel1);
        second2Box.add(jStageLabel);
        verticalBox.add(second2Box);

        Box thirdBox = Box.createHorizontalBox();
        jNewBtn = new JButton("新建");
        jEditBtn = new JButton("编辑");
        jOkBtn = new JButton("已复习");
        JButton jDeleteBtn = new JButton("删除");
        thirdBox.add(jNewBtn);
        thirdBox.add(jEditBtn);
        thirdBox.add(jOkBtn);
        thirdBox.add(jDeleteBtn);
        verticalBox.add(thirdBox);

        Box thourBox = Box.createHorizontalBox();
        jTip = new JLabel();
        thourBox.add(jTip);
        verticalBox.add(thourBox);

        add(verticalBox);

        jNewBtn.addActionListener(newBtnListener);
        jEditBtn.addActionListener(editBtnListener);
        jOkBtn.addActionListener(okBtnListener);
        jDeleteBtn.addActionListener(deleteBtnListener);
    }

    private IViewUpdateListener viewUpdateListener = new IViewUpdateListener() {
        @Override
        public void viewUpdate(MemoryTask task) {

            isClickNewBtn = false;
            isEdit = false;
            jTitleField.setEnabled(false);
            jDetailArea.setEnabled(false);
            jEditBtn.setText("编辑");
            jNewBtn.setEnabled(true);
            jEditBtn.setEnabled(true);
            jOkBtn.setEnabled(true);

            memoryTask = task;
            jTip.setText("");
            if (memoryTask == null) {
                jTitleField.setText("");
                jDetailArea.setText("");
                jStageLabel1.setText("");
                jStageLabel.setText("");
            } else {

                jTitleField.setText(memoryTask.getTitle());
                jDetailArea.setText(memoryTask.getDetail());
                jStageLabel1.setText("");

                if (memoryTask.getStage() == IMemoryStage.OVER) {
                    jStageLabel.setText("已经复习完成，相信你不会忘了");
                    jEditBtn.setEnabled(false);
                    jOkBtn.setEnabled(false);

                } else {
                    jStageLabel.setText("第" + memoryTask.getStage() + "次复习");

                }


            }


        }
    };

    private ActionListener newBtnListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            if (isEdit) {
                jTip.setText("请先保存");
                return;
            }


            if (!isClickNewBtn) {
                isClickNewBtn = true;
                jTitleField.setEnabled(true);
                jDetailArea.setEnabled(true);
                jTitleField.setText("");
                jDetailArea.setText("");

                jStageLabel1.setText("创建新任务");
                jStageLabel.setText("");

                jNewBtn.setText("保存");
            } else {
                String title = jTitleField.getText();
                if (title.trim().equals("")) {
                    jTip.setText("标题不能为空");
                    return;
                }


                isClickNewBtn = false;
                jNewBtn.setText("新建");

                jTitleField.setEnabled(false);
                jDetailArea.setEnabled(false);

                String detail = jDetailArea.getText();
                MemoryData newMemoryData = MemoryTask.createMemoryData(title, detail);
                MemoryTask newMemoryTask = new MemoryTask(newMemoryData);

                if (memoryTask != null) {
                    jTitleField.setText(memoryTask.getTitle());
                    jDetailArea.setText(memoryTask.getDetail());

                    jStageLabel1.setText("当前复习阶段");
                    if (memoryTask.getStage() == IMemoryStage.OVER) {
                        jStageLabel.setText("已经复习完成，相信你不会忘了");
                        jEditBtn.setEnabled(false);
                        jOkBtn.setEnabled(false);
                    } else {
                        jStageLabel.setText("第" + memoryTask.getStage() + "次复习");
                    }

                    jTip.setText("<html><body>已经新建任务成功，加入到下一次到复习提醒，<br>当前显示的是上一次选择的任务</body></html>\n");
                } else {
                    jTitleField.setText("");
                    jDetailArea.setText("");
                    jTip.setText("已经新建任务成功，到下一次到复习提醒");
                }

                iView.clcikNewSave(newMemoryTask);

            }

        }
    };


    private ActionListener editBtnListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            if (isClickNewBtn) {
                jTip.setText("请先保存");
                return;
            }


            if (memoryTask == null) {
                jTip.setText("请先选择任务");
                return;
            }

            if (!isEdit) {
                isEdit = true;
                jTitleField.setEnabled(true);
                jDetailArea.setEnabled(true);
                jEditBtn.setText("保存");
                jOkBtn.setEnabled(false);
                jNewBtn.setEnabled(false);
                jTip.setText("当前处于编辑状态");
            } else {

                isEdit = false;
                jTitleField.setEnabled(false);
                jDetailArea.setEnabled(false);
                jEditBtn.setText("编辑");
                jNewBtn.setEnabled(true);
                jOkBtn.setEnabled(true);
                if (memoryTask != null) {
                    memoryTask.setTitle(jTitleField.getText());
                    memoryTask.setDetail(jDetailArea.getText());
                    iView.clickEditSave(memoryTask);
                    jTip.setText("编辑保存成功");
                } else {
                    jTip.setText("");
                }
            }

        }
    };

    private ActionListener okBtnListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            if (memoryTask != null) {
                iView.clickNext(memoryTask);
                memoryTask = null;
                jTitleField.setText("");
                jDetailArea.setText("");
                jStageLabel1.setText("");
                jStageLabel.setText("");
                jTip.setText("复习完成，已加入到下一次到复习提醒");

            } else {

                jTip.setText("先选择要复习的任务");
            }

        }
    };

    private ActionListener deleteBtnListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            if (memoryTask != null) {
                iView.deleteTask(memoryTask);
                memoryTask = null;
                jTitleField.setText("");
                jDetailArea.setText("");
                jStageLabel1.setText("");
                jStageLabel.setText("");
                jTip.setText("删除成功");

            } else {

                jTip.setText("先选择要删除的任务");
            }

        }
    };
}
