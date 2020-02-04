package com.comparator.usecase;

import com.comparator.entity.InputData;
import com.comparator.usecase.exception.DecodeDataException;
import com.comparator.usecase.port.DataRepository;
import org.apache.commons.lang3.StringUtils;

/**
 * @Usecase
 *
 * Created by Anderson de Souza Martins
 *
 * Use Case responsible to persist the data object, using the DataRepository port
 */
public final class SaveData {

    private DataRepository decodeDataRepository;

    public SaveData() {
    }

    public SaveData(DataRepository decodeDataRepository) {
        this.decodeDataRepository = decodeDataRepository;
    }

    public void apply(InputData data) {
        if (data == null)
            throw new DecodeDataException("Inform a valid data.");

        if (StringUtils.isAllEmpty(data.getLeft(), data.getRight()))
            throw new DecodeDataException("Inform a left or right binary data.");

        if (StringUtils.isEmpty(data.getId()))
            throw new DecodeDataException("Inform a valid id.");

        decodeDataRepository.save(data);
    }

}