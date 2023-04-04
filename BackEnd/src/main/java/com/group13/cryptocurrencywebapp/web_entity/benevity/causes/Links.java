package com.group13.cryptocurrencywebapp.web_entity.benevity.causes;

public class Links {

    private String self;
    private String first;
    private String next;
    private String last;

    public Links() {

    }

    public Links(String self, String first, String next, String last) {
        this.self = self;
        this.first = first;
        this.next = next;
        this.last = last;
    }

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

}
