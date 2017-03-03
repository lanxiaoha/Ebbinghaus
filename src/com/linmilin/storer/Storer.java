package com.linmilin.storer;

import com.linmilin.entity.MemoryData;

import java.util.List;

/**
 * Created by lan on 17/2/28.
 */
public interface Storer {


    List<MemoryData> getAll();

    public void delete(String id);

    public void update(MemoryData memoryData);

    public void create(MemoryData memoryData);

    void save();
}
