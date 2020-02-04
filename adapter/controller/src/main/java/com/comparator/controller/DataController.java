package com.comparator.controller;

import com.comparator.controller.model.RequestData;
import com.comparator.controller.model.ResponseData;
import com.comparator.entity.InputData;
import com.comparator.usecase.CompareData;
import com.comparator.usecase.DecodeData;
import com.comparator.usecase.SaveData;
import com.comparator.usecase.FindData;

/**
 * @Controller
 *
 * Created by Anderson de Souza Martins
 *
 * The controller receives the input and then performs the business operation through the Usecases.
 */
public class DataController {

    private CompareData compareDecodeData;
    private SaveData saveDecodeData;
    private DecodeData decodeData;
    private FindData findData;

    public DataController(CompareData compareDecodeData, SaveData saveDecodeData, DecodeData decodeData, FindData findData) {
        this.compareDecodeData = compareDecodeData;
        this.saveDecodeData = saveDecodeData;
        this.decodeData = decodeData;
        this.findData = findData;
    }

    public void saveDataLeft(final String id, final String data) {
        String decodedData = decodeData.apply(data);
        RequestData inputData = RequestData.builder().setId(id).setLeft(decodedData).build();
        saveDecodeData.apply(inputData.toData());
    }

    public void saveDataRight(final String id, final String data) {
        String decodedData = decodeData.apply(data);
        RequestData inputData = RequestData.builder().setId(id).setRight(decodedData).build();
        saveDecodeData.apply(inputData.toData());
    }

    public ResponseData compareData(final String id) {
        InputData inputData = findData.apply(id);
        return ResponseData.toWeb(compareDecodeData.apply(inputData));
    }

}