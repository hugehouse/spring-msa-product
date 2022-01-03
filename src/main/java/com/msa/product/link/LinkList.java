package com.msa.product.link;

public enum LinkList {
    checkOut("http://122.45.184.159:8082/pay/");

    private String link;

    private LinkList(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }
}
