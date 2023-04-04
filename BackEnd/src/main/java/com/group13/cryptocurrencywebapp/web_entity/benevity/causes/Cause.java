package com.group13.cryptocurrencywebapp.web_entity.benevity.causes;

public class Cause {
    private Data[] data;

    private Links links;

    public Cause() {

    }

    public Cause(Data[] data, Links links) {
        this.data = data;
        this.links = links;
    }

    public Data[] getData() {
        return data;
    }

    public void setData(Data[] data) {
        this.data = data;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

}
