package com.comparator.controller.model;

import com.comparator.entity.InputData;

public class RequestData {

    private String id;
    private String right;
    private String left;

    private RequestData(RequestData.Builder builder) {
        this.id = builder.id;
        this.right = builder.right;
        this.left = builder.left;
    }

    public RequestData(String id, String right, String left) {
        this.id = id;
        this.right = right;
        this.left = left;
    }

    public static RequestData.Builder builder() {
        return new RequestData.Builder();
    }

    public static class Builder {
        private String id;
        private String right;
        private String left;

        public Builder() {
        }

        public RequestData.Builder setId(String id) {
            this.id = id;
            return this;
        }

        public RequestData.Builder setRight(String right) {
            this.right = right;
            return this;
        }

        public RequestData.Builder setLeft(String left) {
            this.left = left;
            return this;
        }

        public RequestData build() {
            return new RequestData(this);
        }
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

    public InputData toData(){
        InputData data = InputData.builder().setId(getId()).setLeft(getLeft()).setRight(getRight()).build();
        return data;
    }
}