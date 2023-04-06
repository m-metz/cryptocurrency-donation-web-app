package com.group13.cryptocurrencywebapp.web_entity.benevity.causes.detail;

public class Social {

    private String facebook;
    private String twitter;
    private String rss;

    public Social() {

    }

    public Social(String facebook, String twitter, String rss) {
        this.facebook = facebook;
        this.twitter = twitter;
        this.rss = rss;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getRss() {
        return rss;
    }

    public void setRss(String rss) {
        this.rss = rss;
    }

}
