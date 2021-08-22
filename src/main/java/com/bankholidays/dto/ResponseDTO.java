package com.bankholidays.dto;

public class ResponseDTO {

    private byte[] fileOutput;

    private boolean hasError;

    private String errorMessage;

    public byte[] getFileOutput() {
        return this.fileOutput;
    }

    public void setFileOutput(byte[] fileOutput) {
        this.fileOutput = fileOutput;
    }

    public boolean hasError() {
        return this.hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
