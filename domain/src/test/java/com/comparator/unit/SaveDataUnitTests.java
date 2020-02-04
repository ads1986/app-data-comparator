package com.comparator.unit;

import com.comparator.entity.InputData;
import com.comparator.usecase.SaveData;
import com.comparator.usecase.exception.DecodeDataException;
import com.comparator.usecase.port.DataRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class SaveDataUnitTests {

    @Mock
    public DataRepository repository;

    @InjectMocks
    public SaveData saveData = new SaveData();

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void saveDataWithAValidObject(){
        InputData inputData = InputData.builder().setId("1").setLeft("Teste 1").build();
        assertDoesNotThrow( () -> saveData.apply(inputData));
    }

    @Test
    public void saveDataWithNullObject(){
        Assertions.assertThrows(DecodeDataException.class, () -> {
            saveData.apply(null);
        });
    }

    @Test
    public void saveDataWithInvalidId(){
        InputData inputData = InputData.builder().build();
        Assertions.assertThrows(DecodeDataException.class, () -> {
            saveData.apply(inputData);
        });
    }

    @Test
    public void saveDataWithInvalidLeftOrRight(){
        InputData inputData = InputData.builder().setId("1").build();
        Assertions.assertThrows(DecodeDataException.class, () -> {
            saveData.apply(inputData);
        });
    }
}