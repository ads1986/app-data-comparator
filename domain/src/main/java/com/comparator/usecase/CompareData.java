package com.comparator.usecase;

import com.comparator.entity.InputData;
import com.comparator.entity.OutputData;
import com.comparator.usecase.port.DataRepository;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @Usecase
 *
 * Created by Anderson de Souza Martins
 *
 * Use case responsible for comparing 2 decoded data
 */
public final class CompareData implements Function<InputData, OutputData> {

    private DataRepository repository;

    public CompareData() {
    }

    public CompareData(DataRepository repository) {
        this.repository = repository;
    }

    @Override
    public OutputData apply(InputData inputData) {
        Map<String, String> data =  compare(inputData);

        OutputData outputData = OutputData.builder().setData(data).build();
        return outputData;
    }

    private Map<String, String> compare(InputData inputData) {
        Map<String, String> data = new HashMap<>();

        if (inputData == null) {
            data.put("message", "Data not found.");
        } else if (StringUtils.isEmpty(inputData.getLeft())) {
            data.put("message", "No left data available.");
        } else if (StringUtils.isEmpty(inputData.getRight())){
            data.put("message", "No right data available.");
        } else if (isEqual(inputData)) {
            data.put("message", "Data is equal.");
        } else if (hasNoEqualSize(inputData)) {
            data.put("message", "Has no equal size.");
        } else if (!hasNoEqualSize(inputData)) {
            data.putAll(diffData(inputData));
        }

        return data;
    }

    private Map<String, String> diffData(InputData inputData){
        char[] left = inputData.getLeft().toCharArray();
        char[] right = inputData.getRight().toCharArray();

        int offset = 0;
        int diffLength = 0;

        for (int i=0; i < left.length; i++) {
            if (left[i] != right[i]) {
                offset = i;
                diffLength += 1;
            }
        }

        Map<String, String> data = new HashMap<>();
        data.put("offset", String.valueOf(offset));
        data.put("diffLength", String.valueOf(diffLength));
        data.put("message", "equal size but different content.");
        return data;
    }

    private Boolean hasNoEqualSize(InputData decoded){
        return decoded.getLeft().length() != decoded.getRight().length();
    }

    private Boolean isEqual(InputData decoded){
        return decoded.getLeft().equals(decoded.getRight());
    }

}