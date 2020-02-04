package com.comparator.controller.model;

import com.comparator.entity.OutputData;

import java.util.Map;

public class ResponseData {

    private Map<String, String> data;

    private ResponseData(ResponseData.Builder builder) {
        this.data = builder.data;
    }

    public static ResponseData.Builder builder() {
        return new ResponseData.Builder();
    }

    public static class Builder {
        private Map<String, String> data;

        public Builder() {
        }

        public ResponseData.Builder setData(Map<String, String> data) {
            this.data = data;
            return this;
        }

        public ResponseData build() {
            return new ResponseData(this);
        }
    }

    public Map<String, String> getData() {
        return data;
    }

    public static ResponseData toWeb(OutputData data){
        return ResponseData.builder().setData(data.getData()).build();
    }
}