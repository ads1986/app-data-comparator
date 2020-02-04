package com.comparator.unit;

import com.comparator.usecase.DecodeData;
import com.comparator.usecase.exception.DecodeDataException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class DecodeDataUnitTests {

    @InjectMocks
    public DecodeData decodeData = new DecodeData();

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void decodedWithValidEncodedData(){
        String decoded = decodeData.apply("eyJpZCI6ICIxMjMifQ==");
        Assertions.assertEquals(decoded, "{\"id\": \"123\"}");
    }

    @Test
    public void decodeWithAnInvalidEncodedData(){
        Assertions.assertThrows(DecodeDataException.class, () -> {
            decodeData.apply("{\"id\": \"123\"}");
        });
    }

    @Test
    public void decodeWithANullObject(){
        Assertions.assertThrows(DecodeDataException.class, () -> {
            decodeData.apply(null);
        });
    }

    @Test
    public void decodeWithAnEmptyString(){
        Assertions.assertThrows(DecodeDataException.class, () -> {
            decodeData.apply("");
        });
    }
}
