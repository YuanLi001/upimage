//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.upimage.utils;

import java.io.Serializable;

public class Result<T> implements Serializable {
    private T result;
    private Boolean succeed;
    private String message;

    public Result() {
    }

    public Result(Boolean succeed) {
        this.succeed = succeed;
    }

    public Result(Boolean succeed, String message) {
        this.succeed = succeed;
        this.message = message;
    }

    public Result(T result, Boolean succeed, String message) {
        this.result = result;
        this.succeed = succeed;
        this.message = message;
    }

    public T getResult() {
        return this.result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public Boolean getSucceed() {
        return this.succeed;
    }

    public void setSucceed(Boolean succeed) {
        this.succeed = succeed;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Result{result=" + this.result + ", succeed=" + this.succeed + ", message='" + this.message + '\'' + '}';
    }
}
