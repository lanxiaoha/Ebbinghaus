package com.linmilin.view;

import com.linmilin.controller.IView;
import com.linmilin.entity.MemoryData;
import com.linmilin.entity.MemoryTask;

import javax.swing.*;
import java.awt.*;

/**
 * Created by lan on 17/2/28.
 */
public class MainPanel extends Panel {


    public MainPanel(IView iView){

        setLayout(null);
        ListPanel listPanel = new ListPanel(iView);
        DetailPanel detailPanel = new DetailPanel(iView);

        add(listPanel);
        add(detailPanel);
    }
}
