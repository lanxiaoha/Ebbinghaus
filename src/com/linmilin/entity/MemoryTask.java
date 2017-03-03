package com.linmilin.entity;

import java.util.UUID;

/**
 * Created by lan on 17/2/28.
 */
public class MemoryTask {

    private MemoryData memoryData;

    public MemoryTask(MemoryData memoryData) {
        this.memoryData = memoryData;
    }

    public static MemoryData createMemoryData(String title,String detail) {

        MemoryData memoryData = new MemoryData();
        memoryData.setId(UUID.randomUUID().toString());

        memoryData.setTitle(title);

        memoryData.setDetail(detail);

        memoryData.setCurMemoryStage(IMemoryStage.CREATE);

        long createDate = System.currentTimeMillis();
        memoryData.setCreateMemoryDate(createDate);
        memoryData.setPreMemoryDate(createDate);

        long nextStageDate = DateBuilder.buildNextStageDate(createDate, IMemoryStage.CREATE);
        memoryData.setNextMemoryAlermDate(nextStageDate);

        return memoryData;
    }


    public void nextStage() {

        int curMemoryStage = memoryData.getCurMemoryStage();
        if (curMemoryStage == IMemoryStage.OVER) {
            memoryData.setOver(true);
            return;
        } else {
            long preMemoryDate = System.currentTimeMillis();
            memoryData.setPreMemoryDate(preMemoryDate);

            curMemoryStage = curMemoryStage + 1;
            memoryData.setCurMemoryStage(curMemoryStage);
            if (curMemoryStage == IMemoryStage.OVER) {
                memoryData.setOver(true);
            } else {
                long nextMemoryAlermDate = DateBuilder.buildNextStageDate(memoryData.getPreMemoryDate(), curMemoryStage);
                memoryData.setNextMemoryAlermDate(nextMemoryAlermDate);
                memoryData.setOver(false);
            }

        }

    }

    public boolean isOver() {

        return memoryData.isOver();
    }

    public long getNextMemoryAlermDate() {
        return memoryData.getNextMemoryAlermDate();
    }

    @Override
    public String toString() {
        return memoryData.getTitle();
    }

    public void setTitle(String text) {
        memoryData.setTitle(text);
    }

    public void setDetail(String text) {
            memoryData.setDetail(text);
    }

    public String getTitle() {

        return memoryData.getTitle();
    }

    public String getDetail() {

        return memoryData.getDetail();
    }

    public MemoryData getMemoryData() {
        return memoryData;
    }

    public int getStage() {
        return memoryData.getCurMemoryStage();
    }

    public String getId() {
        return memoryData.getId();
    }
}
