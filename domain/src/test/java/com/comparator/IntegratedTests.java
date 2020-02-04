package com.comparator;

import com.comparator.entity.InputData;
import com.comparator.entity.OutputData;
import com.comparator.usecase.CompareData;
import com.comparator.usecase.DecodeData;
import com.comparator.usecase.FindData;
import com.comparator.usecase.SaveData;
import com.comparator.usecase.port.DataRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.when;

public class IntegratedTests {

    @Mock
    public DataRepository repository;

    @InjectMocks
    public FindData findData = new FindData();

    @InjectMocks
    public DecodeData decodeData = new DecodeData();

    @InjectMocks
    public CompareData compareData = new CompareData();

    @InjectMocks
    public SaveData saveData = new SaveData();

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void compareTwoValidData(){
        String left = decodeData.apply("eyJpZCI6ICIxMjMifQ==");
        Assertions.assertEquals(left, "{\"id\": \"123\"}");

        String right = decodeData.apply("eyJpZCI6ICIxMjMifQ==");
        Assertions.assertEquals(right, "{\"id\": \"123\"}");

        InputData inputDataLeft = InputData.builder().setId("1").setLeft(left).build();
        assertDoesNotThrow( () -> saveData.apply(inputDataLeft));

        InputData inputDataRight = InputData.builder().setId("1").setRight(right).build();
        assertDoesNotThrow( () -> saveData.apply(inputDataRight));

        InputData inputToBeFound = InputData.builder().setId("1").setLeft(left).setRight(right).build();
        when(repository.findById("1")).thenReturn(inputToBeFound);

        InputData inputFromDB = findData.apply("1");

        OutputData outputData = compareData.apply(inputFromDB);

        Assertions.assertEquals("Data is equal.", outputData.getData().get("message"));
    }

}