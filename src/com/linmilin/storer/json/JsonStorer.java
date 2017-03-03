package com.linmilin.storer.json;

import com.alibaba.fastjson.JSON;
import com.linmilin.entity.MemoryData;
import com.linmilin.storer.Storer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lan on 17/2/28.
 */
public class JsonStorer implements Storer {

    public static String FILE_DIR_PATH = "/Users/lan/Documents/ebbinghaus/file";
    public static final String FILE_NAME = "ebbinghaus.txt";
    private MemoryDataList memoryDataList;

    public JsonStorer() {

        init();
    }

    private void init() {

        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;

        FILE_DIR_PATH = ".";
        System.out.println("基本目录："+FILE_DIR_PATH);
        FILE_DIR_PATH =  FILE_DIR_PATH + File.separator + "data";
        try {
            File floder = new File(FILE_DIR_PATH);
            if (!floder.exists()) {
                floder.mkdirs();
            }
            File file = new File(floder, FILE_NAME);

            if (file.exists()) {
                FileInputStream fio = new FileInputStream(file);
                br = new BufferedReader(new InputStreamReader(fio));
                String line;

                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }

        String json = sb.toString();
        System.out.println("读取的json：" + json);
        if (json == null || "".equals(json)) {
            memoryDataList = new MemoryDataList();
            memoryDataList.memoryList = new ArrayList<>();
        } else {
            memoryDataList = JSON.parseObject(json, MemoryDataList.class);
        }
    }

    @Override
    public List<MemoryData> getAll() {
        if(memoryDataList == null){
            return null;
        }
        return memoryDataList.memoryList;
    }

    @Override
    public void delete(String id) {

        if (memoryDataList == null || memoryDataList.memoryList == null) {
            return;
        }
        List<MemoryData> memoryList = memoryDataList.memoryList;
        for (int i = memoryList.size() - 1; i >= 0; i--) {
            MemoryData memoryData = memoryList.get(i);
            if (memoryData.getId().equals(id)) {
                memoryList.remove(i);
            }
        }

        save();

    }

    @Override
    public void update(MemoryData memoryData) {

        if (memoryDataList == null || memoryDataList.memoryList == null) {
            return;
        }
        List<MemoryData> memoryList = memoryDataList.memoryList;
        for (int i = memoryList.size() - 1; i >= 0; i--) {
            MemoryData data = memoryList.get(i);
            if (data.getId().equals(memoryData.getId())) {
                memoryList.remove(i);
                memoryList.add(memoryData);
                break;
            }
        }

        save();


    }

    @Override
    public void create(MemoryData memoryData) {

        if (memoryDataList == null) {
            memoryDataList = new MemoryDataList();
            memoryDataList.memoryList = new ArrayList<>();
        }
        memoryDataList.memoryList.add(memoryData);

        save();

    }

    @Override
    public void save() {

        if (memoryDataList != null) {
            String json = JSON.toJSONString(memoryDataList);
            System.out.println("save json :" + json);
            BufferedWriter bw = null;
            try {
                File floder = new File(FILE_DIR_PATH);
                if (!floder.exists()) {
                    floder.mkdirs();
                }
                File file = new File(floder, FILE_NAME);


                FileOutputStream fos = new FileOutputStream(file);
                bw = new BufferedWriter(new OutputStreamWriter(fos));
                bw.write(json);
                bw.flush();
            } catch (Exception e) {
                e.printStackTrace();
                if (bw != null) {
                    try {
                        bw.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }

        }


    }
}
