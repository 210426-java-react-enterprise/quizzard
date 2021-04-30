package com.revature.quizzard.models;

public class Prompt {
    private String response;

    public Prompt(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
