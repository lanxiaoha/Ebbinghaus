package com.linmilin.entity;

import java.sql.Time;
import java.util.UUID;

/**
 * Created by lan on 17/2/28.
 */
public class MemoryData {

    private boolean over;
    private String id;
    private String title;

    private String detail;
    /**
     * 当前记忆阶段
     */
    private int curMemoryStage;
    /**
     * 创建的日期
     */
    private long createMemoryDate;
    /**
     * 上一次的处理完毕的时间
     */
    private long preMemoryDate;

    /**
     * 下一次的提醒时间
     */
    private long nextMemoryAlermDate;

    public MemoryData() {

    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getCreateMemoryDate() {
        return createMemoryDate;
    }

    public void setCreateMemoryDate(long createMemoryDate) {
        this.createMemoryDate = createMemoryDate;
    }

    public int getCurMemoryStage() {
        return curMemoryStage;
    }

    public void setCurMemoryStage(int curMemoryStage) {
        this.curMemoryStage = curMemoryStage;
    }

    public long getNextMemoryAlermDate() {
        return nextMemoryAlermDate;
    }

    public void setNextMemoryAlermDate(long nextMemoryAlermDate) {
        this.nextMemoryAlermDate = nextMemoryAlermDate;
    }

    public long getPreMemoryDate() {
        return preMemoryDate;
    }

    public void setPreMemoryDate(long preMemoryDate) {
        this.preMemoryDate = preMemoryDate;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "MemoryData{" +
                "over=" + over +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", detail='" + detail + '\'' +
                ", curMemoryStage=" + curMemoryStage +
                ", createMemoryDate=" + createMemoryDate +
                ", preMemoryDate=" + preMemoryDate +
                ", nextMemoryAlermDate=" + nextMemoryAlermDate +
                '}';
    }
}


