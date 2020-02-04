package com.comparator.unit;

import com.comparator.entity.InputData;
import com.comparator.usecase.CompareData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.Map;

public class CompareDataUnitTests {

    @InjectMocks
    public CompareData compareData = new CompareData();

    @Test
    public void compareWithInvalidObject(){
        Map<String, String> data = compareData.apply(null).getData();
        Assertions.assertEquals("Data not found.", data.get("message"));
    }

    @Test
    public void compareWithInvalidLeftData(){
        InputData inputData = InputData.builder().setId("1").setRight("{\"id\":\"123\"}").build();
        Map<String, String> data = compareData.apply(inputData).getData();
        Assertions.assertEquals("No left data available.", data.get("message"));
    }

    @Test
    public void compareWithInvalidRightData(){
        InputData inputData = InputData.builder().setId("1").setLeft("{\"id\":\"123\"}").build();
        Map<String, String> data = compareData.apply(inputData).getData();
        Assertions.assertEquals("No right data available.", data.get("message"));
    }

    @Test
    public void compareWithValidObjectAndEqualData(){
        InputData inputData = InputData.builder().setId("1").setLeft("{\"id\":\"123\"}").setRight("{\"id\":\"123\"}").build();
        Map<String, String> data = compareData.apply(inputData).getData();
        Assertions.assertEquals("Data is equal.", data.get("message"));
    }

    @Test
    public void compareWithValidObjectAndDiffDataSize(){
        InputData inputData = InputData.builder().setId("1").setLeft("{\"id\":\"1243\"}").setRight("{\"id\":\"123\"}").build();
        Map<String, String> data = compareData.apply(inputData).getData();
        Assertions.assertEquals("Has no equal size.", data.get("message"));
    }

    @Test
    public void compareWithValidObjecAndEqualSizeButDiffSizelData(){
        InputData inputData = InputData.builder().setId("1").setLeft("{\"id\":\"123\"}").setRight("{\"id\":\"156\"}").build();
        Map<String, String> data = compareData.apply(inputData).getData();
        Assertions.assertEquals("9", data.get("offset"));
        Assertions.assertEquals("2", data.get("diffLength"));
        Assertions.assertEquals("equal size but different content.", data.get("message"));
    }
}
