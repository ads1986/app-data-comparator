package com.comparator.micronaut.config;

import com.comparator.controller.DataController;
import com.comparator.memory.db.MemoryDataRepository;
import com.comparator.usecase.CompareData;
import com.comparator.usecase.DecodeData;
import com.comparator.usecase.FindData;
import com.comparator.usecase.SaveData;

public class Config {

    public static DataController getDataController() {
        SaveData saveData = new SaveData(new MemoryDataRepository());
        CompareData compareData = new CompareData(new MemoryDataRepository());
        DecodeData decodeData = new DecodeData();
        FindData findData = new FindData(new MemoryDataRepository());
        return new DataController(compareData, saveData, decodeData, findData);
    }

}