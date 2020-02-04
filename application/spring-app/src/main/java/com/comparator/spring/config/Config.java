package com.comparator.spring.config;

import com.comparator.controller.DataController;
import com.comparator.memory.db.MemoryDataRepository;
import com.comparator.usecase.CompareData;
import com.comparator.usecase.DecodeData;
import com.comparator.usecase.FindData;
import com.comparator.usecase.SaveData;
import com.comparator.usecase.port.DataRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    private final DataRepository dataRepository = new MemoryDataRepository();

    @Bean
    public SaveData saveData() {
        return new SaveData(dataRepository);
    }

    @Bean
    public CompareData compareData() {
        return new CompareData(dataRepository);
    }

    @Bean
    public DecodeData decodeData() {
        return new DecodeData();
    }

    @Bean
    public FindData findData() {
        return new FindData(dataRepository);
    }

    @Bean
    public DataController dataController() {
        return new DataController(compareData(), saveData(), decodeData(), findData());
    }

}