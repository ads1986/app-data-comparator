package com.comparator.unit;

import com.comparator.entity.InputData;
import com.comparator.usecase.FindData;
import com.comparator.usecase.exception.DecodeDataException;
import com.comparator.usecase.port.DataRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

public class FindDataUnitTests {

    @Mock
    public DataRepository repository;

    @InjectMocks
    public FindData findData = new FindData();

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findDataWithInvalidId(){
        Assertions.assertThrows(DecodeDataException.class, () -> {
            findData.apply(null);
        });
    }

    @Test
    public void findDataWithValidIdAndDataReturned(){
        InputData inputData = InputData.builder().setId("1").build();
        when(repository.findById("1")).thenReturn(inputData);
        Assertions.assertEquals(inputData.getId(), "1");
    }

    @Test
    public void findDataWithValidIdAndDataNotReturned(){
        when(repository.findById("1")).thenReturn(null);
        Assertions.assertNull(findData.apply("1"));
    }

}