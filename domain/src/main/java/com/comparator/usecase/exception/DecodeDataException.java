package com.comparator.usecase.exception;

/**
 * @Exception
 *
 * Created by Anderson de Souza Martins
 *
 * Class responsible for handling domain layer exceptions
 */
public class DecodeDataException extends RuntimeException {
    public DecodeDataException(final String message) {
        super(message);
    }
}
