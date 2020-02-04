package com.comparator.usecase;

import com.comparator.usecase.exception.DecodeDataException;
import org.apache.commons.lang3.StringUtils;

import java.util.Base64;
import java.util.function.Function;

/**
 * @Usecase
 *
 * Created by Anderson de Souza Martins
 *
 * Use Case responsible to decode the base64 data to the original mode
 */
public final class DecodeData implements Function<String, String> {

    @Override
    public String apply(String data) {

        if (StringUtils.isEmpty(data))
            throw new DecodeDataException("Inform a valid data.");

        try {
            return new String(Base64.getDecoder().decode(data.getBytes()));
        } catch (IllegalArgumentException e){
            throw new DecodeDataException("Inform a valid data.");
        }
    }

}