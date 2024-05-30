package com.jayani.itubeapp;

public class itemModel {

    int id;
    String url;


    public itemModel(String url){
        this.url=url;

    }

    public itemModel(int id,String url){
        this.id=id;
        this.url=url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
