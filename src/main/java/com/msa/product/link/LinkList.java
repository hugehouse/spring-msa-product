package com.msa.product.link;

public enum LinkList {
    checkOut("http://localhost:8082/pay/");

    private String link;

    private LinkList(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }
}
