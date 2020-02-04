package com.comparator.usecase;

import com.comparator.entity.InputData;
import com.comparator.usecase.exception.DecodeDataException;
import com.comparator.usecase.port.DataRepository;
import org.apache.commons.lang3.StringUtils;

import java.util.function.Function;

/**
 * @Usecase
 *
 * Created by Anderson de Souza Martins
 *
 * Use Case responsible for searching for a specific data object, using the DataRepository port
 */
public class FindData implements Function<String, InputData> {

    private DataRepository repository;

    public FindData() {
    }

    public FindData(DataRepository repository) {
        this.repository = repository;
    }

    @Override
    public InputData apply(String id) {

        if (StringUtils.isEmpty(id))
            throw new DecodeDataException("Inform a valid id.");

        InputData inputData = repository.findById(id);

        return inputData;
    }
}
