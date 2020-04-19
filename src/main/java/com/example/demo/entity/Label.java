package com.example.demo.entity;

public enum Label {
    NEW("new"), REOPEN("reopen"), DONE("done"), READY_FOR_REVIEW("ready for review");
    private String label;

    Label(String str) {
        this.label=str;
    }

    @Override
    public String toString() {
        return label;
    }
}
