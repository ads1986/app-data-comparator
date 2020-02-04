package com.comparator.usecase.port;

import com.comparator.entity.InputData;

/**
 * @Port
 *
 * Created by Anderson de Souza Martins
 *
 * Interface that exposes the methods to be implemented by the repository in the adapter layer
 */
public interface DataRepository {

    InputData findById(String id);

    void save(InputData decoded);

}