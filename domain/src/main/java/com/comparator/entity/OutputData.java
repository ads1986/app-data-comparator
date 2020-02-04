package com.comparator.entity;

import java.util.Map;

/**
 * @Entity
 *
 *  Created by Anderson de Souza Martins
 *
 * Domain class responsible for maintaining the messages related to analysis and returning the layers above
 */
public final class OutputData {

    private Map<String, String> data;

    private OutputData(OutputData.Builder builder) {
        this.data = builder.data;
    }

    public static OutputData.Builder builder() {
        return new OutputData.Builder();
    }

    public static class Builder {
        private Map<String, String> data;

        public Builder() {
        }

        public OutputData.Builder setData(Map<String, String> data) {
            this.data = data;
            return this;
        }

        public OutputData build() {
            return new OutputData(this);
        }
    }

    public Map<String, String> getData() {
        return data;
    }

    public static OutputData toWeb(OutputData data){
        return OutputData.builder().setData(data.getData()).build();
    }
}