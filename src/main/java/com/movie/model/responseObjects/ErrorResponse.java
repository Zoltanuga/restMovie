package com.movie.model.responseObjects;

import org.springframework.http.HttpStatus;

public class ErrorResponse {
    private String errorMessage;
    private HttpStatus status;

    public ErrorResponse(String errorMessage, HttpStatus status) {
        this.errorMessage = errorMessage;
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ErrorResponse that = (ErrorResponse) o;

        if (errorMessage != null ? !errorMessage.equals(that.errorMessage) : that.errorMessage != null) return false;
        return status == that.status;
    }

    @Override
    public int hashCode() {
        int result = errorMessage != null ? errorMessage.hashCode() : 0;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}