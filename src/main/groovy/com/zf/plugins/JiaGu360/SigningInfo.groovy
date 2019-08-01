package com.zf.plugins.JiaGu360;

public class SigningInfo {
    String storeFilePath
    String storePassword
    String keyAlias
    String keyPassword

    SigningInfo() {
    }

    SigningInfo(String storeFilePath, String storePassword, String keyAlias, String keyPassword) {
        this.storeFilePath = storeFilePath
        this.storePassword = storePassword
        this.keyAlias = keyAlias
        this.keyPassword = keyPassword
    }

    void storeFilePath(String storeFilePath) {
        this.storeFilePath = storeFilePath
    }

    void storePassword(String storePassword) {
        this.storePassword = storePassword
    }

    void keyAlias(String keyAlias) {
        this.keyAlias = keyAlias
    }

    void keyPassword(String keyPassword) {
        this.keyPassword = keyPassword
    }


    @Override
    public String toString() {
        return "SigningInfo{" +
                "storeFilePath='" + storeFilePath + '\'' +
                ", storePassword='" + storePassword + '\'' +
                ", keyAlias='" + keyAlias + '\'' +
                ", keyPassword='" + keyPassword + '\'' +
                '}';
    }
}
