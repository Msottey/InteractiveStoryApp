package com.cbwit.interactivestoryapp.model;

/**
 * Created by toshaottey on 1/1/18.
 */

public class Choice {

    private int textID;
    private int nextPage;

    public Choice(int textId, int nextPage) {
        this.textID = textId;
        this.nextPage = nextPage;
    }

    public int getTextID() {
        return textID;
    }

    public void setTextID(int textID) {
        this.textID = textID;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }
}
