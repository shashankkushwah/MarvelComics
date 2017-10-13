package com.marvel.comics.data.model;

/**
 * Created by Shashank on 13/10/2017.
 */

public class ComicsResponse {

    private int code;
    private String status;
    private String copyright;
    private String attributionText;
    private String etag;

    private ComicsData data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getAttributionText() {
        return attributionText;
    }

    public void setAttributionText(String attributionText) {
        this.attributionText = attributionText;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public ComicsData getData() {
        return data;
    }

    public void setData(ComicsData data) {
        this.data = data;
    }
}
