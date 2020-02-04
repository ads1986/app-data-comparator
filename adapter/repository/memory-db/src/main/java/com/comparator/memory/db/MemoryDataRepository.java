package com.comparator.memory.db;

import com.comparator.entity.InputData;
import com.comparator.memory.db.model.DataDB;
import com.comparator.usecase.port.DataRepository;

import java.util.HashMap;
import java.util.Map;

/**
 * @Repository
 *
 * Created by Anderson de Souza Martins
 *
 * A simple implementation of a memory database, just for testing purposes.
 */
public class MemoryDataRepository implements DataRepository {

    private static final Map<String, DataDB> database = new HashMap<String, DataDB>();

    public MemoryDataRepository() {
    }

    public void save(InputData data) {
        DataDB dataDB = DataDB.toDataDB(data);

        InputData inputData = findById(data.getId());
        if (inputData != null) {
            if (data.getLeft() != null) {
                inputData.setLeft(data.getLeft());
            } else {
                inputData.setRight(data.getRight());
            }
            database.put(dataDB.getId(), DataDB.toDataDB(inputData));
        } else {
            database.put(dataDB.getId(), dataDB);
        }
    }

    public InputData findById(String id) {
        if (database.containsKey(id)) {
            DataDB dataDB = database.get(id);
            return dataDB.toData();
        }
        return null;
    }
}
