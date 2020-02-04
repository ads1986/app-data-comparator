package com.comparator.memory.db.model;

import com.comparator.entity.InputData;

public class DataDB {

    private String id;
    private String right;
    private String left;

    private DataDB(Builder builder) {
        this.id = builder.id;
        this.right = builder.right;
        this.left = builder.left;
    }

    public DataDB(String id, String right, String left) {
        this.id = id;
        this.right = right;
        this.left = left;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String id;
        private String right;
        private String left;

        public Builder() {
        }

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setRight(String right) {
            this.right = right;
            return this;
        }

        public Builder setLeft(String left) {
            this.left = left;
            return this;
        }

        public DataDB build() {
            return new DataDB(this);
        }
    }

    public String getData(){
        if (this.left == null)
            return this.right;
        return this.left;
    }

    public String getId() {
        return id;
    }

    public String getRight() {
        return right;
    }

    public String getLeft() {
        return left;
    }

    public static DataDB toDataDB(InputData data){
        return DataDB.builder().setId(data.getId()).setLeft(data.getLeft()).setRight(data.getRight()).build();
    }

    public InputData toData(){
        InputData data = InputData.builder().setId(getId()).setLeft(getLeft()).setRight(getRight()).build();
        return data;
    }
}