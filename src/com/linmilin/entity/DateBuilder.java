package com.linmilin.entity;

/**
 * Created by lan on 17/2/28.
 */
public class DateBuilder {

    /**
     * 生成下一次的提醒时间
     *
     * @param createDate
     * @param curMemoryStage
     * @return
     */
    public static long buildNextStageDate(long createDate, int curMemoryStage) {

        long relateTime = nextRelativeStageTime(curMemoryStage);
        return createDate + relateTime;
    }

    private static long nextRelativeStageTime1(int curMemoryStage) {

        if (curMemoryStage == IMemoryStage.CREATE) {
            return  10 * 1000;
        }

        if (curMemoryStage == IMemoryStage.FIRST) {
            return 15 * 1000;
        }

        if (curMemoryStage == IMemoryStage.SECOND) {
            return 20 * 1000;
        }

        if (curMemoryStage == IMemoryStage.THIRD) {
            return 30 * 1000;
        }

        if (curMemoryStage == IMemoryStage.FOURTH) {
            return 40 * 1000;
        }

        if (curMemoryStage == IMemoryStage.FIFTH) {
            return 50 * 1000;
        }

        if (curMemoryStage == IMemoryStage.SIXTH) {
            return 60 * 1000;
        }
        if (curMemoryStage == IMemoryStage.SEVENTH) {
            return 70 * 1000;
        }

        return 0;
    }


    private static long nextRelativeStageTime(int curMemoryStage) {

        if (curMemoryStage == IMemoryStage.CREATE) {
            return 5 * 60 * 1000;
        }

        if (curMemoryStage == IMemoryStage.FIRST) {
            return 30 * 60 * 1000;
        }

        if (curMemoryStage == IMemoryStage.SECOND) {
            return 12 * 60 * 60 * 1000;
        }

        if (curMemoryStage == IMemoryStage.THIRD) {
            return 24 * 60 * 60 * 1000;
        }

        if (curMemoryStage == IMemoryStage.FOURTH) {
            return 2 * 24 * 60 * 60 * 1000;
        }

        if (curMemoryStage == IMemoryStage.FIFTH) {
            return 4 * 24 * 60 * 60 * 1000;
        }

        if (curMemoryStage == IMemoryStage.SIXTH) {
            return 7 * 24 * 60 * 60 * 1000;
        }
        if (curMemoryStage == IMemoryStage.SEVENTH) {
            return 15 * 24 * 60 * 60 * 1000;
        }

        return 0;
    }
}
