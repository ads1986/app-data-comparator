package com.comparator.entity;

/**
 * @Entity
 *
 * Created by Anderson de Souza Martins
 *
 * Domain class responsible for representing the input data for analysis
 */
public final class InputData {

    private String id;
    private String right;
    private String left;

    private InputData(Builder builder) {
        this.id = builder.id;
        this.right = builder.right;
        this.left = builder.left;
    }

    public InputData(String id, String right, String left) {
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

        public InputData build() {
            return new InputData(this);
        }
    }

    public String getData(){
        if (this.left == null)
            return this.right;
        return this.left;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setRight(String right) {
        this.right = right;
    }

    public void setLeft(String left) {
        this.left = left;
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
}